package edu.tec.ic4700.queues;

/**
 * @author: diegomunguia
 */
public interface Priorizable<T> {
    enum Priority {LOW, MED, HIGH}

    T getValue();
    Priority getPriority();
}
