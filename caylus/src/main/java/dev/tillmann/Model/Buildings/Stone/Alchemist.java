package dev.tillmann.model.buildings.stone;

import dev.tillmann.caylus.cli.CLI;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Alchemist extends StoneBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(5);
    }

    @Override
    public Resources toBuildCost(Player player) {
        return Resources.empty().addStone(1).addWood(1);
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        CLI.OptionResponse optionResponse = CLI.instance().getAlchemistOption(player);

        if(optionResponse.option == 1) {
            CLI.ResourcesResponse oneResource = CLI.instance().getOneResource(player);

            player.spend(oneResource.resources);
            player.gain(Resources.empty().addGold(1));
        }
        else if(optionResponse.option == 2) {
            CLI.ResourcesResponse firstResource = CLI.instance().getOneResource(player);
            CLI.ResourcesResponse secondResource = CLI.instance().getOneResource(player);

            player.spend(firstResource.resources.add(secondResource.resources));
            player.gain(Resources.empty().addGold(2));
        }
        else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String name() {
        return "Alchemist";
    }
}