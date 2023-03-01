package dev.tillmann.model.buildings.starting;

import dev.tillmann.caylus.cli.CLI;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Guild3 extends StartingBuilding {
    @Override
    protected void activatePlayer(Player player) {
        CLI.ResourcesResponse response = CLI.instance().getOneResource();
        player.gain(resourcesGain());
        player.spend(response.resources.addWorkers(1));
    }

    private Resources resourcesGain() {
        return Resources.empty().addWorkers(3);
    }
}