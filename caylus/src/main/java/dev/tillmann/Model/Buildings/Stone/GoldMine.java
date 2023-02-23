package dev.tillmann.Model.Buildings.Stone;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class GoldMine extends StoneBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(4);
    }

    @Override
    public Resources toBuildCost() {
        return Resources.empty().addStone(1).addFood(1);
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        player.gain(Resources.empty().addGold(1));
    }
}