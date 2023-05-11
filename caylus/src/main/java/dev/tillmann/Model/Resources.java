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

    private Resources() { }

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

    /*
     * Check if the resources can be taken.
     */
    public boolean canTake() {
        return wood <= remainingWood && food <= remainingFood && fabric <= remainingFabric && stone <= remainingStone && gold <= remainingGold && workers <= camp.remainingWorkers();
    }

    /*
     * Takes the resources and returns true. Or returns false, meaning the resources can't be taken and doesn't spend the resources obviously.
     */
    public static boolean take(Resources resources) {
        if(!resources.canTake()) {
            return false;
        }

        remainingFood -= resources.food();
        remainingWood -= resources.wood();
        remainingStone -= resources.stone();
        remainingFabric -= resources.fabric();
        remainingGold -= resources.gold();
        camp.getWorkers(resources.workers());
        return true;
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

    public Resources addGold(int count) {
        gold += count;
        return this;
    }

    public Resources addWorkers(int count) {
        workers += count;
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