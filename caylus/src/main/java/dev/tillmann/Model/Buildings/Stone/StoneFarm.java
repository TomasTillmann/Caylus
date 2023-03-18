package dev.tillmann.model.buildings.stone;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class StoneFarm extends StoneBuilding {
    @Override
    public void immidiateReward(Player player) {
    }

    @Override
    public Resources toBuildCost(Player player) {
        return Resources.empty().addStone(1).addFood(1);
    }

    @Override
    public String description() {
        return String.format("Place %s workers and two food and one fabric to get one food and one stone. If someone else than the owner activates this building, the owner gets one food.", workersCost()); 
    }

    @Override
    public void constructionActivate(Player player) {
        player.gain(Resources.empty().addFood(2).addFabric(1));

        if(player != owner()) {
            owner().gain(Resources.empty().addFood(1));
        }
    }

    @Override
    public void setupActivate(Player player) {
        player.gain(Resources.empty().addFood(1).addFabric(1));
    }

    @Override
    public String name() {
        return "Stone farm";
    }
}