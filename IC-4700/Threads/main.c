#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define NUM_THREADS 3

void* do_work(void* arg) {
    int thread_id = *((int*) arg);
    for (int i = 0; i < 100000; i++) {
        printf("Thread %d: %d\n", thread_id, i);
    }

    return NULL;
}

void main() {
    int ids[NUM_THREADS];
    pthread_t threads[NUM_THREADS];

    for(int i = 0; i < NUM_THREADS; i++) {
        ids[i] = i;
        pthread_create(&threads[i], NULL, do_work, &ids[i]);
    }

    for(int i = 0; i < NUM_THREADS; i++) {
        pthread_join(threads[i], NULL);
    }
}
