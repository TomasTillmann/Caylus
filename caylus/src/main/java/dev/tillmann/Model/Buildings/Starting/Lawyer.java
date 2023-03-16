package dev.tillmann.model.buildings.starting;

import dev.tillmann.caylus.cli.BuildingToOwnResponse;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Lawyer extends StartingBuilding {
    @Override
    public String name() {
        return "Lawyer";
    }

    @Override
    protected void activatePlayer(Player player) {
        player.spend(resourcesCost());
        BuildingToOwnResponse response = BuildingToOwnResponse.parse(player);
        response.building.setOwner(player);
    }

    private Resources resourcesCost() {
        return Resources.empty().addFabric(1);
    }
}