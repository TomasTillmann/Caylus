package dev.tillmann.caylus.cli;

import dev.tillmann.model.Player;
import dev.tillmann.model.buildings.Building;

public class BuildingToOwnResponse extends Response {
    public Building building;

    /**
     * Promts the user to provide which building he would like to own.
     * Selected buiding will have the player as it's owner.
     * @param player
     * @return
     */
    public static BuildingToOwnResponse parse(Player player) {
        BuildingToOwnResponse response = new BuildingToOwnResponse();
        visualizer.showRoad(board().road());
        visualizer.showWhoseTurnIs(player);

        response.building = getSanitizedInput(
            "Select the building you would like to own. You can't own empty building or building which is already owned.",
            "To select the building, type the number above it. For example, write 1. Beware, you can't own empty building or building which is already owned.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input) - 1;
                    try {
                        Building building = board().road().building(n);
                        if(building != null) {
                            if(!building.hasOwner()) {
                                return building;
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
