section .bss
    buffer resb 12 ; max 10 dígitos para un int32 + cambio de línea + terminador nulo

section .text
    global _start

_start:
    ; Calcular la expresión
