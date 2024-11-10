## Liga entre Java y C

1. Implementar una biblioteca en C para ser invocada desde Java. En nuestro caso será una biblioteca que contiene un procedimiento para calcular la raíz cuadrada de un número flotante. Crear un archivo llamado `raizc.c` con el siguiente contenido:

```c
#include <assert.h>
#include <math.h>

double raizc(double n) {
    assert(n >= 0);

    if (n == 0) {
        return 0;
    }

    double precision = 1e-10;
    double aprox = n; 

    double error;
    do {
        double nueva_aprox = 0.5 * (aprox + n / aprox);
        error = fabs(nueva_aprox - aprox);
        aprox = nueva_aprox;
    } while (error > precision);

    return aprox;
}
```

2. Declarar en un archivo `RaizCuadrada.java` una clase que contenga el método correspondiente en Java. La firma del método usa el modificador `native`.

```java
public class RaizCuadrada {
    
    public native double raizc(double n);

    static {
        System.loadLibrary("raizc");
    }
}
```

3. Compilar la clase Java con el modificador `-h` para generar el correspondiente archivo de encabezado de C `RaizCuadrada.h`.

```bash
javac -h . RaizCuadrada.java
```

4. Implementar en `raiz.c` el procedimiento que fue declarado en el archivo de encabezado de C generado por el compilador de Java.

```c
#include <assert.h>
#include <math.h>

#include <jni.h>
#include "RaizCuadrada.h"

double raizc(double n) {
    assert(n >= 0);

    if (n == 0) {
        return 0;
    }

    double precision = 1e-10;
    double aprox = n; 

    double error;
    do {
        double nueva_aprox = 0.5 * (aprox + n / aprox);
        error = fabs(nueva_aprox - aprox);
        aprox = nueva_aprox;
    } while (error > precision);

    return aprox;
}

JNIEXPORT jdouble JNICALL Java_RaizCuadrada_raizc(JNIEnv *env, jobject obj, jdouble n) {
    return raizc(n);
}
```

5. Compilar la biblioteca.

```bash
gcc -I./ -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/linux" -shared -o libraizc.so -fPIC raizc.c -lm
```

6. Escribir un método para ejecutar el programa en `RaizCuadrada.java`.

```java
public static void main(String[] args) {
    RaizCuadrada rc = new RaizCuadrada();
    double resultado = rc.raizc(2);
    System.out.println("Raiz cuadrada de 2: " + resultado);
}
```

7. Compilar nuevamente la clase de java.

```bash
javac RaizCuadrada.java
```

8. Ejecutar el programa, indicando al interprete de Java la ruta de la biblioteca compartida.

```bash
java -Djava.library.path=./ RaizCuadrada
```
