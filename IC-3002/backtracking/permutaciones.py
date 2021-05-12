def permutaciones(simbolos: set, parcial: list, n: int, k: int, resultado: list):
    """
    Genera todas las posibles permutaciones del conjunto simbolos
    :param simbolos: (S) conjunto de simbolos a partir de los cuales se generan las permutaciones
    :param parcial: (π) la permutación que se va construyendo parcialmente
    :param n: (n) numero de simbolos
    :param k: (k) indice para ir caminando sobre el conjunto de simbolos
    :param resultado: (P) lista de permutaciones resultantes
    :return: permutaciones
    """

    if k == n:
        resultado.append(list(parcial))

    else:
        for s in simbolos:
            simbolos_prima = simbolos.difference({s})
            parcial[k] = s
            permutaciones(simbolos_prima, parcial + [s], n, k + 1, resultado)
            parcial[k] = None


S = {'A', 'B', 'C', 'D'}
n = len(S)
resultado = list()
permutaciones(S, [None] * n, n, 0, resultado)

for perm in resultado:
    print(perm)
