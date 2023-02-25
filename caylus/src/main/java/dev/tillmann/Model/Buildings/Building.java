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
    protected void additionalPlan(Player player) { }

    public int workersCost() {
        return 1;
    }

    public void activate() {
        for(Player player : plannedPlayers) {
            activatePlayer(player);
        }
    }

    public final int spendWorkers() {
        int workers = 0;
        for(Player player : playerToWorkers.keySet()) {
            player.spend(Resources.empty().addWorkers(playerToWorkers.get(player)));
            workers += playerToWorkers.get(player);
        }

        return workers;
    }

    public final void plan(Player player) {
        plannedPlayers.add(player);

        // workers cost required for planning
        if(playerToWorkers.containsKey(player)) {
            playerToWorkers.put(player, playerToWorkers.get(player) + workersCost());
        }
        else {
            playerToWorkers.put(player, workersCost());
        }
        //

        additionalPlan(player);
    }

    public final void toResidence(Player owner) {
        this.owner = owner;
    }
}
