package dev.tillmann.Model.Buildings.Starting;

import dev.tillmann.Caylus.*;
import dev.tillmann.Model.Board;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Buildings.Wooden.WoodenBuilding;

public class Carpenter extends StartingBuilding {
    private Board map;

    public Carpenter(Board map) {
        this.map = map;
    }

    @Override
    protected void activatePlayer(Player player) {
        CLI.WoodenBuildingToBuildResponse response = CLI.getWoodenBuildingToBuild(player);
        WoodenBuilding building = response.woodenBuilding;

        building.owner = player;
        map.road().build(building);

        player.spend(building.toBuildCost());
        building.immidiateReward(player);
    }
}