package dev.tillmann.Model.Buildings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Map;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class GuildsBridge extends StartingBuilding {
    /*
     * Players are in order in which they passed. 
     */
    private List<Player> passedPlayers = new ArrayList<>();

    private Map map;

    public GuildsBridge(Map map) {
        this.map = map;
    }

    public void passed(Player player) {
        passedPlayers.add(player);
    }

    public List<Player> passedPlayers() {
        return Collections.unmodifiableList(passedPlayers);
    }

    @Override
    public void activate() {
        // there are no planned players on guilds bridge
        // each round all players move the provost, in the order of passing

        for(Player player : passedPlayers) {
            activatePlayer(player);
        }
    }

    @Override
    protected void activatePlayer(Player player) {
        CLI.ProvostPositionResponse response = CLI.getProvostPosition(player);
        map.road().setProvost(response.provostNewPosition);
        player.spend(resourcesCost(response.provostDifference));
    }

    private Resources resourcesCost(int provostDifference) {
        return Resources.empty().addWorkers(provostDifference);
    }
}
