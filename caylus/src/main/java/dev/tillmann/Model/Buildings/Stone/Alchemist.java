package dev.tillmann.model.buildings.stone;

import dev.tillmann.caylus.cli.OptionResponse;
import dev.tillmann.caylus.cli.ResourcesResponse;
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
    public String description() {
        return String.format("Place %s workers and spend either food, wood, stone, or fabric or two of such resources and gain one gold or two golds respectively.\nCost: stone, wood.", workersCost()); 
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        OptionResponse optionResponse = OptionResponse.getAlchemistOption(player);

        if(optionResponse.option == 1) {
            ResourcesResponse oneResource = ResourcesResponse.spendOneResource(player);

            player.spend(oneResource.resources);
            player.gain(Resources.empty().addGold(1));
        }
        else if(optionResponse.option == 2) {
            ResourcesResponse firstResource = ResourcesResponse.spendOneResource(player);
            ResourcesResponse secondResource = ResourcesResponse.spendOneResource(player);

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