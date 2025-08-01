section .bss
    buffer resb 12 ; max 10 dígitos para un int32 + cambio de línea + terminador nulo

section .text
    global _start

_start:
    ; Calcular la expresión
    mov eax, 1
    push eax
    mov eax, 2
    push eax
    mov eax, 3
    pop ebx
    imul eax, ebx
    pop ebx
    add eax, ebx
    push eax
    mov eax, 4
    push eax
    mov eax, 5
    push eax
    mov eax, 6
    pop ebx
    add eax, ebx
    pop ebx
    imul eax, ebx
    pop ebx
    add eax, ebx

    ; Convertir número a hilera
    mov ecx, buffer + 11
    mov byte [ecx], 0xA
    dec ecx

    mov ebx, 10

.convert_loop:
    xor edx, edx
    div ebx
    add dl, '0'
    mov [ecx], dl
    dec ecx
    test eax, eax
    jnz .convert_loop

    inc ecx

    ; Imprimir en stdout
    mov eax, 4
    mov ebx, 1
    mov edx, buffer + 12
    sub edx, ecx
    mov ecx, ecx
    int 0x80

    ; Terminar
    mov eax, 1
    xor ebx, ebx
    int 0x80
