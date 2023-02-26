package dev.tillmann.Model.Buildings.Starting;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Lawyer extends StartingBuilding {
    @Override
    protected void activatePlayer(Player player) {
        player.spend(resourcesCost());
        CLI.BuildingToOwnResponse response = CLI.getBuildingToOwn(player);
        response.building.setOwner(player);
    }

    private Resources resourcesCost() {
        return Resources.empty().addFabric(1);
    }
}