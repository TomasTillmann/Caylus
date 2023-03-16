package dev.tillmann.caylus.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class ConstructionSiteResponse extends Response {
    public List<Resources> bundles;

    public static ConstructionSiteResponse getConstructionSiteResponse(Player player) {
        ConstructionSiteResponse response = new ConstructionSiteResponse();
        response.bundles = new ArrayList<>();

        visualizer.showConstructionSite(board().constructionSite());
        visualizer.showWhoseTurnIs(player);

        while(getSanitizedInput("Would you like to deliver a bundle? (yes / no)", "Write yes or no.", 
        input -> {
            if(input.equals("yes")) {
                return true;
            }

            if(input.equals("no")) {
                return false;
            }

            return null;
        })) {
            Optional<Resources> bundle;
            bundle = getSanitizedInput("Choose a bundle. (food, wood, stone, fabric, gold)", "Write the names of the resources seperated by space. For example, writing: food fabric gold, you delivered just that. Beware you can deliver only what you have in resources. If you have realised, you can't deliver a bundle, type stop.",
            input -> {
                String[] tokens = input.trim().split(" ");
                if(tokens.length == 1){ 
                    if(tokens[0].equals("stop")) {
                        return Optional.empty();
                    }
                }

                Resources possibleBundle = Resources.empty();
                if(tokens.length == 3) {
                    int checksum = 0;
                    for(int i = 0; i < tokens.length; ++i) {
                        String entry = tokens[i];
                        if(entry.equals("food")) {
                            if(possibleBundle.food() == 1) {
                                return null;
                            }

                            possibleBundle.addFood(1);
                            checksum += 1;
                        }
                        if(entry.equals("wood")) {
                            if(possibleBundle.wood() == 1) {
                                return null;
                            }

                            possibleBundle.addWood(1);
                            checksum += 1;
                        }
                        if(entry.equals("stone")) {
                            if(possibleBundle.stone() == 1) {
                                return null;
                            }

                            possibleBundle.addStone(1);
                            checksum += 1;
                        }
                        if(entry.equals("fabric")) {
                            if(possibleBundle.fabric() == 1) {
                                return null;
                            }

                            possibleBundle.addFabric(1);
                            checksum += 1;
                        }
                        if(entry.equals("gold")) {
                            if(possibleBundle.gold() == 1) {
                                return null;
                            }

                            possibleBundle.addGold(1);
                            checksum += 1;
                        }
                    }

                    if(checksum == 3) {
                        return Optional.of(possibleBundle);
                    }
                }

                return null;
            });

            if(bundle.isPresent()) {
                response.bundles.add(bundle.get());
            }
            else {
                // user realised he can't deliver a bundle and written stop
                break;
            }
        }

        visualizer.showDelimiter();
        return response;
    }
}