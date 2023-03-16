package dev.tillmann.model.buildings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.tillmann.caylus.cli.ConstructionSiteResponse;
import dev.tillmann.model.BuildingsPile;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;
import dev.tillmann.model.Visualizable;
import dev.tillmann.model.buildings.stone.StoneBuilding;
import dev.tillmann.model.buildings.wooden.WoodenBuilding;

public final class ConstructionSite implements Visualizable {
    private Building first;
    public Building first() { return first; }

    private Building second;
    public Building second() { return second; }

    private Building third;
    public Building third() { return third; }

    private List<Player> plannedPlayers = new ArrayList<>();
    public List<Player> plannedPlayers() { return Collections.unmodifiableList(plannedPlayers); }

    public ConstructionSite() {
        first = BuildingsPile.Instance.getRandomBuildings(b -> b instanceof YellowFlagBuilding, 1).get(0);
        second = BuildingsPile.Instance.getRandomBuildings(b -> b instanceof WoodenBuilding, 1).get(0);
        third = BuildingsPile.Instance.getRandomBuildings(b -> b instanceof StoneBuilding, 1).get(0);
    }

    @Override
    public String visualize() {
        String visualization = "";
        visualization += "First: " + first().visualize() + "\n";
        visualization += "Second: " + second().visualize() + "\n";
        visualization += "Third: " + third().visualize() + "\n";

        visualization += "Planned players: \n";
        for(Player player : plannedPlayers()) {
            visualization += player.name + "\n";
        }
        if(plannedPlayers().size() != 0) { visualization += "\n"; }

        return visualization;
    }

    public void deliver() {
        if(plannedPlayers.size() == 0) {
            return;
        }

        int mostBundles = 0;
        Player bestPlayer = plannedPlayers.get(0);

        for(Player player : plannedPlayers) {
            ConstructionSiteResponse response = ConstructionSiteResponse.getConstructionSiteResponse(player);

            for(Resources resources : response.bundles) {
                player.spend(resources);
                player.awardPrestigePoints(5);
            }

            // keep track of the player who donated most bundles so far
            if(response.bundles.size() > mostBundles) {
                bestPlayer = player;
                mostBundles = response.bundles.size();
            }
            //
        }

        bestPlayer.getFavor();
        reset();
    }

    public void plan(Player player) {
        plannedPlayers.add(player);
    }

    private void reset() {
        plannedPlayers.clear();
    }
}
