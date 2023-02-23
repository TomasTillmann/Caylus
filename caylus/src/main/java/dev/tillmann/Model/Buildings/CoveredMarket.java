package dev.tillmann.Model.Buildings;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class CoveredMarket extends WoodenBuilding {

    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(3);
    }

    @Override
    public Resources resourcesCost() {
        CLI.ResourcesResponse response = CLI.getWoodenBuildingResources(this);
        return response.resources;
    }

    @Override
    public void constructionActivate(Player player) {
        // todo: zjistit jak se prechazi z construction side to setup side
        player.gain(Resources.empty().addWorkers(3));

        side = Side.Setup;
    }

    @Override
    public void setupActivate(Player player) {
        player.spend(Resources.empty().addWorkers(workersCost()));
        player.gain(Resources.empty().addWorkers(2));
        player.awardPrestigePoints(1);
    }
}