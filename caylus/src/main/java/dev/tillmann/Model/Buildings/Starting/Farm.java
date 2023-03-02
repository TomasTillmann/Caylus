package dev.tillmann.model.buildings.starting;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Farm extends StartingBuilding {
    @Override
    public String name() {
        return "Farm";
    }

    @Override
    protected void activatePlayer(Player player) {
        player.gain(resourcesGain());
    }

    private Resources resourcesGain() {
        return Resources.empty().addFood(1);
    }
}