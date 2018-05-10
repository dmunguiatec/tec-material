package edu.tec.ic4700.queues;

/**
 * @author: diegomunguia
 */
public interface Queue<T> {
    void enqueue(T element);
    T dequeue();
    int getSize();
}
