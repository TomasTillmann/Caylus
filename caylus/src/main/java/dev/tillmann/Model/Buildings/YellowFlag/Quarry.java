package dev.tillmann.Model.Buildings.YellowFlag;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Quarry extends YellowFlagBuilding {
    @Override
    protected void activatePlayer(Player player) {
        player.spend(resourcesCost());
        player.gain(resourcesGain());
    }

    private Resources resourcesCost() {
        return Resources.empty().addWorkers(workersCost());
    }

    private Resources resourcesGain() {
        return Resources.empty().addStone(1);
    }
}