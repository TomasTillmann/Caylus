package dev.tillmann.Model.Buildings.Stone;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class StoneGuild extends StoneBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(4);
    }

    @Override
    public Resources toBuildCost() {
        CLI.ResourcesResponse response = CLI.getOneResource();
        return Resources.empty().addStone(1).add(response.resources);
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        CLI.ResourcesResponse response = CLI.getOneResource();
        player.spend(response.resources);
        player.gain(Resources.empty().addWorkers(5));
    }
}