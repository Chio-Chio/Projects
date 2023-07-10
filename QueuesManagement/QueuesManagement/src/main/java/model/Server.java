package model;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private BlockingDeque<Task> queue;
    private int id;
    //private int availableSpace;
    private AtomicInteger waitingPeriod;

    public Server(int i) {
        this.id = i;
        this.queue = new LinkedBlockingDeque<Task>(100000);

        this.waitingPeriod = new AtomicInteger(0);
    }


    public Server(BlockingDeque<Task> queue, AtomicInteger waitingPeriod) {
        this.queue = queue;
        this.waitingPeriod = waitingPeriod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BlockingDeque<Task> getQueue() {
        return queue;
    }

    public void setQueue(BlockingDeque<Task> queue) {
        this.queue = queue;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public boolean isEmpty() {
        if(queue.isEmpty())
            return true;
        return false;
    }

    public String printServer() {
        String aux = "Queue " + this.id + ": ";
        BlockingDeque<Task> copy = new LinkedBlockingDeque<>(queue);
        while (!copy.isEmpty()){
            aux = aux + copy.getFirst().printTask() + " ";
            copy.removeFirst();
        }
        return aux;
    }
}
