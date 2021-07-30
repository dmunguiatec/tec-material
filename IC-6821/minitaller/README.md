# Ambiente desarrollo Java #

## Preparación previa ##

Correr un contenedor volátil de Ubuntu para demostrar instalación de `sdkman`

```bash
docker run -it ubuntu /bin/bash
```

En este contenedor, instalar paquetes requeridos por el script de instalación de `sdkman`


```bash
apt update && apt install -y curl zip unzip
```

Para demostrar instalación de java y gradle, construir imagen con herramientas pre-cargadas

```bash
docker build -t ic6821-devenv .
```

## Guión ##

Abrir contenedor volátil en una terminal y correr la imagen en otra terminal.

```
docker run -it ic6821-devenv /bin/bash
```

Abrir el website de sdkman e instalar en el contenedor volátil

```
+---------+---------+
|         |         |
|         |  term1  |
|         |         |
| browser +---------+
|         |         |
|         |  term2  |
|         |         |
+---------+---------+
```

Demostrar uso de sdkman

```
sdk install java
sdk install gradle
sdk current
sdk current java
```

Ver otros comandos en https://sdkman.io/usage

Empezar a trabajar el demo fuera del contenedor:

Crear directorio

```bash
mkdir demo
cd demo
```

Crear app con gradle

```bash
gradle init
```

Queremos las opciones: application, Java, only one application project, Groovy, JUnit 4, `demo`, `edu.ic6821.demo`

Ir al sitio de jetbrains (https://www.jetbrains.com/idea/download) y hablar de la instalación de IntelliJ

Abrir IntelliJ y cargar proyecto `demo`.

Descripción de archivos.

Mostrar cómo ejecutar la aplicación: mostrar gradle en terminal: build (explicar contenidos de build), installDist y ejecución del ejecutable, clean, distZip.

Mostrar la terminal integrada.

Trabajar módulo `users` para demostrar: anotaciones, optional, lambdas.

- Crear paquete `user`
- Crear modelo `User` y generar constructor y getters, equals, hashCode y toString
- Crear interfaces `UserRepository`. Explicar Optional.
- Crear implementación `ListUserRepository`. Explicar lambdas.

- Implementar tests

Agregar este snippet a `build.gradle` para ver mayor detalle sobre pruebas:

```
test {
    // display the following test events
    testLogging {
        events "PASSED", "FAILED", "SKIPPED"
        exceptionFormat = 'full'
    }
}
```

- Demostrar la función de las pruebas haciendo que fallen modificando el código.
- Introducir lombok y verificar que tests siguen corriendo



