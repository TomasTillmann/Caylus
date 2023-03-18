package dev.tillmann.model.buildings.starting;

import dev.tillmann.caylus.cli.ProvostPositionResponse;
import dev.tillmann.model.Board;
import dev.tillmann.model.Player;

public class Toll extends StartingBuilding {
    Board map;

    public Toll(Board map) {
        this.map = map;
    }

    @Override
    public String name() {
        return "Toll";
    }

    @Override
    public String description() {
        return String.format("Place %s workers to be able to move the provost by 0, 1, or 2 steps either forward or backward.", workersCost()); 
    }

    @Override
    protected void activatePlayer(Player player) {
        ProvostPositionResponse response = ProvostPositionResponse.parse(player, map.road());
        map.road().setProvost(response.provostNewPosition);
    }
}