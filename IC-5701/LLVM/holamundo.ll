; define string constant "%d\n"
@s = internal constant [12 x i8] c"Hola Mundo\0A\00"
declare i32 @puts(i8*)

define i32 @main() {
   %ps = getelementptr [12 x i8]* @s, i64 0, i64 0
   call i32 @puts(i8* %ps)
   ret i32 0
}
