package dev.tillmann.model.buildings.wooden;

import dev.tillmann.caylus.cli.ResourcesResponse;
import dev.tillmann.caylus.cli.StoneBuildingResponse;
import dev.tillmann.model.Board;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;
import dev.tillmann.model.buildings.stone.StoneBuilding;

public class Stonemason extends WoodenBuilding {
    private Board map;

    public Stonemason(Board map) {
        this.map = map;
    }

    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(4);
    }

    @Override
    public String description() {
        switch(side) {
            case Construction:
                return String.format("Place %s worker and spend one wood, food, wood, stone and fabric to build a stone building.", workersCost()); 
            case Setup:
                return String.format("Place %s worker and spend one wood, food and fabric to build a stone building.", workersCost()); 
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public Resources toBuildCost(Player player) {
        ResourcesResponse response = ResourcesResponse.getOneResource(player);
        return Resources.empty().addWood(1).add(response.resources);
    }

    @Override
    public void constructionActivate(Player player) {
        StoneBuildingResponse response = StoneBuildingResponse.getStoneBuildingToBuild(player);
        activate(player, response.stoneBuilding.toBuildCost(player), response.stoneBuilding);
    }

    @Override
    public void setupActivate(Player player) {
        StoneBuildingResponse response = StoneBuildingResponse.getStoneBuildingToBuild(player);
        activate(player, setupSideToBuildCost(player, response.stoneBuilding), response.stoneBuilding);
    }

    private void activate(Player player, Resources toSpend, StoneBuilding building) {
        building.setOwner(player);
        map.road().build(building);
        player.spend(toSpend);
        building.immidiateReward(player);
    }

    private Resources setupSideToBuildCost(Player player, StoneBuilding building) {
        Resources resources = building.toBuildCost(player);
        return resources.addStone(-resources.stone());
    }

    @Override
    public String name() {
        return "Stonemason";
    }
}