package dev.tillmann.model.buildings.stone;

import dev.tillmann.caylus.cli.OptionResponse;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Jeweler extends StoneBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(5);
    }

    @Override
    public Resources toBuildCost(Player player) {
        return Resources.empty().addStone(1).addWood(1);
    }

    @Override
    public String description() {
        return String.format("Place %s workers and one or two golds to gain 5PP or 9PP respectively.\nCost: stone, wood.", workersCost()); 
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        OptionResponse response = OptionResponse.getJewelerOption(player);

        if(response.option == 1) {
            player.spend(Resources.empty().addGold(1));
            player.awardPrestigePoints(5);
        }
        else if(response.option == 2) {
            player.spend(Resources.empty().addGold(2));
            player.awardPrestigePoints(9);
        }
        else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String name() {
        return "Jeweler";
    }
}