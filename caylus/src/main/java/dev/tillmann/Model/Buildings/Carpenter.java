package dev.tillmann.Model.Buildings;

import dev.tillmann.Caylus.*;
import dev.tillmann.Model.Resources;

public class Carpenter extends StartingBuilding {
    @Override
    public void activate(Map map) {
        for(Player player : plannedPlayers) {
            CLI.WoodenBuildingToBuild response = CLI.getWoodenBuildingToBuild(player);
            WoodenBuilding building = response.woodenBuilding;
            building.owner = player;
            map.road().build(building);
            player.updateResources(building.resourcesCost(), );
        }
    }

    @Override
    public Resources resourcesCost() {
        return Resources.Free().WithWorkers(1);
    }
}