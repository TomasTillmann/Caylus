package dev.tillmann.caylus.cli;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;
import dev.tillmann.model.buildings.Building;

public class PlayerPlanResponse extends Response {
    public boolean passed;
    public Building building;
    public boolean deliver;

    /**
     * {@code player} decides what would he like to do on his turn.
     * @param player
     * @return
     */
    public static PlayerPlanResponse parse(Player player) {
        final int pass = 1;
        final int plan = 2;
        final int deliver = 3;

        PlayerPlanResponse response = new PlayerPlanResponse();
        visualizer.showWhoseTurnIs(player);

        // get player's decision
        int option = getSanitizedInput(
            "Choose what would you like to do:\npass(1)\nplan(2)\ndeliver(3)",
            "You can choose only one of these options. For example, to pass, write 1. Note that you can't deliver multiple times.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == pass || n == plan) {
                        return n;
                    }

                    if(n == deliver) {
                        return board().constructionSite().canPlan(player) ? n : null;
                    }
                } catch(Exception ex) { }
                return null;
            });

        if(option == pass) {
            response.passed = true;
            visualizer.showDelimiter();
            return response;
        }

        if(option == deliver) {
            response.deliver = true;
            visualizer.showDelimiter();
            return response;
        }

        visualizer.showRoad(board().road());

        response.building = getSanitizedInput(
            "Select the building you would like to plan on. You can't select empty building.",
            "To select the building, type the number above it. For example, write 1.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input) - 1;
                    try {
                        Building building = board().road().building(n);
                        if(building != null) {
                            if(building.canPlan(player)) {
                                return board().road().building(n);
                            }
                            else {
                                visualizer.println(String.format("You don't have enough workers to plan on %s or you have already plan on %s during this turn.", building.name()));
                            }
                        }
                    } catch(IllegalArgumentException ex) {}
                } catch(Exception ex) {}

                return null;
            });

        visualizer.showDelimiter();
        return response;
    }
}
