package dev.tillmann.model.buildings.starting;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Guild4 extends StartingBuilding {
    @Override
    public String name() {
        return "Guild4";
    }

    @Override
    protected void activatePlayer(Player player) {
        player.gain(resourcesGain());
        player.spend(resourcesCost());
    }

    @Override
    public String description() {
        return String.format("Place %s workers and one stone to get four workers.", workersCost()); 
    }

    private Resources resourcesGain() {
        return Resources.empty().addWorkers(3);
    }

    private Resources resourcesCost() {
        return Resources.empty().addStone(1);
    }
}