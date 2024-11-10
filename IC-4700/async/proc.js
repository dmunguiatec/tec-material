const fs = require('fs');
const fsp = fs.promises;
const readline = require('readline');


function expiraciones() {
    const filename = 'data/PADRON_COMPLETO.txt'
    return new Promise((resolve, reject) => {
        const lines = [];
        const stream = fs.createReadStream(filename, { encoding: 'utf8' });
        const rl = readline.createInterface({
            input: stream,
            crlfDelay: Infinity 
        });

        rl.on('line', (line) => {
            if (expira(line)) {
                lines.push(line);
            }
        });

        rl.on('close', () => {
            resolve(lines);
        });

        rl.on('error', (err) => {
            reject(err);
        });
    });
}

function expira(linea) {
    const SEPARADOR = ',';
    const FECHACADUC = 3;

    const registro = linea.split(SEPARADOR);
    return registro[FECHACADUC].startsWith("202412");
}

async function distritos() {
    const filename = 'data/Distelec.txt'
    const data = await fsp.readFile(filename, 'utf-8');
    const lines = data.split('\n');
    return lines;

}

async function main() {
    const promesa = expiraciones();
    const dist = await distritos();
    console.log("Número de distritos: ", dist.length);

    const expirados = await promesa;
    console.log("Número de expiraciones en 12/2024: ", expirados.length);
}

main();