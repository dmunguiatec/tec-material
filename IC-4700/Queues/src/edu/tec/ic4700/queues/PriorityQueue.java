package edu.tec.ic4700.queues;

/**
 * @author: diegomunguia
 */
public interface PriorityQueue<T> extends Queue<T> {
    void enqueue(T element, Priorizable.Priority priority);
}
