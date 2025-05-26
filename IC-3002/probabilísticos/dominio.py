from abc import ABC
from abc import abstractmethod

class Dominio(ABC):
    """
    Representa el objeto de dominio que conoce los detalles de implementación y modelamiento
    de algún problema específico para ser resuelto con algoritmos probabilísticos.

    Métodos:
    generar()
        Construye aleatoriamente una estructura de datos que representa una posible 
        solución al problema.

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

    @abstractmethod
    def generar(self):
        """Construye una estructura de datos que representa una posible solución al problema.
        
        Entradas:
        ninguna

        Salidas:
        Una estructura de datos que representa una posible solución válida al problema
        """

        pass

    @abstractmethod
    def fcosto(self, sol):
        """Calcula el costo asociado con una solución dada.
        
        Entradas:
        sol (estructura de datos)
            Solución cuyo costo se debe calcular
        
        Salidas:
        (float) valor del costo asociado con la solución
        """

        pass

    @abstractmethod
    def vecino(self, sol):
        """Calcula una solución vecina a partir de una solución dada.

        Una solución vecina comparte la mayor parte de su estructura con 
        la solución que la origina, aunque no son exactamente iguales. El 
        método transforma aleatoriamente algún aspecto de la solución
        original.

        Entradas:
        sol (estructura de datos)
            Solución a partir de la cual se calculará una nueva solución vecina.

        Salidas:
        (estructura de datos) nueva solución construida con base en la solución de la entrada.
        """

        pass

    @abstractmethod
    def validar(self, sol): 
        """Valida que la solución dada cumpla con todos los requerimientos del problema.

        Entradas:
        sol (estructura de datos)
            La solución a validar
        
        Salidas:
        (bool) True si la solución es valida, False en cualquier otro caso.
        """

        pass

    @abstractmethod
    def texto(self, sol):
        """Construye una representación en hilera legible por humanos de la solución
        con el fin de reportar resultados al usuario final.

        Entradas:
        sol (estructura de datos)
            La solución a transformar en texto legible
        
        Salidas:
        (str) El texto legible que representa a la solución.
        """

        pass