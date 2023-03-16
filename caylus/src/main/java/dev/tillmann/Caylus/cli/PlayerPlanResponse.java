package dev.tillmann.caylus.cli;

import dev.tillmann.model.Player;
import dev.tillmann.model.buildings.Building;

public class PlayerPlanResponse extends Response {
    public boolean passed;
    public Building building;
    public boolean deliver;

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

        // resolve if he would like to plan
        int buildingIndex = getSanitizedInput(
            "Select the building you would like to plan on. You can't select empty building.",
            "To select the building, type the number above it. For example, write 1.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input) - 1;
                    try {
                        Building building = board().road().building(n);
                        if(building != null) {
                            return n;
                        }

                    } catch(IllegalArgumentException ex) {}
                } catch(Exception ex) {}

                return null;
            });
        
        response.building = board().road().building(buildingIndex);

        visualizer.showDelimiter();
        return response;
    }
}