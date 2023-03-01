package dev.tillmann.model.buildings.starting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.tillmann.caylus.cli.CLI;
import dev.tillmann.model.Board;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

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
        // on guilds bridge, no players are ever planned, but it still has to be activated -> inherited field plannedPlayers is completely ignored

        for(Player player : passedPlayers) {
            activatePlayer(player);
        }

        reset();
    }

    @Override
    protected void activatePlayer(Player player) {
        CLI.ProvostPositionResponse response = CLI.instance().getProvostPosition(player);
        map.road().setProvost(response.provostNewPosition);
        player.spend(resourcesCost(response.provostDifference));
    }

    @Override
    protected void additionalReset() {
        for(Player player : allPlayers) {
            stillPlanningPlayers.add(player);
        }

        passedPlayers.clear();
    }

    private Resources resourcesCost(int provostDifference) {
        return Resources.empty().addWorkers(provostDifference);
    }
}
