package dev.tillmann.model;

public class Camp {
    public int recruitmentWorkersCount;

    private int remainingWorkers = 75;
    public int remainingWorkers() { return remainingWorkers; }
    public void returnWorkers(int workers) { remainingWorkers += workers; }

    public Camp(int playersCount) {
        recruitmentWorkersCount = playersCount == 5 ? 2 : 3;
        Resources.provideCamp(this);
    }

    public int getWorkers(int count) {
        if(remainingWorkers < count) { throw new UnsupportedOperationException(); }
        remainingWorkers -= count;
        return count;
    }
}