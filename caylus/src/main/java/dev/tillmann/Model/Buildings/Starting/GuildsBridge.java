package dev.tillmann.Model.Buildings.Starting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Board;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class GuildsBridge extends StartingBuilding {
    private List<Player> allPlayers;

    /*
     * Players are in order in which they passed. 
     */
    private List<Player> passedPlayers = new ArrayList<>();
    private List<Player> stillPlanningPlayers;

    private Board map;

    public GuildsBridge(Board map, List<Player> players) {
        this.map = map;
        allPlayers = players;
        stillPlanningPlayers = new ArrayList<>(allPlayers);
    }

    public void passed(Player player) {
        passedPlayers.add(player);
        stillPlanningPlayers.remove(player);
    }

    public List<Player> passedPlayers() {
        return Collections.unmodifiableList(passedPlayers);
    }

    public List<Player> stillPlanningPlayers() {
        return Collections.unmodifiableList(stillPlanningPlayers);
    }

    @Override
    public void activate() {
        // there are no still planning players on guilds bridge
        // each round all players move the provost, in the order of passing

        for(Player player : passedPlayers) {
            activatePlayer(player);
        }

        // reset the bridge for next round
        for(Player player : allPlayers) {
            stillPlanningPlayers.add(player);
        }
        passedPlayers.clear();
        //
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
