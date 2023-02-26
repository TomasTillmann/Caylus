package dev.tillmann.Model.Buildings.Starting;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Guild3 extends StartingBuilding {
    @Override
    protected void activatePlayer(Player player) {
        CLI.ResourcesResponse response = CLI.getOneResource();
        player.gain(resourcesGain());
        player.spend(response.resources.addWorkers(1));
    }

    private Resources resourcesGain() {
        return Resources.empty().addWorkers(3);
    }
}