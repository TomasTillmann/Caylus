package dev.tillmann.Model;

public final class Resources {
    private int food;
    public int food() { return food; }

    private int wood;
    public int wood() { return wood; }

    private int stone;
    public int stone() { return stone; }

    private int fabric;
    public int fabric() { return fabric; }

    private int gold;
    public int gold() { return gold; }

    private int workers;
    public int workers() { return workers; }

    private Resources() {}

    public static Resources empty() {
        return new Resources();
    }

    public static Resources with(int food, int wood, int stone, int fabric, int gold, int workers) {
        Resources resources = Resources.empty();
        resources.food = food;
        resources.wood = wood;
        resources.stone = stone;
        resources.fabric = fabric;
        resources.gold = gold;
        resources.workers = workers;

        return resources;
    }

    public Resources addFood(int count) {
        food += count;
        return this;
    }

    public Resources addWood(int count) {
        wood += count;
        return this;
    }

    public Resources addStone(int count) {
        stone += count;
        return this;
    }

    public Resources addFabric(int count) {
        fabric += count;
        return this;
    }

    public Resources withGold(int count) {
        gold += count;
        return this;
    }

    public Resources addWorkers(int count) {
        workers += count;
        return this;
    }

    public Resources add(Resources resources) {
        Resources newResources = Resources.empty();
        newResources.food += resources.food;
        newResources.wood += resources.wood;
        newResources.stone += resources.stone;
        newResources.fabric += resources.fabric;
        newResources.gold += resources.gold;
        newResources.workers += resources.workers;

        return newResources;
    }

    public Resources sub(Resources resources) {
        Resources newResources = Resources.empty();
        newResources.food -= resources.food;
        newResources.wood -= resources.wood;
        newResources.stone -= resources.stone;
        newResources.fabric -= resources.fabric;
        newResources.gold -= resources.gold;
        newResources.workers -= resources.workers;

        return newResources;
    }
}