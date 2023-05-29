package dev.tillmann.caylus.cli;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class ResourcesResponse extends Response {
    public Resources resources;

    /**
     * {@code player} chooses which one resource will he would like to gain.
     * 
     * @param player
     * @return
     */
    public static ResourcesResponse gainOneResource(Player player) {
        ResourcesResponse response = new ResourcesResponse();
        visualizer.showWhoseTurnIs(player);

        response.resources = getSanitizedInput(
                "Which resource would you like to gain?\n1.Food\n2.Wood\n3.Stone\n4.Fabric.",
                "For example, to select Food, write 1.",
                input -> {
                    try {
                        Integer n = Integer.parseInt(input);
                        if (n == 1) {
                            return Resources.empty().addFood(1);
                        }
                        if (n == 2) {
                            return Resources.empty().addWood(1);
                        }
                        if (n == 3) {
                            return Resources.empty().addStone(1);
                        }
                        if (n == 4) {
                            return Resources.empty().addFabric(1);
                        }
                    } catch (NumberFormatException ex) {
                    }

                    return null;

                });

        visualizer.showDelimiter();
        return response;
    }

    public static ResourcesResponse spendOneResource(Player player) {
        ResourcesResponse response = new ResourcesResponse();
        visualizer.showWhoseTurnIs(player);

        response.resources = getSanitizedInput(
                "Which resource would you like to spend?\n1.Food\n2.Wood\n3.Stone\n4.Fabric.",
                "For example, to select Food, write 1. You can spend only the resource which you have.",
                input -> {
                    try {
                        Integer n = Integer.parseInt(input);
                        Resources resource = null;
                        if (n == 1) {
                            resource = Resources.empty().addFood(1);
                        }
                        if (n == 2) {
                            resource = Resources.empty().addWood(1);
                        }
                        if (n == 3) {
                            resource = Resources.empty().addStone(1);
                        }
                        if (n == 4) {
                            resource = Resources.empty().addFabric(1);
                        }

                        if (resource != null && player.canSpend(resource)) {
                            return resource;
                        }
                    } catch (Exception ex) {
                    }

                    return null;
                });

        visualizer.showDelimiter();
        return response;
    }
}
