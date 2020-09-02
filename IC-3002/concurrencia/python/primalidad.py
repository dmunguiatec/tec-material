from timeit import default_timer as timer
from multiprocessing import Process
from os import getpid

def es_primo_secuencial(n):
    for i in range(2, n):
        if n % i == 0:
            return False
    return True

start = timer()
print(f'¿es primo 433494437? {es_primo_secuencial(433494437)}') # 1073676287
end = timer()
print(f'Secuencial: {end - start} seg')

def es_primo_concurrente(n):
    
    def aux(n, inicio, fin):
        print(f'pid-{getpid()} Probando desde {inicio} hasta {fin}')

        for i in range(inicio, fin):
            if n % i == 0:
                print(f'pid-{getpid()} False')
                return
        print(f'pid-{getpid()} True')

    tam_lote = (n - 2) // 4
    inicio = 2
    fin = inicio + tam_lote
    p1 = Process(target=aux, args=(n, inicio, fin))
    p1.start()
        
    inicio = fin
    fin = inicio + tam_lote
    p2 = Process(target=aux, args=(n, inicio, fin))
    p2.start()

    inicio = fin
    fin = inicio + tam_lote
    p3 = Process(target=aux, args=(n, inicio, fin))
    p3.start()

    inicio = fin
    fin = n
    p4 = Process(target=aux, args=(n, inicio, fin))
    p4.start()

    p1.join()
    p2.join()
    p3.join()
    p4.join()

start = timer()
es_primo_concurrente(433494437)
end = timer()
print(f'Concurrente: {end - start} seg')
    
from multiprocessing import Value

def es_primo_concurrente_memc(n):
    
    def aux(n, inicio, fin, res):
        print(f'pid-{getpid()} Probando desde {inicio} hasta {fin}')

        for i in range(inicio, fin):
            if n % i == 0:
                return
        res.value = True

    tam_lote = (n - 2) // 4
    inicio = 2
    fin = inicio + tam_lote
    r1 = Value('b', False)
    p1 = Process(target=aux, args=(n, inicio, fin, r1))
    p1.start()
        
    inicio = fin
    fin = inicio + tam_lote
    r2 = Value('b', False)
    p2 = Process(target=aux, args=(n, inicio, fin, r2))
    p2.start()

    inicio = fin
    fin = inicio + tam_lote
    r3 = Value('b', False)
    p3 = Process(target=aux, args=(n, inicio, fin, r3))
    p3.start()

    inicio = fin
    fin = n
    r4 = Value('b', False)
    p4 = Process(target=aux, args=(n, inicio, fin, r4))
    p4.start()

    p1.join()
    p2.join()
    p3.join()
    p4.join()

    return r1.value and r2.value and r3.value and r4.value

start = timer()
print(f'¿es primo 433494437? {es_primo_concurrente_memc(433494437)}')
end = timer()
print(f'Concurrente mem compartida: {end - start} seg')
