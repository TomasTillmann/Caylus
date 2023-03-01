package dev.tillmann.model.buildings;

import java.util.ArrayList;
import java.util.List;

import dev.tillmann.caylus.cli.CLI;
import dev.tillmann.model.BuildingsProvider;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;
import dev.tillmann.model.buildings.stone.StoneBuilding;
import dev.tillmann.model.buildings.wooden.WoodenBuilding;

public final class ConstructionSite {
    private Building first;
    public Building first() { return first; }

    private Building second;
    public Building second() { return second; }

    private Building third;
    public Building third() { return third; }

    private List<Player> plannedPlayers = new ArrayList<>();

    public ConstructionSite() {
        first = BuildingsProvider.getBuildings(b -> b instanceof YellowFlagBuilding, 1).get(0);
        second = BuildingsProvider.getBuildings(b -> b instanceof WoodenBuilding, 1).get(0);
        third = BuildingsProvider.getBuildings(b -> b instanceof StoneBuilding, 1).get(0);
    }

    public void deliver() {
        if(plannedPlayers.size() == 0) {
            return;
        }

        int mostBundles = 0;
        Player bestPlayer = plannedPlayers.get(0);

        for(Player player : plannedPlayers) {
            CLI.ConstructionSiteResponse response = CLI.instance().getConstructionSiteResponse(player);

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
