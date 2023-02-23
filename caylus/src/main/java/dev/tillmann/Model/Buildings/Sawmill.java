package dev.tillmann.Model.Buildings;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Sawmill extends YellowFlagBuilding {
    @Override
    protected void activatePlayer(Player player) {
        player.spend(resourcesCost());
        player.gain(resourcesGain());
    }

    private Resources resourcesCost() {
        return Resources.empty().addWorkers(workersCost());
    }

    private Resources resourcesGain() {
        return Resources.empty().addWood(1);
    }
}