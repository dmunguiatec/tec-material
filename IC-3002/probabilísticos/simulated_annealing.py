import math
from random import random

from dominio_tsp import DominioTSP

def optimizar(dominio, temperatura = 10e4, tasa_enfriamiento = 0.995):
    """Algoritmo de optimización estocástica simulated annealing.

    Entradas:
    dominio (Dominio)
        Un objeto que modela el dominio del problema que se quiere aproximar.

    temperatura (float/int)
        Temperatura inicial del algoritmo, se recomienda un número alto

    tasa_enfriamiento (float)
        Porcentaje de enfriamiento de la temperatura durante cada iteración, el valor
        por defecto es 0.95, lo que indica una tasa de enfriamiento del 5%.

    Salidas:
        (estructura de datos) Estructura de datos según el dominio, que representa una
        aproximación a la mejor solución al problema.
    """

    sol = dominio.generar()
    costo = dominio.fcosto(sol)

    while temperatura > 0.01:
        nueva_sol = dominio.vecino(sol)
        nuevo_costo = dominio.fcosto(nueva_sol)

        p = math.exp(-(abs(nuevo_costo - costo))/temperatura)
        p_azar = random()

        print(sol, f'{nuevo_costo} < {costo}', f', p = {p}')

        if nuevo_costo < costo or p_azar <= p:
            sol = nueva_sol
            costo = nuevo_costo
        
        temperatura = temperatura * tasa_enfriamiento
    
    return sol


if __name__ == "__main__":
    dominio = DominioTSP('datos/ciudades_cr.csv', 'Alajuela')
    sol = optimizar(dominio)
    print(dominio.texto(sol), dominio.fcosto(sol))
