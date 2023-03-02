package dev.tillmann.model.buildings.starting;

import dev.tillmann.caylus.cli.CLI;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Guild3 extends StartingBuilding {
    @Override
    public String name() {
        return "Guild3";
    }

    @Override
    protected void activatePlayer(Player player) {
        CLI.ResourcesResponse response = CLI.instance().getOneResource(player);
        player.gain(resourcesGain());
        player.spend(response.resources);
    }

    private Resources resourcesGain() {
        return Resources.empty().addWorkers(3);
    }
}