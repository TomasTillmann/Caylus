package dev.tillmann.model.buildings.starting;

import dev.tillmann.caylus.cli.CLI;
import dev.tillmann.model.Board;
import dev.tillmann.model.Player;

public class Toll extends StartingBuilding {
    Board map;

    public Toll(Board map) {
        this.map = map;
    }

    @Override
    protected void activatePlayer(Player player) {
        CLI.ProvostPositionResponse response = CLI.instance().getProvostPosition(player, map.road());
        map.road().setProvost(response.provostNewPosition);
    }
}