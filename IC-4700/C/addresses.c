#include <stdio.h>
#include <stdlib.h>

int global = -1;
const int K = 0;
const char* s = "hola mundo";

void foo(int param) {
    int *p = malloc(sizeof(int));

    int local = 0;

    printf("local: %x\n", (unsigned int) &local);
    printf("param: %x\n", (unsigned int) &param);
    printf("global: %x\n", (unsigned int) &global);
    printf("K: %x\n", (unsigned int) &K);

    printf("p: %x\n", (unsigned int) p);
}

int main() {
    printf("foo: %x\n", (unsigned int) (void*)&foo);
    printf("s: %x\n", (unsigned int) &s);
    foo(1);
}
