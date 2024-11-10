const fs = require('fs');

function leer(filename) {
    fs.readFile(filename, 'utf8', (err, data) => {
        if (err) {
            console.error('Error leyendo el archivo:', err);
            return;
        }

        const lines = data.split('\n');
        console.log('Number of lines:', lines.length);
    });

    console.log('Filename: ', filename);
}

leer('data/Distelec.txt');