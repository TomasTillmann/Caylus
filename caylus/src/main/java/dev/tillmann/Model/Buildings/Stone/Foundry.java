package dev.tillmann.model.buildings.stone;

import dev.tillmann.caylus.cli.CLI;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

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
        CLI.OptionResponse response = CLI.instance().getFoundryOption();

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