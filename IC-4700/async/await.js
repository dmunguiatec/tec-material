const fs = require('fs').promises;

async function leer(filename) {
    try {
        const data = await fs.readFile(filename, 'utf-8');
        console.log('Filename: ', filename);
        
        const lines = data.split('\n');
        console.log('Number of lines:', lines.length);

    } catch (err) {
        console.error('Error leyendo el archivo:', err);
    }    
}

leer('data/Distelec.txt');