package dev.tillmann.Model.Buildings.Starting;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Sawmill extends StartingBuilding {
    @Override
    protected void activatePlayer(Player player) {
        player.gain(resourcesGain());
    }

    private Resources resourcesGain() {
        return Resources.empty().addWood(1);
    }
}