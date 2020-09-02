from multiprocessing import Process, Value, Lock

def contar(c, n):
    for i in range(n):
        c.value = c.value + 1

def contar_conc():
    c = Value('i', 0)
    n = 10000
    l = Lock()
    p1 = Process(target=contar, args=(c, n))
    p2 = Process(target=contar, args=(c, n))

    p1.start()
    p2.start()

    p1.join()
    p2.join()

    return c.value

print(contar_conc())

def contar_sync(c, n, lock):
    for i in range(n):
        with lock:
            c.value = c.value + 1

def contar_conc_lock():
    c = Value('i', 0)
    n = 10000
    l = Lock()
    p1 = Process(target=contar_sync, args=(c, n, l))
    p2 = Process(target=contar_sync, args=(c, n, l))

    p1.start()
    p2.start()

    p1.join()
    p2.join()

    return c.value

print(contar_conc_lock())