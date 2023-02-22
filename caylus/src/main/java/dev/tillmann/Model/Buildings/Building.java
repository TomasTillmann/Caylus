package dev.tillmann.Model.Buildings;

import java.util.ArrayList;
import java.util.List;

import dev.tillmann.Caylus.Map;
import dev.tillmann.Caylus.Player;
import dev.tillmann.Model.Resources;

public abstract class Building {
    protected List<Player> plannedPlayers = new ArrayList<>();
    protected Player owner;

    public abstract void activate(Map map);
    public abstract Resources resourcesCost();

    public void plan(Player player) {
        plannedPlayers.add(player);
    }

    public void toResidence(Player owner) {
        this.owner = owner;
    }
}
