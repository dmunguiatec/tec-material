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
