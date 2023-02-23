package dev.tillmann.Model.Buildings.Wooden;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Map;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;
import dev.tillmann.Model.Buildings.Stone.StoneBuilding;

public class Stonemason extends WoodenBuilding {
    private Map map;

    public Stonemason(Map map) {
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
        StoneBuilding building = response.stoneBuilding;

        building.owner = player;
        map.road().build(building);
        player.spend(building.toBuildCost());
        building.immidiateReward(player);
    }

    @Override
    public void setupActivate(Player player) {
        CLI.StoneBuildingToBuildResponse response = CLI.getStoneBuildingToBuild(player);
        StoneBuilding building = response.stoneBuilding;

        building.owner = player;
        map.road().build(building);
        player.spend(setupSideToBuildCost(building));
        building.immidiateReward(player);
    }

    private Resources setupSideToBuildCost(StoneBuilding building) {
        Resources resources = building.toBuildCost();
        return resources.addStone(-resources.stone());
    }
}