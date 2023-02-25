package dev.tillmann.Model.Buildings.YellowFlag;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Board;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Toll extends YellowFlagBuilding {
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