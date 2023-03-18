package dev.tillmann.model.buildings.starting;

import dev.tillmann.caylus.cli.WoodenBuildingResponse;
import dev.tillmann.model.Board;
import dev.tillmann.model.Player;
import dev.tillmann.model.buildings.wooden.WoodenBuilding;

public class Carpenter extends StartingBuilding {
    private Board map;

    public Carpenter(Board map) {
        this.map = map;
    }

    @Override
    public String name() {
        return "Carpenter";
    }

    @Override
    public String description() {
        return String.format("Place %s workers and build a wooden building.", workersCost()); 
    }

    @Override
    protected void activatePlayer(Player player) {
        WoodenBuildingResponse response = WoodenBuildingResponse.parse(player);
        WoodenBuilding building = response.woodenBuilding;

        building.setOwner(player);
        map.road().build(building);

        player.spend(building.toBuildCost(player));
        building.immidiateReward(player);
    }
}