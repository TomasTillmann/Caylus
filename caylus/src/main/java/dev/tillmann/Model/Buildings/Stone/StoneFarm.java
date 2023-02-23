package dev.tillmann.Model.Buildings.Stone;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class StoneFarm extends StoneBuilding {
    @Override
    public void immidiateReward(Player player) {
    }

    @Override
    public Resources toBuildCost() {
        return Resources.empty().addStone(1).addFood(1);
    }

    @Override
    public void constructionActivate(Player player) {
        player.gain(Resources.empty().addFood(2).addFabric(1));

        if(player != owner) {
            owner.gain(Resources.empty().addFood(1));
        }
    }

    @Override
    public void setupActivate(Player player) {
        player.gain(Resources.empty().addFood(1).addFabric(1));
    }
}