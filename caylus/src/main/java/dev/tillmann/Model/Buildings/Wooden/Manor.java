package dev.tillmann.Model.Buildings.Wooden;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Manor extends WoodenBuilding {

    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(4);
        player.getFavor();
    }

    @Override
    public Resources toBuildCost() {
        return Resources.empty().addWood(1).addFabric(1);
    }

    @Override
    public void constructionActivate(Player player) { }

    @Override
    public void setupActivate(Player player) { }
}