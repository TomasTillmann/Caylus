package dev.tillmann.model.buildings.wooden;

import dev.tillmann.caylus.cli.OptionResponse;
import dev.tillmann.caylus.cli.ResourcesResponse;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class Tailor extends WoodenBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(4);
    }

    @Override
    public Resources toBuildCost(Player player) {
        ResourcesResponse response = ResourcesResponse.getOneResource(player);
        return Resources.empty().addWood(1).add(response.resources);
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        OptionResponse response = OptionResponse.getTailorOption(player);

        Resources resources = Resources.empty();
        int pp;

        // either first or second option
        if(response.option == 1) {
            resources.addFabric(1);
            pp = 4;
        }
        else if(response.option == 2) {
            resources.addFabric(2);
            pp = 6;
        }
        else {
            throw new UnsupportedOperationException();
        }

        player.spend(resources);
        player.awardPrestigePoints(pp);
    }

    @Override
    public String name() {
        return "Tailor";
    }
}