def n_reinas(tablero, n, fila):
    if fila == n:
        reportar(tablero)
    else:
        for col in range(n):
            if pos_segura(tablero, fila, col):
                tablero[fila] = col
                n_reinas(tablero, n, fila + 1)
                tablero[fila] = None

def reportar(tablero):
    n = len(tablero)

    for i in range(n):
        row = ""
        for j in range(n):
            if tablero[i] == j:
                row += "X"
            else:
                row += "."
        print(row)

    print('\n')


def pos_segura(tablero, fila, col):
    d1 = fila + col
    d2 = fila - col
    for i in range(fila):
        j = tablero[i]
        if j == col or (i+j) == d1 or (i-j) == d2:
            return False

    return True


import random
import statistics

def estimar(n_muestra, n):
    muestra = []
    for i in range(n_muestra):
        tablero = [None] * n
        fila = 0
        nivel = 1
        total = 1

        while fila < n:
            hijos = expandir_ancho(tablero, n, fila)
            if len(hijos) == 0:
                fila = n
            else:
                total *= len(hijos)
                nivel += 1
                col = random.choice(hijos)
                tablero[fila] = col
                fila += 1
        muestra.append(total)

    return statistics.mean(muestra)


def expandir_ancho(tablero, n, fila):
    hijos = []
    for col in range(0, n):
        if pos_segura(tablero, fila, col):
            hijos.append(col)

    return hijos
