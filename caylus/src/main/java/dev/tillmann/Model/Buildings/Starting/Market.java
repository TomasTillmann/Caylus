package dev.tillmann.model.buildings.starting;

import dev.tillmann.caylus.cli.ResourcesResponse;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Market extends StartingBuilding {
    @Override
    public String name() {
        return "Market";
    }

    @Override
    protected void activatePlayer(Player player) {
        player.spend(resourcesCost());
        ResourcesResponse response = ResourcesResponse.getOneResource(player);
        player.gain(response.resources);
    }

    @Override
    public String description() {
        return String.format("Place %s workers to get one food.", workersCost()); 
    }

    private Resources resourcesCost() {
        return Resources.empty().addWorkers(1);
    }
}