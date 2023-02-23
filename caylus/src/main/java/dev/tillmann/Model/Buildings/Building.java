package dev.tillmann.Model.Buildings;

import java.util.ArrayList;
import java.util.List;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public abstract class Building {
    protected List<Player> plannedPlayers = new ArrayList<>();
    protected java.util.Map<Player, Integer> playerToWorkers = new java.util.HashMap<>();
    public Player owner;

    protected abstract void activatePlayer(Player player);

    public int workersCost() {
        return 1;
    }

    public void activate() {
        for(Player player : plannedPlayers) {
            activatePlayer(player);
        }
    }

    public void spendWorkers() {
        for(Player player : playerToWorkers.keySet()) {
            player.spend(Resources.empty().addWorkers(playerToWorkers.get(player)));
        }
    }

    public final void plan(Player player) {
        plannedPlayers.add(player);
        if(playerToWorkers.containsKey(player)) {
            playerToWorkers.put(player, workersCost());
        }
        else {
            playerToWorkers.put(player, playerToWorkers.get(player) + workersCost());
        }
    }

    public final void toResidence(Player owner) {
        this.owner = owner;
    }
}
