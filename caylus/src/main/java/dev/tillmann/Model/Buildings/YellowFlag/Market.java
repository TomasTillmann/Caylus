package dev.tillmann.Model.Buildings.YellowFlag;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Market extends YellowFlagBuilding {
    @Override
    protected void activatePlayer(Player player) {
        player.spend(resourcesCost());
        CLI.ResourcesResponse response = CLI.getOneResource();
        player.gain(response.resources);
    }

    private Resources resourcesCost() {
        return Resources.empty().addWorkers(1);
    }
}