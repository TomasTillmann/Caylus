package dev.tillmann.caylus.cli;

import dev.tillmann.model.BuildingsPile;
import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;
import dev.tillmann.model.buildings.wooden.WoodenBuilding;

import java.util.stream.Collectors;

public class WoodenBuildingResponse extends Response {
    public WoodenBuilding woodenBuilding;
    public Resources toBuildCost;

    /**
     * Which wooden building would like the {@code player} build.
     * 
     * @param player
     * @return
     */
    public static WoodenBuildingResponse parse(Player player) {
        WoodenBuildingResponse response = new WoodenBuildingResponse();
        visualizer.showRemainingWoodenBuildings();
        visualizer.showWhoseTurnIs(player);

        response.woodenBuilding = getSanitizedInput(
                "Select which wooden building would you like to build.",
                "Select by writing the number associated with the building. For example, write 1.",
                input -> {
                    try {
                        Integer n = Integer.parseInt(input) - 1;
                        try {
                            WoodenBuilding woodenBuilding = (WoodenBuilding) BuildingsPile.Instance.remainingBuildings()
                                    .stream().filter(b -> b instanceof WoodenBuilding).collect(Collectors.toList())
                                    .get(n);
                            response.toBuildCost = woodenBuilding.toBuildCost(player);
                            if (player.canSpend(response.toBuildCost)) {
                                return woodenBuilding;
                            } else {
                                visualizer.println(String.format("You don't have enough resources to build %s",
                                        woodenBuilding.name()));
                            }
                        } catch (ClassCastException ex) {
                        }
                    } catch (NumberFormatException ex) {
                    }

                    return null;
                });

        // actually take it out of the pile to claim it's ownership and build it on the
        // road
        BuildingsPile.Instance.getBuildings(b -> b == response.woodenBuilding);

        visualizer.showDelimiter();
        return response;
    }
}
