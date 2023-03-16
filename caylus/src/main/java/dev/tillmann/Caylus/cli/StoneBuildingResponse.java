package dev.tillmann.caylus.cli;

import dev.tillmann.model.BuildingsPile;
import dev.tillmann.model.Player;
import dev.tillmann.model.buildings.stone.StoneBuilding;

public class StoneBuildingResponse extends Response {
    public StoneBuilding stoneBuilding;

    public static StoneBuildingResponse getStoneBuildingToBuild(Player player) {
        StoneBuildingResponse response = new StoneBuildingResponse();
        visualizer.showWhoseTurnIs(player);

        int option = getSanitizedInput(
            "Select which wooden building would you like to build.",
            "Select by writing the number associated with the building. For example, write 1.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input) - 1;
                    try {
                        BuildingsPile.Instance.remainingBuildings().stream().filter(b -> b instanceof StoneBuilding).toList().get(n);
                        return n;
                    } catch(Exception ex) {}
                } catch(Exception ex) { }

                return null;
            });

            response.stoneBuilding = (StoneBuilding)BuildingsPile.Instance.remainingBuildings().stream().filter(b -> b instanceof StoneBuilding).toList().get(option);

            // actually take it out of the pile to claim it's ownership
            BuildingsPile.Instance.getBuildings(b -> b == response.stoneBuilding);

            visualizer.showDelimiter();
            return response;
    }
}