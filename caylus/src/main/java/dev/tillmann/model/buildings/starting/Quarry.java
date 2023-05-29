package dev.tillmann.model.buildings.starting;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Quarry extends StartingBuilding {
    @Override
    public String name() {
        return "Quarry";
    }

    @Override
    public String description() {
        return String.format("Place %s workers to get one stone.", workersCost()); 
    }

    @Override
    protected void activatePlayer(Player player) {
        player.gain(resourcesGain());
    }

    private Resources resourcesGain() {
        return Resources.empty().addStone(1);
    }
}