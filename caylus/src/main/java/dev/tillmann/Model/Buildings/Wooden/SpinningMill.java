package dev.tillmann.model.buildings.wooden;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class SpinningMill extends WoodenBuilding {
    private WoodenFarm farm;

    public SpinningMill() {
        farm = new WoodenFarm();
        farm.side = Side.Setup;
    }

    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(2);
    }

    @Override
    public String description() {
        switch(side) {
            case Construction:
                return String.format("Place %s worker and two fabric to get two woods.\nCost: 2 woods.", workersCost()); 
            case Setup:
                return farm.description();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public Resources toBuildCost(Player player) {
        return Resources.empty().addWood(2);
    }

    @Override
    public void constructionActivate(Player player) {
        player.gain(Resources.empty().addFabric(2));
    }

    @Override
    public void setupActivate(Player player) {
        farm.activate();
    }

    @Override
    public String name() {
        return "Spinning mill";
    }
}