package dev.tillmann.model.buildings.wooden;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class WoodenQuarry extends WoodenBuilding {
    private WoodenFarm farm;

    public WoodenQuarry() {
        farm = new WoodenFarm();
        farm.side = Side.Setup;
    }

    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(2);
    }

    @Override
    public Resources toBuildCost() {
        return Resources.empty().addWood(1).addFood(1);
    }

    @Override
    public void constructionActivate(Player player) {
        player.gain(Resources.empty().addStone(2));
    }

    @Override
    public void setupActivate(Player player) {
        farm.activate();
    }
}