package edu.tec.ic4700.queues.impl;

import edu.tec.ic4700.queues.AbstractQueue;

import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author: diegomunguia
 */
public class RegularQueue<T> extends AbstractQueue<T> {

    @Override
    public void enqueue(T element) {
        queue.add(element);
    }

    @Override
    public T dequeue() {
        return queue.isEmpty() ? null : (T) queue.remove(0);
    }
}
