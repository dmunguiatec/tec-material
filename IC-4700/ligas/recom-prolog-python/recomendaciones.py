# -*- coding: utf-8 -*-
from pyswip import Prolog

def recomendar(usuario):
    prolog = Prolog()
    prolog.consult("películas.pl")

    consulta = f"recomendar({usuario}, Película)."

    print(f"Recomendaciones para {usuario}:")
    for result in prolog.query(consulta):
        print(" *", result["Película"])

if __name__ == "__main__":
    usuario = input("Nombre de usuario para generar recomendaciones: ").strip()
    recomendar(usuario)