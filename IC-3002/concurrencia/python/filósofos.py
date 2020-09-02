from multiprocessing import Process, Lock
from random import randrange
import time

ID = 0
LOCK = 1

def cenar(id, izq, der):
    while True:
        print(f'[{id}] Pensando')
        time.sleep(randrange(2))

        with izq[LOCK]:
            with der[LOCK]:
                print(f'[{id}] Comiendo con {izq[ID]} y {der[ID]}')
                time.sleep(randrange(2))

def con_deadlock():
    n_comensales = 5
    palillos = [(i, Lock()) for i in range(n_comensales)]
    for i in range(n_comensales):
        p = Process(target=cenar, args=(i, palillos[i], palillos[(i + 1) % n_comensales]))
        p.start()


def cenar_mejorado(id, izq, der):
    if izq[ID] < der[ID]:
        primero = izq
        segundo = der
    else:
        primero = der
        segundo = izq

    while True:
        print(f'[{id}] Pensando')
        time.sleep(randrange(2))

        with primero[LOCK]:
            with segundo[LOCK]:
                print(f'[{id}] Comiendo con {primero[ID]} y {segundo[ID]}')
                time.sleep(randrange(2))


def sin_deadlock():
    n_comensales = 5
    palillos = [(i, Lock()) for i in range(n_comensales)]
    for i in range(n_comensales):
        p = Process(target=cenar_mejorado, args=(i, palillos[i], palillos[(i + 1) % n_comensales]))
        p.start()

sin_deadlock()