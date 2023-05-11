package dev.tillmann.model.buildings.wooden;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Manor extends WoodenBuilding {

    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(4);
        player.getFavor();
    }

    @Override
    public Resources toBuildCost(Player player) {
        return Resources.empty().addWood(1).addFabric(1);
    }

    @Override
    public String description() {
        switch(side) {
            case Construction:
                return String.format("Place %s worker and gain one favor.\nCost: wood, fabric.", workersCost()); 
            case Setup:
                return String.format("Place %s worker and gain 2PP.", workersCost()); 
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public void constructionActivate(Player player) { }

    @Override
    public void setupActivate(Player player) { }

    @Override
    public String name() {
        return "Manor";
    }
}