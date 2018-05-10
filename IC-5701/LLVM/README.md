# LLVM: ejemplos de uso

Pasando de la representación intermedia LLVM a un ejecutable x86 (64 bits)

```
llvm-as -o holamundo.bc holamundo.ll
llc holamundo.bc -o holamundo.s -march x86-64
llvm-gcc -o holamundo holamundo.s
```

Links de interés:

https://theantlrguy.atlassian.net/wiki/display/ANTLR3/LLVM

http://www.llvm.org/docs/LangRef.html
