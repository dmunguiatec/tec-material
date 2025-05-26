import csv
import random

from dominio import Dominio


class DominioTSP(Dominio):
    """
    Esta clase modela el dominio del problema del Vendedor Viajero para su resolución
    con algoritmos probabilísticos.

    Las soluciones se modelan como listas de enteros, donde cada número representa
    una ciudad específica. Si el grafo contiene n ciudades, la lista siempre contiene
    (n-1) elementos. La lista nunca contiene elementos repetidos y nunca contiene la 
    ciudad de inicio y fin del circuito.

    Métodos:
    generar()
        Construye aleatoriamente una lista que representa una posible solución al problema.

    fcosto(sol)
        Calcula el costo asociado con una solución dada.

    vecino(sol)
        Calcula una solución vecina a partir de una solución dada.

    validar(sol)
        Valida que la solución dada cumple con los requisitos del problema.

    texto(sol)
        Construye una representación en hilera legible por humanos de la solución
        con el fin de reportar resultados al usuario final.
    """

    __matriz_ady = []
    __ciudades_números = {}
    __números_ciudades = []
    __ciudad_inicio = -1
    __n = -1

    def __init__(self, ciudades_rutacsv, ciudad_inicio):
        """Construye un objeto de modelo de dominio para una instancia
        específica del problema del vendedor viajero.

        Entradas:
        ciudades_rutacsv (str)
            Ruta al archivo csv que contiene la matriz de pesos entre las ciudades
            para las que se quiere resolver el problema del vendedor viajero.

        ciudad_inicio (str)
            Nombre de la ciudad que será el inicio y fin del circuito a calcular.

        Salidas:
            Una instancia de DominioTSP correctamente inicializada.
        """

        with open(ciudades_rutacsv, 'r') as archivo_csv:
            lector = csv.reader(archivo_csv, delimiter=',')
            primera_fila = True
            número_ciudad = 0
            for fila in lector:
                if primera_fila:
                    self.__números_ciudades = fila[1:]
                    primera_fila = False
                else:
                    ciudad = fila[0]
                    if ciudad == ciudad_inicio:
                        self.__ciudad_inicio = número_ciudad

                    self.__ciudades_números[ciudad] = número_ciudad
                    número_ciudad += 1

                    self.__matriz_ady.append([float(w) for w in fila[1:]])

            self.__n = len(self.__números_ciudades)

    def validar(self, sol):
        """Valida que la solución dada cumple con los requisitos del problema.

        Si n es el número de ciudades en el grafo, la solución debe:
        - Tener tamaño (n-1)
        - Contener sólo números enteros menores que n (las ciudades se numeran de 0 a (n-1))
        - No contener elementos repetidos
        - No contener la ciudad de inicio/fin del circuito

        Entradas:
        sol (list)
            La solución a validar.

        Salidas:
        (bool) True si la solución es válida, False en cualquier otro caso
        """

        if len(sol) != (self.__n - 1):
            return False

        for ciudad in sol:
            if ciudad == self.__ciudad_inicio:
                return False

            if ciudad >= self.__n:
                return False

        if len(sol) != len(set(sol)):
            return False

        return True

    def texto(self, sol):
        """Construye una representación en hilera legible por humanos de la solución
        con el fin de reportar resultados al usuario final.

        La hilera cumple con el siguiente formato:
        Nombre ciudad inicio -> Nombre ciudad 1 -> ... -> Nombre ciudad n -> Nombre ciudad inicio

        Entradas:
        sol (list)
            Solución a representar como texto legible

        Salidas:
        (str) Hilera en el formato mencionado anteriormente.
        """

        inicio = self.__números_ciudades[self.__ciudad_inicio]
        return ' -> '.join([inicio] + [self.__números_ciudades[c] for c in sol] + [inicio])

    def generar(self):
        """Construye aleatoriamente una lista que representa una posible solución al problema.

        Entradas:
        ninguna

        Salidas:
        (list) Una lista que representa una solución válida para esta instancia del vendedor viajero
        """

        sol = [c for c in range(self.__n) if c != self.__ciudad_inicio]
        random.shuffle(sol)
        return sol

    def fcosto(self, sol):
        """Calcula el costo asociado con una solución dada.

        Entradas:
        sol (list)
            Solución cuyo costo se debe calcular

        Salidas:
        (float) valor del costo asociado con la solución
        """

        n = len(sol)
        costo = 0
        for i in range(n - 1):
            costo += self.__matriz_ady[sol[i]][sol[i+1]]

        costo += self.__matriz_ady[self.__ciudad_inicio][sol[0]]
        costo += self.__matriz_ady[sol[-1]][self.__ciudad_inicio]

        return costo

    def vecino(self, sol):
        """Calcula una solución vecina a partir de una solución dada.

        Una solución vecina comparte la mayor parte de su estructura con 
        la solución que la origina, aunque no son exactamente iguales. El 
        método transforma aleatoriamente algún aspecto de la solución
        original.

        Entradas:
        sol (list)
            Solución a partir de la cual se originará una nueva solución vecina

        Salidas:
        (list) Solución vecina
        """

        n = len(sol)
        vecino = sol.copy()

        a = random.randint(0, (n-1))
        b = a
        while b == a:
            b = random.randint(0, (n-1))

        vecino[a], vecino[b] = vecino[b], vecino[a]

        return vecino
