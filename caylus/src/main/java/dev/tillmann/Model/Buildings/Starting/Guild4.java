package dev.tillmann.Model.Buildings.Starting;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Guild4 extends StartingBuilding {

    @Override
    protected void activatePlayer(Player player) {
        player.gain(resourcesGain());
        player.spend(resourcesCost());
    }

    private Resources resourcesGain() {
        return Resources.empty().addWorkers(3);
    }

    private Resources resourcesCost() {
        return Resources.empty().addStone(1);
    }
}