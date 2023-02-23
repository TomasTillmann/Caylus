package dev.tillmann.Model.Buildings;

import dev.tillmann.Caylus.*;
import dev.tillmann.Model.Map;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Carpenter extends StartingBuilding {
    private Map map;

    public Carpenter(Map map) {
        this.map = map;
    }

    @Override
    protected void activatePlayer(Player player) {
        player.spend(resourcesCost());

        CLI.WoodenBuildingToBuildResponse response = CLI.getWoodenBuildingToBuild(player);
        WoodenBuilding building = response.woodenBuilding;

        building.owner = player;
        map.road().build(building);

        player.spend(building.resourcesCost());
        building.immidiateReward(player);
    }

    private Resources resourcesCost() {
        return Resources.empty().addWorkers(workersCost());
    }
}