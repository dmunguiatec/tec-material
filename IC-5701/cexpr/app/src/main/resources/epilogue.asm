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
