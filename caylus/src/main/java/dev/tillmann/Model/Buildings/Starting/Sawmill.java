package dev.tillmann.model.buildings.starting;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Sawmill extends StartingBuilding {
    @Override
    public String name() {
        return "Sawmill";
    }

    @Override
    protected void activatePlayer(Player player) {
        player.gain(resourcesGain());
    }

    private Resources resourcesGain() {
        return Resources.empty().addWood(1);
    }
}