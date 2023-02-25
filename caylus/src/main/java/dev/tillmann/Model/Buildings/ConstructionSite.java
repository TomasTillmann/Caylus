package dev.tillmann.Model.Buildings;

import java.util.ArrayList;
import java.util.List;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.BuildingsProvider;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;
import dev.tillmann.Model.Buildings.Starting.StartingBuilding;
import dev.tillmann.Model.Buildings.Stone.StoneBuilding;
import dev.tillmann.Model.Buildings.Wooden.WoodenBuilding;

public class ConstructionSite {
    private StartingBuilding first;
    public StartingBuilding first() { return first; }

    private WoodenBuilding second;
    public WoodenBuilding second() { return second; }

    private StoneBuilding third;
    public StoneBuilding third() { return third; }

    private List<Player> plannedPlayers = new ArrayList<>();

    public ConstructionSite() {
        first = BuildingsProvider.getYellowFlag(1).get(0);
        second = BuildingsProvider.getWooden(1).get(0);
        third = BuildingsProvider.getStone(1).get(0);
    }

    public void deliver() {
        if(plannedPlayers.size() == 0) {
            return;
        }

        int mostBundles = 0;
        Player bestPlayer = plannedPlayers.get(0);

        for(Player player : plannedPlayers) {
            CLI.ConstructionSiteResponse response = CLI.getConstructionSiteResponse(player);

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
        plannedPlayers.clear();
    }

    public void plan(Player player) {
        plannedPlayers.add(player);
    }
}
