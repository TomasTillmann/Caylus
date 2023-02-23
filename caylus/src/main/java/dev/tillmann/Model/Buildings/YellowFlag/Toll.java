package dev.tillmann.Model.Buildings.YellowFlag;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Map;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Toll extends YellowFlagBuilding {
    Map map;

    public Toll(Map map) {
        this.map = map;
    }

    @Override
    protected void activatePlayer(Player player) {
        player.spend(resourcesCost());
        CLI.ProvostPositionResponse response = CLI.getProvostPosition(player);
        map.road().setProvost(response.provostNewPosition);
    }

    private Resources resourcesCost() {
        return Resources.empty().addWorkers(workersCost());
    }
}