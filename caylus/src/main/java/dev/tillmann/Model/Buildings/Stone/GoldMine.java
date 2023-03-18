package dev.tillmann.model.buildings.stone;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class GoldMine extends StoneBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(4);
    }

    @Override
    public Resources toBuildCost(Player player) {
        return Resources.empty().addStone(1).addFood(1);
    }

    @Override
    public String description() {
        return String.format("Place %s workers to get one gold.", workersCost()); 
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        player.gain(Resources.empty().addGold(1));
    }

    @Override
    public String name() {
        return "Gold mine";
    }
}