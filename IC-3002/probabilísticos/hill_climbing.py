import math

from dominio_tsp import DominioTSP

def optimizar(dominio, k_vecinos):
    """Algoritmo de optimización estocástica hill climbing.

    Entradas:
    dominio (Dominio)
        Un objeto que modela el dominio del problema que se quiere aproximar.

    k_vecinos:
        Tamaño de la muestra de vecinos a calcular

    Salidas:
        (estructura de datos) Estructura de datos según el dominio, que representa una
        aproximación a la mejor solución al problema.
    """

    sol = dominio.generar()

    sol_min = sol
    costo_min = dominio.fcosto(sol)

    while True:
        print(sol, costo_min)

        vecinos = []
        for _ in range(0, k_vecinos):
            vecinos.append(dominio.vecino(sol))
        
        for vecino in vecinos:
            costo_v = dominio.fcosto(vecino)
            if costo_v < costo_min:
                costo_min = costo_v
                sol_min = vecino
        
        if sol_min == sol:
            return sol
        else:
            sol = sol_min        

if __name__ == "__main__":
    dominio = DominioTSP('datos/ciudades_cr.csv', 'Alajuela')
    n = len(dominio.generar())
    k = math.floor(n * (n - 1) * 0.5 * 0.2)
    sol = optimizar(dominio, k_vecinos=k)
    print(dominio.texto(sol), dominio.fcosto(sol))
