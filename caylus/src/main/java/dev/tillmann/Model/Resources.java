package dev.tillmann.model;

public final class Resources implements Visualizable {
    private static Camp camp;

    private static int remainingFood = 20;
    public static int remainingFood() { return remainingFood; } 

    private static int remainingWood = 20;
    public static int remainingWood() { return remainingWood; }

    private static int remainingStone = 20;
    public static int remainingStone() { return remainingStone; }

    private static int remainingFabric = 15;
    public static int remainingFabric() { return remainingFabric; }

    private static int remainingGold = 15;
    public static int remainingGold() { return remainingGold; }

    public static int remainingWorkers() { return camp.remainingWorkers(); }

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

    public static void returnBack(Resources resources) {
        remainingWood += resources.wood();
        remainingFood += resources.food();
        remainingFabric += resources.fabric();
        remainingStone += resources.stone();
        remainingGold += resources.gold();
        camp.returnWorkers(resources.workers());
    }

    @Override
    public String visualize() {
        String visualization = "";
        visualization += "wood: " + wood() + "\n";
        visualization += "food: " + food() + "\n";
        visualization += "fabric: " + fabric() + "\n";
        visualization += "stone: " + stone() + "\n";
        visualization += "gold: " + gold() + "\n";
        visualization += "workers: " + workers() + "\n";

        return visualization;
    }

    public Resources addFood(int count) {
        if(remainingFood < count) { throw new UnsupportedOperationException(); }
        food += count;
        remainingFood = remainingFood - count;

        return this;
    }

    public Resources addWood(int count) {
        if(remainingWood < count) { throw new UnsupportedOperationException(); }
        wood += count;
        remainingWood = remainingWood - count;

        return this;
    }

    public Resources addStone(int count) {
        if(remainingStone < count) { throw new UnsupportedOperationException(); }
        stone += count;
        remainingStone = remainingStone - count;

        return this;
    }

    public Resources addFabric(int count) {
        if(remainingFabric < count) { throw new UnsupportedOperationException(); }
        fabric += count;
        remainingFabric = remainingFabric - count;

        return this;
    }

    public Resources addGold(int count) {
        if(remainingGold < count) { throw new UnsupportedOperationException(); }
        gold += count;
        remainingGold = remainingGold - count;

        return this;
    }

    public Resources addWorkers(int count) {
        workers += camp.getWorkers(count);
        return this;
    }

    public Resources add(Resources resources) {
        Resources newResources = Resources.empty();
        newResources.food = this.food + resources.food;
        newResources.wood = this.wood + resources.wood;
        newResources.stone = this.stone + resources.stone;
        newResources.fabric = this.fabric + resources.fabric;
        newResources.gold = this.gold + resources.gold;
        newResources.workers = this.workers + resources.workers;

        return newResources;
    }

    public Resources sub(Resources resources) {
        Resources newResources = Resources.empty();
        newResources.food = this.food - resources.food;
        newResources.wood = this.wood - resources.wood;
        newResources.stone = this.stone - resources.stone;
        newResources.fabric = this.fabric - resources.fabric;
        newResources.gold = this.gold - resources.gold;
        newResources.workers = this.workers - resources.workers;

        return newResources;
    }

    public static void provideCamp(Camp camp) {
        Resources.camp = camp;
    }
}