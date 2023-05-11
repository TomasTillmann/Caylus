package dev.tillmann.model.buildings.starting;

import dev.tillmann.caylus.cli.ResourcesResponse;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Guild3 extends StartingBuilding {
    @Override
    public String name() {
        return "Guild3";
    }

    @Override
    public String description() {
        return String.format("Place %s workers and get one food, wood ,stone or fabric to get three workers.", workersCost()); 
    }

    @Override
    protected void activatePlayer(Player player) {
        ResourcesResponse response = ResourcesResponse.spendOneResource(player);
        player.gain(resourcesGain());
        player.spend(response.resources);
    }

    private Resources resourcesGain() {
        return Resources.empty().addWorkers(3);
    }
}