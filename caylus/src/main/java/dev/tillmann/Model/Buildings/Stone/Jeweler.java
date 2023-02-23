package dev.tillmann.Model.Buildings.Stone;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Jeweler extends StoneBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(5);
    }

    @Override
    public Resources toBuildCost() {
        return Resources.empty().addStone(1).addWood(1);
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        CLI.OptionResponse response = CLI.getJewelerOption();

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
}