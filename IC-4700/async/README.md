## Ambiente de desarrollo

1. Instalar nvm

```bash
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.1/install.sh | bash
```

2. Instalar node

```bash
nvm install node
```

3. Asegurarse de utilizar la última versión disponible

```bash
nvm alias default node
```

4. Instalar dependencias

```bash
npm install
```

5. Descargar los datos del padrón electoral desde https://www.tse.go.cr/zip/padron/padron_completo.zip y descomprimir en el directorio `data/`

## Ejemplos

### Funciones asincrónicas con callback 

`callback.js`

```js
    fs.readFile(filename, 'utf8', (err, data) => {
        if (err) {
            console.error('Error leyendo el archivo:', err);
            return;
        }

        const lines = data.split('\n');
        console.log('Number of lines:', lines.length);
    });

```

La función asincrónica llama a la función *callback* correspondiente a la expresión lambda en el tercer argumento de la llamada a `readFile`. El callback siembre recibe dos paráemtros: un objeto que representa un error, en caso de que la operación falle y el resultado de la operación, en caso de ser exitosa. En nuestro ejemplo llamamos `data` a este resultado. La función *callback* se ejecuta automáticamente cuando la operación `readFile` finaliza.

El manejo con *callbacks* tiene el problema de que si dentro del callback necesito hacer otra llamada asíncrona esta a su vez hará otro callback diferente y así sucesivamente, complicando la lectura y mantenibilidad del código.

### Funciones asincrónicas con async/await

`await.js`

```js
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
```

Es el mecanismo más recomendado para evitar los problemas de los callbacks. El uso de `await` antes de la llamada asincrónica indica al intérprete que debe esperar a que la llamada finalice antes de proceder a ejecutar el siguiente comando. La función que contiene a estas llamadas debe ser declarada como `async`, y esta a su vez debe ser invocada con `await` en otras funciones que la utilicen.

### Funciones asincrónicas con promesas

`promise.js`

```js
const promise = new Promise((resolve, reject) => {
    fs.readFile(filename, 'utf-8', (err, data) => {
        if (err) {
            reject(err);
        } else {
            resolve(data);
        }
    });
});

console.log('Hacemos otra cosa mientras tanto...');

const data = await promise;
const lines = data.split('\n');
console.log('Number of lines:', lines.length);
```

El objeto `Promise` representa una promesa de que en el futuro la llamada asincrónica producirá un resultado.

La promesa recibe una expresión lambda que a su vez recibe dos funciones: `resolve` que se ejecuta cuando la llamada asincrónica es exitosa para retornar el resultado correspondiente, y `reject` que se ejecuta cuando la llamada asincrónica falla para retornar el error correspondiente.

Este modelo permite no bloquear la ejecución de inmediato cuando se hace la llamada asincrónica, dejándola en ejecución en el *background*. Podemos demorar el bloqueo hasta más adelante en el código.

### Optimización de llamadas pesadas

`proc.js`

Muestra un ejemplo del uso de promesas para optimizar el tiempo de ejecución del procesamiento de archivos muy grandes.

En este ejemplo iniciamos el procesamiento del archivo del padrón electoral, filtrando todos los registros que tienen fecha de caducidad en diciembre de 2024. 

Este es un archivo que tiene millones de registros entonces, en lugar de bloquear la ejecución, retornamos una promesa, dejamos que se siga procesando en *background*, y pasamos a procesar el archivo de distritos que tiene sólo miles de archivos. En este caso si bloqueamos la ejecución hasta completarlo y luego bloqueamos la promesa inicial hasta que también termine el procesamiento del padrón.

De esta forma, el tiempo que habríamos gastado esperando el primer procesamiento -mucho más largo- lo utilizamos finalizando una tarea más pequeña.