package dev.tillmann.caylus.cli;

import dev.tillmann.model.BuildingsPile;
import dev.tillmann.model.Player;
import dev.tillmann.model.buildings.Building;
import dev.tillmann.model.buildings.stone.StoneBuilding;

import java.util.stream.Collectors;

public class StoneBuildingResponse extends Response {
    public StoneBuilding stoneBuilding;

    /**
     * Gets the stone building to build.
     * 
     * @param player
     * @return
     */
    public static StoneBuildingResponse getStoneBuildingToBuild(Player player) {
        StoneBuildingResponse response = new StoneBuildingResponse();
        visualizer.showWhoseTurnIs(player);

        response.stoneBuilding = getSanitizedInput(
                "Select which wooden building would you like to build.",
                "Select by writing the number associated with the building. For example, write 1.",
                input -> {
                    try {
                        Integer n = Integer.parseInt(input) - 1;
                        try {
                            StoneBuilding building = (StoneBuilding) BuildingsPile.Instance.remainingBuildings()
                                    .stream().filter(b -> b instanceof StoneBuilding).collect(Collectors.toList())
                                    .get(n);
                            if (player.canSpend(building.toBuildCost(player))) {
                                return building;
                            } else {
                                visualizer.println(
                                        String.format("You don't have enough resources to build %s.", building.name()));
                            }

                        } catch (ClassCastException ex) {
                        }
                    } catch (NumberFormatException ex) {
                    }

                    return null;
                });

        // actually take it out of the pile to claim it's ownership and build it on the
        // road
        BuildingsPile.Instance.getBuildings(b -> b == response.stoneBuilding);

        visualizer.showDelimiter();
        return response;
    }
}
