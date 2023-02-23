package dev.tillmann.Model.Buildings.Stone;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Alchemist extends StoneBuilding {
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
        CLI.OptionResponse optionResponse = CLI.getAlchemistOption();

        if(optionResponse.option == 1) {
            CLI.ResourcesResponse oneResource = CLI.getOneResource();

            player.spend(oneResource.resources);
            player.gain(Resources.empty().addGold(1));
        }
        else if(optionResponse.option == 2) {
            CLI.ResourcesResponse firstResource = CLI.getOneResource();
            CLI.ResourcesResponse secondResource = CLI.getOneResource();

            player.spend(firstResource.resources.add(secondResource.resources));
            player.gain(Resources.empty().addGold(2));
        }
        else {
            throw new UnsupportedOperationException();
        }
    }
}