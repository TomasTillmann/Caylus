package dev.tillmann.Model.Buildings.Wooden;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class WoodenMarket extends WoodenBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(3);
    }

    @Override
    public Resources toBuildCost() {
        return Resources.empty().addWood(1).addFood(1);
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        CLI.ResourcesResponse response = CLI.getOneResource();
        player.gain(response.resources);
    }
}