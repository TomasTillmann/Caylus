package dev.tillmann.Model.Buildings.Stone;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Foundry extends StoneBuilding {
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
        CLI.OptionResponse response = CLI.getFoundryOption();

        if(response.option == 1) {
            player.spend(Resources.empty().addWorkers(1));
            player.gain(Resources.empty().addGold(1));
        }
        else if(response.option == 2) {
            player.spend(Resources.empty().addWorkers(3));
            player.gain(Resources.empty().addGold(2));
        }
        else {
            throw new UnsupportedOperationException();
        }
    }
}