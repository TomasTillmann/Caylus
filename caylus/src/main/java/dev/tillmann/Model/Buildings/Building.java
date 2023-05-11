package dev.tillmann.model.buildings;

import java.util.ArrayList;
import java.util.List;

import dev.tillmann.caylus.cli.Visualizer;
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
        visualization += "Owner:" + (hasOwner() ? " " + owner().name : "") + "\n";
        visualization += "Planned:";
        for(Player player : plannedPlayers()) {
            visualization += " " + player.name;
        }
        visualization += "\n";

        visualization += "Description: " + description() + "\n";

        return visualization;
    }

    public final boolean hasOwner() {
        return owner == null ? false : true;
    }

    public boolean canPlan(Player player) {
        return player.canSpend(Resources.empty().addWorkers(workersCost())) && !plannedPlayers.contains(player);
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

    public abstract String description();

    public void activate() {
        for(Player player : plannedPlayers()) {
            Visualizer.instance().println(String.format("Building %s is being activated by player %s.", name(), player.name));
            Visualizer.instance().println(String.format("Description: %s.", description()));
            activatePlayer(player);
        }

        reset();
    }
}
