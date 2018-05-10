;; basado en: https://theantlrguy.atlassian.net/wiki/display/ANTLR3/LLVM

@s = internal constant [4 x i8] c"%d\0A\00"

declare i32 @printf(i8 *, ...)
define i32 @fact(i32 %n_arg) {
   ; init arg(s): n
   %n = alloca i32
   store i32 %n_arg, i32* %n
   ; if ( n<=1)
   %r1 = load i32* %n
   %r2 = add i32 1, 0
   %r3 = icmp sle i32 %r1, %r2
   br i1 %r3, label %true5, label %false5
   true5:
   ; return 1;
   %r4 = add i32 1, 0
   ret i32 %r4
   false5:
   ; else return n*fact(n-1);
   %r6 = load i32* %n
   ; fact(n-1)
   %r7 = load i32* %n
   %r8 = add i32 1, 0
   %r9 = sub i32 %r7, %r8
   %r10 = call i32(i32)* @fact(i32 %r9)
   %r11 = mul i32 %r6, %r10
   ret i32 %r11
}

define i32 @main() {
   %ps = getelementptr [4 x i8]* @s, i64 0, i64 0
   %m0 = add i32 5, 0   
   %m1 = call i32(i32)* @fact(i32 %m0)
   call i32 (i8*, ...)* @printf(i8* %ps, i32 %m1)
   ret i32 0
}