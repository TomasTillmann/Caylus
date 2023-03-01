package dev.tillmann.model;

public class Camp {
    private static final int WORKERS_TOTAL = 75;

    public int recruitmentWorkersCount;

    private int remainingWorkers = WORKERS_TOTAL;
    public int remainingWorkers() { return remainingWorkers; }
    public void returnWorkers(int workers) { remainingWorkers += workers; }


    public Camp(int playersCount) {
        recruitmentWorkersCount = playersCount == 5 ? 2 : 3;
        Resources.provideCamp(this);
    }

    public int getWorkers(int count) {
        if(remainingWorkers < count) { throw new UnsupportedOperationException(); }
        remainingWorkers = remainingWorkers - count;
        return count;
    }
}