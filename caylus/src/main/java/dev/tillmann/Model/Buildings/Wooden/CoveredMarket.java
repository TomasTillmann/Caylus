package dev.tillmann.model.buildings.wooden;

import dev.tillmann.caylus.cli.ResourcesResponse;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class CoveredMarket extends WoodenBuilding {

    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(3);
    }

    @Override
    public Resources toBuildCost(Player player) {
        ResourcesResponse response = ResourcesResponse.getOneResource(player);
        return response.resources.addWood(1);
    }

    @Override
    public String description() {
        switch(side) {
            case Construction:
                return String.format("Place %s worker and take three workers.", workersCost()); 
            case Setup:
                return String.format("Place %s worker and take two workers and gain 1PP.", workersCost()); 
            default:
                throw new UnsupportedOperationException();
        }
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

    @Override
    public String name() {
        return "Covered market";
    }
}