# Compilador de expresiones

Compila una expresión aritmética simple en código ensamblador x86.

Las expresiones admiten las operaciones `+` y `*`, números naturales y paréntesis para agrupación.

## Construcción

```bash
./gradlew clean installDist
```

Produce un ejecutable llamado

```bash
app/build/install/app/bin/app
```

## Ejecución

Los archivos de código fuente deben tener la extensión `.expr` y contener una única expresión a ejecutar finalizada con `.`

Las expresiones se componen por números naturales, los operadores `+` y `*`, y paréntesis para agrupación.

Por ejemplo `prueba.expr`

```
1 + 2
*
3 + 4
*
(5 + 6).
```

La ejecución del compilador produce un archivo con código ensamblador con el mismo nombre que el archivo fuente pero con extensión `.asm`.

```bash
app/build/install/app/bin/app prueba.expr
```

En este caso produce `prueba.asm`. Este archivo puede ser ensamblado con NASM para generar un ejecutable nativo.

```bash
nasm -f elf32 prueba.asm -o prueba.o &&
ld -m elf_i386 prueba.o -o prueba
```
