package dev.tillmann.model.buildings.stone;

import dev.tillmann.caylus.cli.ResourcesResponse;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class StoneGuild extends StoneBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(4);
    }

    @Override
    public Resources toBuildCost(Player player) {
        ResourcesResponse response = ResourcesResponse.spendOneResource(player);
        return Resources.empty().addStone(1).add(response.resources);
    }

    @Override
    public String description() {
        return String.format("Place %s workers and spend one food, wood, stone or fabric to get five workers.\nCost: stone, wood/stone/fabric/food.", workersCost()); 
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        ResourcesResponse response = ResourcesResponse.spendOneResource(player);
        player.spend(response.resources);
        player.gain(Resources.empty().addWorkers(5));
    }

    @Override
    public String name() {
        return "Stone guild";
    }
}