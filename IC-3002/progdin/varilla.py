def varilla_ingenuo(tam_varilla, valores):
    if tam_varilla == 0:
        return 0

    valor = -1
    for i in range(1, tam_varilla + 1):
        valor = max(valor, valores[i] + varilla_ingenuo(tam_varilla - i, valores))

    return valor


max_valor = varilla_ingenuo(8, valores=[0, 1, 5, 8, 9, 10, 17, 17, 20])
print(max_valor)


def varilla_arriba_abajo(tam_varilla, valores, memo):
    if memo[tam_varilla] is not None:
        return memo[tam_varilla]

    valor = -1
    for i in range(1, tam_varilla + 1):
        valor = max(valor, valores[i] + varilla_arriba_abajo(tam_varilla - i, valores, memo))

    memo[tam_varilla] = valor
    return valor


max_valor = varilla_arriba_abajo(8, valores=[0, 1, 5, 8, 9, 10, 17, 17, 20], memo=[0]+([None]*8))
print(max_valor)


def varilla_abajo_arriba(tam_varilla, valores):
    memo = [0]+([None]*8)
    valor = -1
    for j in range(1, tam_varilla + 1):
        for i in range(1, j + 1):
            valor = max(valor, valores[i] + memo[j - i])

        memo[j] = valor

    return valor


max_valor = varilla_abajo_arriba(8, valores=[0, 1, 5, 8, 9, 10, 17, 17, 20])
print(max_valor)
