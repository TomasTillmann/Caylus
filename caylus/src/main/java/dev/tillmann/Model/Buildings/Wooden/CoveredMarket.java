package dev.tillmann.model.buildings.wooden;

import dev.tillmann.caylus.cli.CLI;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class CoveredMarket extends WoodenBuilding {

    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(3);
    }

    @Override
    public Resources toBuildCost() {
        CLI.ResourcesResponse response = CLI.instance().getWoodenBuildingResources(this);
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