package dev.tillmann.Model.Buildings;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Fairground extends StartingBuilding {
    @Override
    protected void activatePlayer(Player player) {
        player.spend(resourcesCost());
        player.getFavor();
    }

    private Resources resourcesCost() {
        return Resources.empty().addWood(1).addWorkers(workersCost());
    }
}