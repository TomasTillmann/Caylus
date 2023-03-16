package dev.tillmann.caylus.cli;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class ResourcesResponse extends Response {
    public Resources resources;

    // todo: exclude gold and workers - change name than - only allow wood, stone, fabric, food
    public static ResourcesResponse getOneResource(Player player) {
        ResourcesResponse response = new ResourcesResponse();

        response.resources = getSanitizedInput(
            "Which resource would you like?\n1.Food\n2.Wood\n3.Stone\n4.Fabric.",
            "For example, to select Food, write 1.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == 1) {
                        return Resources.empty().addFood(1);
                    }
                    if(n == 2) {
                        return Resources.empty().addWood(1);
                    }
                    if(n == 3) {
                        return Resources.empty().addStone(1);
                    }
                    if(n == 4) {
                        return Resources.empty().addFabric(1);
                    }
                } catch(Exception ex) {}

                return null;

            });

        return response;
    }
}