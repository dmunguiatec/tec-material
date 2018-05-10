package edu.tec.ic4700.queues;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: diegomunguia
 */
public abstract class AbstractQueue<T> implements Queue<T> {

    protected List queue = new ArrayList();

    @Override
    public int getSize() {
        return queue.size();
    }
}
