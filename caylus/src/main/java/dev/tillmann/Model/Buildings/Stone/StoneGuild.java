package dev.tillmann.model.buildings.stone;

import dev.tillmann.caylus.cli.CLI;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class StoneGuild extends StoneBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(4);
    }

    @Override
    public Resources toBuildCost(Player player) {
        CLI.ResourcesResponse response = CLI.instance().getOneResource(player);
        return Resources.empty().addStone(1).add(response.resources);
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        CLI.ResourcesResponse response = CLI.instance().getOneResource(player);
        player.spend(response.resources);
        player.gain(Resources.empty().addWorkers(5));
    }

    @Override
    public String name() {
        return "Stone guild";
    }
}