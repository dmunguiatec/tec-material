def comprimir(entrada):
    codigo = 4
    tabla = {'a': 0, 'c': 1, 'g': 2, 't': 3}

    ultima_sec_reconocida = ""
    salida = []

    for simbolo in entrada:
        secuencia = ultima_sec_reconocida + simbolo

        if secuencia in tabla:
            ultima_sec_reconocida = secuencia
        else:
            salida.append(tabla[ultima_sec_reconocida])
            tabla[secuencia] = codigo
            codigo += 1
            ultima_sec_reconocida = simbolo

    if ultima_sec_reconocida:
        salida.append(tabla[ultima_sec_reconocida])

    print(tabla)

    return salida


def descomprimir(entrada):
    sig_codigo = 4
    tabla = {0: 'a', 1: 'c', 2: 'g', 3: 't'}

    salida = ''
    decodificada_anterior = tabla[entrada.pop(0)]
    salida = decodificada_anterior

    for codigo in entrada:
        if codigo in tabla:
            decodificada = tabla[codigo]
        elif codigo == sig_codigo:
            decodificada = decodificada_anterior + decodificada_anterior[0]
        else:
            raise ValueError(f'Codigo desconocido {codigo}')

        salida += decodificada

        tabla[sig_codigo] = decodificada_anterior + decodificada[0]
        sig_codigo += 1

        decodificada_anterior = decodificada

    return salida

s = "gatttccactacgcattcttgatgttccctacagctctcacatttcccgaatcggcgcgaaatctttcgttcgttctgtcggagcgcggttggtgggtgcgtcaagtagagttttttccaaaagactggaattatcgaagccgtagatccagcgcatatagccgtcgttgagtttcgttaactgtaatccatgatctttgaatatatcatattcatacgcgcattacatcttgctccttaacccgggaatctaagtcagcaggactctgaccggactcccccccgggcctagggactatgtctccacacggtggcgctttttcaggggatgcatgaacggtgaatgacgatatgtgggggtccgaaccagaagacgactttgtaaccttacatattcaacgcttcgtcaccctgtcaagcgagatgtatcggtgccgctggtaggccgtgtgcgggcaagacgttcatgtcaggatcccgttttgacttgccgactcctgaatttcaagtactggggatccactctaggaaggatagagcagatcgaatacgcaaacttgtttagggctcacacatgcgttcgcaatgagagggctaaagcttattagcttaggtacttaggtcaacgaaacggtctttcctgtaaccccaagcggtttagttagcacccgtactcgtgccctcgttcaggcgaaacgacaagcgtaaattcaagctccgctcacacttatagatgcgctttagctgctcgaagcgagcacacgtgtatatctcctcaaactccagtctcggcacatcgcggcttcattggttgtcatgggacgcccttgtttttgctaatccacgaggattccgccaactctacattagccacgacgatactgctttcagggtggcacttcatatttaggcagctaccgatcgataggtcaattttaagacatttcagaaagcctcagcgtggaaatgcgagaatggcggaacggcagtgacgatttac"
#s = "gtgcgtgcagtgcgtg"
c = comprimir(s)
print(f's = {s}')
print(f'c = {c}')

d = descomprimir(c)
assert s == d
