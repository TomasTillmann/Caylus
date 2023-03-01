package dev.tillmann.model.buildings.starting;

import dev.tillmann.caylus.cli.CLI;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Lawyer extends StartingBuilding {
    @Override
    protected void activatePlayer(Player player) {
        player.spend(resourcesCost());
        CLI.BuildingToOwnResponse response = CLI.instance().getBuildingToOwn(player);
        response.building.setOwner(player);
    }

    private Resources resourcesCost() {
        return Resources.empty().addFabric(1);
    }
}