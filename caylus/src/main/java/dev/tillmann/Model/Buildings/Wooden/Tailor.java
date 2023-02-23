package dev.tillmann.Model.Buildings.Wooden;

import dev.tillmann.Caylus.CLI;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Tailor extends WoodenBuilding {
    @Override
    public void immidiateReward(Player player) {
        player.awardPrestigePoints(4);
    }

    @Override
    public Resources toBuildCost() {
        CLI.ResourcesResponse response = CLI.getOneResource();
        return Resources.empty().addWood(1).add(response.resources);
    }

    @Override
    public void constructionActivate(Player player) {
        setupActivate(player);
    }

    @Override
    public void setupActivate(Player player) {
        CLI.OptionResponse response = CLI.getTailorResponse(player);

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
}