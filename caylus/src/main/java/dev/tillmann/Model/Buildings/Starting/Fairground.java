package dev.tillmann.model.buildings.starting;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Fairground extends StartingBuilding {
    @Override
    public String name() {
        return "Fairground";
    }

    @Override
    protected void activatePlayer(Player player) {
        player.spend(resourcesCost());
        player.getFavor();
    }

    private Resources resourcesCost() {
        return Resources.empty().addWood(1);
    }
}