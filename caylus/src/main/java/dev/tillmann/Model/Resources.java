package dev.tillmann.Model;

public final class Resources {
    public int food;
    public int wood;
    public int stone;
    public int fabric;
    public int gold;
    public int workers;

    public static Resources Free() {
        return new Resources();
    }

    public Resources WithWorkers(int count) {
        workers = count;
        return this;
    }
}