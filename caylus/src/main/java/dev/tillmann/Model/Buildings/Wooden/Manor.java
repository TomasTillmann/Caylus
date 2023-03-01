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
    public Resources toBuildCost() {
        return Resources.empty().addWood(1).addFabric(1);
    }

    @Override
    public void constructionActivate(Player player) { }

    @Override
    public void setupActivate(Player player) { }
}