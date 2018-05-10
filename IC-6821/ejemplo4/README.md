# Ejemplo 4 - Spring Boot + REST + OrientDB

## Configuración

Crear la base de datos en OrientDB. La manera más sencilla es utilizando la consola gráfica en http://localhost:2480.

Definir la configuración en variables de ambiente, ejemplo:

```bash
export EJ4_ORIENT_URL="remote:localhost/roadmaps"
export EJ4_ORIENT_USR="roadmaps"
export EJ4_ORIENT_PWD="r04dm4p5"
```

Nota: se recomienda el uso de direnv (https://github.com/direnv/direnv) para manejar variables de ambiente.

## Compilar y ejecutar

```bash
gradle clean build && java -jar build/libs/ejemplo4-0.0.1.jar
```

## Insertar en batch

Con `postman` o `curl` enviar un `POST` a `http://localhost:8080/roadmap/batch` con el siguiente `JSON` en el body:

```json
{
    "cities": [
        {
            "name": "San José"
        },
        {
            "name": "Heredia"
        },
        {
            "name": "Alajuela"
        }
    ],

    "roads": [
        {
            "from": "San José",
            "to": "Heredia",
            "name": "Ruta 3",
            "distanceKms": 12
        },
        {
            "from": "San José",
            "to": "Alajuela",
            "name": "Ruta 1",
            "distanceKms": 20
        },
        {
            "from": "Heredia",
            "to": "Alajuela",
            "name": "Ruta 3",
            "distanceKms": 13
        }
    ]
}
```

## Consultas

```
curl http://localhost:8080/roadmap/city
```

```json
[{"rid":"#12:0","name":"San José"},{"rid":"#12:1","name":"Heredia"},{"rid":"#12:2","name":"Alajuela"}]
```

```
curl http://localhost:8080/roadmap/road
```

```json
[{"rid":"#13:0","name":"Ruta 3","distanceKms":12,"from":{"rid":"#12:1","name":"Heredia"},"to":{"rid":"#12:0","name":"San José"}},{"rid":"#13:1","name":"Ruta 1","distanceKms":20,"from":{"rid":"#12:2","name":"Alajuela"},"to":{"rid":"#12:0","name":"San José"}},{"rid":"#13:2","name":"Ruta 3","distanceKms":12,"from":{"rid":"#12:0","name":"San José"},"to":{"rid":"#12:1","name":"Heredia"}},{"rid":"#13:3","name":"Ruta 1","distanceKms":20,"from":{"rid":"#12:0","name":"San José"},"to":{"rid":"#12:2","name":"Alajuela"}},{"rid":"#13:4","name":"Ruta 3","distanceKms":13,"from":{"rid":"#12:1","name":"Heredia"},"to":{"rid":"#12:2","name":"Alajuela"}},{"rid":"#13:5","name":"Ruta 3","distanceKms":13,"from":{"rid":"#12:2","name":"Alajuela"},"to":{"rid":"#12:1","name":"Heredia"}}]
```

## Referencias

Ejemplo basado en la herramienta y ejemplo de eugene-kamenev

https://github.com/eugene-kamenev/orientdb-spring-boot-example

https://github.com/eugene-kamenev/orientdb-groovy