package dev.tillmann.model.buildings.wooden;

import dev.tillmann.caylus.cli.ResourcesResponse;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class WoodenMarket extends WoodenBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(3);
    }

    @Override
    public Resources toBuildCost(Player player) {
        return Resources.empty().addWood(1).addFood(1);
    }

    @Override
    public String description() {
        return String.format("Place %s worker and get food, wood, stone or fabric.\nCost: wood, food.", workersCost()); 
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        ResourcesResponse response = ResourcesResponse.gainOneResource(player);
        player.gain(response.resources);
    }

    @Override
    public String name() {
        return "Wooden market";
    }
}