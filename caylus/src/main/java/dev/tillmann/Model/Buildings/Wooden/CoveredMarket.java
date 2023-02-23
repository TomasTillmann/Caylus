package dev.tillmann.Model.Buildings.Wooden;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class CoveredMarket extends WoodenBuilding {

    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(3);
    }

    @Override
    public Resources toBuildCost() {
        CLI.ResourcesResponse response = CLI.getWoodenBuildingResources(this);
        return response.resources;
    }

    @Override
    public void constructionActivate(Player player) {
        player.gain(Resources.empty().addWorkers(3));
    }

    @Override
    public void setupActivate(Player player) {
        player.gain(Resources.empty().addWorkers(2));
        player.awardPrestigePoints(1);
    }
}