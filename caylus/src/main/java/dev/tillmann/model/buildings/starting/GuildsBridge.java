package dev.tillmann.model.buildings.starting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.tillmann.caylus.cli.ProvostPositionResponse;
import dev.tillmann.model.Board;
import dev.tillmann.model.BuildingsPile;
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

        BuildingsPile.Instance.returnBuilding(this);
    }

    @Override
    public String description() {
        return String.format("You can move the provost here by max 3 places back or forward. Each move costs you one worker.", workersCost()); 
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
    public String name() {
        return "Guilds bridge";
    }

    @Override
    public void activate() {
        // on guilds bridge, no players are ever planned
        for(Player player : passedPlayers) {
            activatePlayer(player);
        }

        reset();
    }

    @Override
    protected void activatePlayer(Player player) {
        int oldProvostPosition = map.road().provost();
        ProvostPositionResponse response = ProvostPositionResponse.parse(player, map.road());
        map.road().setProvost(response.provostNewPosition);
        player.spend(resourcesCost(Math.abs(oldProvostPosition - response.provostNewPosition)));
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
