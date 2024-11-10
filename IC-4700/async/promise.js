const fs = require('fs');

async function leer(filename) {
    try {
        const promise = new Promise((resolve, reject) => {
            fs.readFile(filename, 'utf-8', (err, data) => {
                if (err) {
                    reject(err);
                } else {
                    resolve(data);
                }
            });
        });
            
        console.log('Filename: ', filename);

        const data = await promise;
        const lines = data.split('\n');
        console.log('Number of lines:', lines.length);

    } catch (err) {
        console.error('Error leyendo el archivo:', err);
    }    
}

leer('data/Distelec.txt');