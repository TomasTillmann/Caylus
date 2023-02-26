package dev.tillmann.Model.Buildings.Wooden;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Board;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;
import dev.tillmann.Model.Buildings.Stone.StoneBuilding;

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
    public Resources toBuildCost() {
        CLI.ResourcesResponse response = CLI.getOneResource();
        return Resources.empty().addWood(1).add(response.resources);
    }

    @Override
    public void constructionActivate(Player player) {
        CLI.StoneBuildingToBuildResponse response = CLI.getStoneBuildingToBuild(player);
        activate(player, response.stoneBuilding.toBuildCost(), response.stoneBuilding);
    }

    @Override
    public void setupActivate(Player player) {
        CLI.StoneBuildingToBuildResponse response = CLI.getStoneBuildingToBuild(player);
        activate(player, setupSideToBuildCost(response.stoneBuilding), response.stoneBuilding);
    }

    private void activate(Player player, Resources toSpend, StoneBuilding building) {
        building.setOwner(player);
        map.road().build(building);
        player.spend(toSpend);
        building.immidiateReward(player);
    }

    private Resources setupSideToBuildCost(StoneBuilding building) {
        Resources resources = building.toBuildCost();
        return resources.addStone(-resources.stone());
    }
}