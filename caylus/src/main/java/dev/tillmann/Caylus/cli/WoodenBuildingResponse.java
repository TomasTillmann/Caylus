package dev.tillmann.caylus.cli;

import dev.tillmann.model.BuildingsPile;
import dev.tillmann.model.Player;
import dev.tillmann.model.buildings.wooden.WoodenBuilding;

public class WoodenBuildingResponse extends Response {
    public WoodenBuilding woodenBuilding;

    public static WoodenBuildingResponse parse(Player player) {
        WoodenBuildingResponse response = new WoodenBuildingResponse();
        visualizer.showState(gameState());

        visualizer.showTurn(player);

        visualizer.showRemainingWoodenBuildings();
        visualizer.showDelimiter();

        int option = getSanitizedInput(
            "Select which wooden building would you like to build.",
            "Select by writing the number associated with the building. For example, write 1.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input) - 1;
                    try {
                        BuildingsPile.Instance.remainingBuildings().stream().filter(b -> b instanceof WoodenBuilding).toList().get(n);
                        return n;
                    } catch(Exception ex) {}
                } catch(Exception ex) { }

                return null;
            });

            response.woodenBuilding = (WoodenBuilding)BuildingsPile.Instance.remainingBuildings().stream().filter(b -> b instanceof WoodenBuilding).toList().get(option);

            // actually take it out of the pile to claim it's ownership
            BuildingsPile.Instance.getBuildings(b -> b == response.woodenBuilding);

            return response;
    }
}