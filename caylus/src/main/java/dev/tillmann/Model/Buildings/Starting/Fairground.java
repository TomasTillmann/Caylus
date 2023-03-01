package dev.tillmann.model.buildings.starting;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Fairground extends StartingBuilding {
    @Override
    protected void activatePlayer(Player player) {
        player.spend(resourcesCost());
        player.getFavor();
    }

    private Resources resourcesCost() {
        return Resources.empty().addWood(1);
    }
}