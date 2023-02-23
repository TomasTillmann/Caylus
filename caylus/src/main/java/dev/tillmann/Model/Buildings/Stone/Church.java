package dev.tillmann.Model.Buildings.Stone;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Church extends StoneBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(5);
    }

    @Override
    public Resources toBuildCost() {
        return Resources.empty().addStone(1).addFabric(1);
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        CLI.OptionResponse response = CLI.getChurchOption();

        if(response.option == 1) {
            player.spend(Resources.empty().addWorkers(2));
            player.awardPrestigePoints(4);
        }
        else if(response.option == 2) {
            player.spend(Resources.empty().addWorkers(4));
            player.awardPrestigePoints(6);
        }
        else {
            throw new UnsupportedOperationException();
        }
    }
}