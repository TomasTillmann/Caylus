package dev.tillmann.Model.Buildings.Starting;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;
import dev.tillmann.Model.Buildings.YellowFlag.YellowFlagBuilding;

public class Lawyer extends StartingBuilding {
    @Override
    protected void activatePlayer(Player player) {
        player.spend(resourcesCost());
        CLI.BuildingToOwnResponse response = CLI.getBuildingToOwn(player);
        YellowFlagBuilding building = response.building;
        building.markAsToResidence();
    }

    private Resources resourcesCost() {
        return Resources.empty().addFabric(1);
    }
}