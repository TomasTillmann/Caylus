package dev.tillmann.caylus.cli;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public class OptionResponse extends Response {
    public int option;

    public static OptionResponse getTailorOption(Player player) {
        visualizer.println("Tailor activation.");

        OptionResponse response = new OptionResponse();
        response.option = getSanitizedInput(
            "Would you like to spend 1 cloth and gain 4PP (1) or spend 2 cloth and gain 6PP (2)?",
            "Write 1 for the first option, or 2 for the second.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == 1) {
                        if(player.resources().fabric() >= 1) {
                            return n;
                        }
                    }
                    if(n == 2) {
                        if(player.resources().fabric() >= 2) {
                            return n;
                        }
                    }
                } catch(Exception ex) {}

                return null;
            });

        return response;
    }

    public static OptionResponse getFoundryOption(Player player) {
        visualizer.println("Foundry activation.");

        OptionResponse response = new OptionResponse();
        response.option = getSanitizedInput(
            "Would you like to spend 1 worker and gain 1 gold (1) or spend 3 workers and gain 2 gold (2)?",
            "Write 1 for the first option, or 2 for the second.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == 1) {
                        if(player.resources().workers() >= 1) {
                            return n;
                        }
                    }
                    if(n == 2) {
                        if(player.resources().workers() >= 3) {
                            return n;
                        }
                    }
                } catch(Exception ex) {}

                return null;
            });

        return response;
    }

    public static OptionResponse getAlchemistOption(Player player) {
        visualizer.println("Alchemist activation.");

        OptionResponse response = new OptionResponse();
        response.option = getSanitizedInput(
            "Would you like to spend 1 food / stone / wood / fabric and gain 1 gold (1) or spend 2 food / stone / wood / fabric and gain 2 gold (2)?",
            "Write 1 for the first option, or 2 for the second.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    Resources r = player.resources();

                    if(n == 1) {
                        if(r.food() >= 1 || r.stone() >= 1 || r.wood() >= 1 || r.fabric() >= 1 && (n == 1 || n == 2)) {
                            return n;
                        }
                    }
                } catch(Exception ex) {}

                return null;
            });

        return response;
    }

    public static OptionResponse getChurchOption(Player player) {
        visualizer.println("Church activation.");

        OptionResponse response = new OptionResponse();
        response.option = getSanitizedInput(
            "Would you like to spend 2 workers and gain 4PP (1) or spend 4 workers and gain 6PP (2)?",
            "Write 1 for the first option, or 2 for the second.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == 1) {
                        if(player.resources().workers() >= 2) {
                            return n;
                        }
                    }
                    if(n == 2) {
                        if(player.resources().workers() >= 4) {
                            return n;
                        }
                    }
                } catch(Exception ex) {}

                return null;
            });

        return response;
    }

    public static OptionResponse getJewelerOption(Player player) {
        visualizer.println("Jeweler activation.");

        OptionResponse response = new OptionResponse();
        response.option = getSanitizedInput(
            "Would you like to spend 1 gold and gain 5PP (1) or spend 2 gold and gain 9PP (2)?",
            "Write 1 for the first option, or 2 for the second.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == 1) {
                        if(player.resources().gold() >= 1) {
                            return n;
                        }
                    }
                    if(n == 2) {
                        if(player.resources().gold() >= 2) {
                            return n;
                        }
                    }
                } catch(Exception ex) {}

                return null;
            });

        return response;
    }
}