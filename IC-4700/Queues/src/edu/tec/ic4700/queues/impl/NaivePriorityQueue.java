package edu.tec.ic4700.queues.impl;

import edu.tec.ic4700.queues.AbstractQueue;
import edu.tec.ic4700.queues.PriorityQueue;
import edu.tec.ic4700.queues.Priorizable;

import java.util.ArrayList;

/**
 * @author: diegomunguia
 */
public class NaivePriorityQueue<T> extends AbstractQueue<T> implements PriorityQueue<T> {

    @Override
    public void enqueue(T element, Priorizable.Priority priority) {
        int i = 0;
        while (i < queue.size() && ((Priorizable)queue.get(i)).getPriority().compareTo(priority) >= 0) {
            i++;
        }

        Priorizable<T> prioritizedElement = new Priorizable<T>() {
            public T getValue() { return element; }
            public Priority getPriority() { return priority; }
        };

        if (i == queue.size()) {
            queue.add(prioritizedElement);
        } else {
            queue.add(i, prioritizedElement);
        }
    }

    @Override
    public void enqueue(T element) {
        enqueue(element, Priorizable.Priority.MED);
    }

    @Override
    public T dequeue() {
        return queue.isEmpty() ? null : ((Priorizable<T>) queue.remove(0)).getValue();
    }
}
