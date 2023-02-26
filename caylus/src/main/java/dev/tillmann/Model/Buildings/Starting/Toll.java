package dev.tillmann.Model.Buildings.Starting;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Board;
import dev.tillmann.Model.Player;

public class Toll extends StartingBuilding {
    Board map;

    public Toll(Board map) {
        this.map = map;
    }

    @Override
    protected void activatePlayer(Player player) {
        CLI.ProvostPositionResponse response = CLI.getProvostPosition(player);
        map.road().setProvost(response.provostNewPosition);
    }
}