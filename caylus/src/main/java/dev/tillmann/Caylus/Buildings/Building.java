package dev.tillmann.Caylus.Buildings;

import java.util.ArrayList;
import java.util.List;
import dev.tillmann.Caylus.Player;

public abstract class Building {
    protected List<Player> plannedPlayers = new ArrayList<>();

    public abstract void activate();

    public void plan(Player player) {
        plannedPlayers.add(player);
    }
}
