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
        if(!player.canSpend(resourcesCost())) {
            return;
        }

        player.spend(resourcesCost());
        BuildingToOwnResponse response = BuildingToOwnResponse.parse(player);
        response.building.setOwner(player);
    }

    @Override
    public String description() {
        return String.format("Place %s workers and one fabric to claim ownership of any building on the road provided that it has a yellow flag. This building will be transformed into a residence during the stwewardship phase.", workersCost()); 
    }

    private Resources resourcesCost() {
        return Resources.empty().addFabric(1);
    }
}