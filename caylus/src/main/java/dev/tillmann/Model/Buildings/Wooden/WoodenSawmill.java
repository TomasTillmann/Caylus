package dev.tillmann.Model.Buildings.Wooden;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class WoodenSawmill extends WoodenBuilding {
    private WoodenFarm farm;

    public WoodenSawmill() {
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
        player.gain(Resources.empty().addFood(2));
    }

    @Override
    public void setupActivate(Player player) {
        farm.activate();
    }
}