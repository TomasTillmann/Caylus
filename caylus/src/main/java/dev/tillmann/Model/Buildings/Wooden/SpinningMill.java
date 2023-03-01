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
    public Resources toBuildCost() {
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
}