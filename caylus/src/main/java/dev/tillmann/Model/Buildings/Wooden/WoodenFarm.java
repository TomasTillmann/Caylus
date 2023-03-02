package dev.tillmann.model.buildings.wooden;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class WoodenFarm extends WoodenBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(2);
    }

    @Override
    public Resources toBuildCost(Player player) {
        return Resources.empty().addWood(1).addFood(1);
    }

    @Override
    public void constructionActivate(Player player) {
        player.gain(Resources.empty().addFood(2));
    }

    @Override
    public void setupActivate(Player player) {
        player.awardPrestigePoints(1);
        player.gain(Resources.empty().addFood(1));
    }

    @Override
    public String name() {
        return "Wooden farm";
    }
}