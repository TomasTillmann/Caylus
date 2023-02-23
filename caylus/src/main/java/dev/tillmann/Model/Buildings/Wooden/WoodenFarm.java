package dev.tillmann.Model.Buildings.Wooden;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class WoodenFarm extends WoodenBuilding {
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
        player.awardPrestigePoints(1);
        player.gain(Resources.empty().addFood(1));
    }
}