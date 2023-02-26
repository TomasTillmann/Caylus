package dev.tillmann.Model;

public class Camp {
    private static final int WORKERS_TOTAL = 75;

    public int recruitmentWorkersCount;

    private int remainingWorkers = WORKERS_TOTAL;
    public int remainingWorkers() { return remainingWorkers; }
    public void addWorkers(int workers) { remainingWorkers += workers; }


    public Camp(int playersCount) {
        recruitmentWorkersCount = playersCount == 5 ? 2 : 3;
    }

    public int getWorkers(int count) {
        if(count < 0 || count > remainingWorkers) {
            throw new UnsupportedOperationException();
        }

        remainingWorkers -= count;
        return count;
    }
}