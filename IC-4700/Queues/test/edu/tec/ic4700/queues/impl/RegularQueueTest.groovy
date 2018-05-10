package edu.tec.ic4700.queues.impl

import edu.tec.ic4700.queues.Queue

/**
 * @author: diegomunguia
 */
class RegularQueueTest extends GroovyTestCase {

    void testEnqueue() {
        Queue<Integer> queue = new RegularQueue<>();
        queue.enqueue(1);
    }

    void testDequeueEmptyQueue() {
        Queue<Integer> queue = new RegularQueue<>();
        Integer result = queue.dequeue();

        assert(result == null);
    }

    void testDequeueLoadedQueue() {
        Queue<Integer> queue = new RegularQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        Integer result = queue.dequeue();

        assert(result == 1);
    }

    void testGetSizeEmptyQueue() {
        Queue<Integer> queue = new RegularQueue<>();
        assert(queue.getSize() == 0);
    }

    void testGetSizeLoadedQueue() {
        Queue<Integer> queue = new RegularQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assert(queue.getSize() == 3);
    }
}
