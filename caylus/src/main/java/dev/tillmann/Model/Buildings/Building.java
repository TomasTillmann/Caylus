package dev.tillmann.model.buildings;

import java.util.ArrayList;
import java.util.List;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;
import dev.tillmann.model.Visualizable;

public abstract class Building implements Visualizable {
    private List<Player> plannedPlayers = new ArrayList<>();
    public List<Player> plannedPlayers() { return plannedPlayers; }

    private Player owner;
    public final Player owner() { return owner; }

    public final void setOwner(Player player) {
        owner = player;
        player.ownedBuildings().add(this);
    }

    public abstract String name();

    @Override
    public String visualize() {
        String visualization = "";
        visualization += "Name: " + name() + "\n";

        if(hasOwner()) {
           visualization += "Owner: " + owner().visualize() + "\n";
        }

        if(plannedPlayers().size() != 0) {
            visualization += "\n";
            visualization += "Planned:\n";
        }

        for(Player player : plannedPlayers()) {
            visualization += player.visualize() + "\n";
        }

        return visualization;
    }

    public final boolean hasOwner() {
        return owner == null ? false : true;
    }

    public final int spendWorkers() {
        int workers = 0;
        for(Player player : plannedPlayers()) {
            player.spend(Resources.empty().addWorkers(workersCost()));
            workers += workersCost(); 
        }

        return workers;
    }

    public final void plan(Player player) {
        plannedPlayers().add(player);
    }

    public final void benefit(Player player) {
        activatePlayer(player);
    }

    protected final void reset() {
        plannedPlayers().clear();

        additionalReset();
    }

    protected void additionalReset() { }

    protected abstract void activatePlayer(Player player);

    public int workersCost() {
        return 1;
    }

    public void activate() {
        for(Player player : plannedPlayers()) {
            activatePlayer(player);
        }

        reset();
    }
}
