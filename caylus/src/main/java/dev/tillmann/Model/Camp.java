package dev.tillmann.Model;

public class Camp {
    private static final int WORKERS_TOTAL = 75;

    private int remainingWorkers = WORKERS_TOTAL;
    public int remainingWorkers() { return remainingWorkers; }
    public void addWorkers(int workers) { remainingWorkers += workers; }

    public int getWorkers(int count) {
        if(count < 0 || count > remainingWorkers) {
            throw new UnsupportedOperationException();
        }

        remainingWorkers -= count;
        return count;
    }
}