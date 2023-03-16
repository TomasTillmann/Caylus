package dev.tillmann.caylus.cli;

import java.util.Optional;

import dev.tillmann.model.Player;
import dev.tillmann.model.buildings.Building;
import dev.tillmann.model.characters.GameCharacter;

public class FavorResponse extends Response {
    public boolean stealCharacter;
    public GameCharacter character;
    public Player playerStolenFrom;
    public Building building;

    public static FavorResponse parse(Player player) {
        FavorResponse response = new FavorResponse();
        final int steal = 1;
        final int benefit = 2;

        visualizer.showState(gameState());
        visualizer.showTurn(player);
        visualizer.println("You got a favor!");

        int option = getSanitizedInput(
            "Select if you would rather:\n1.Steal character from another player\n2.Benefit from one of the buildings on construction site",
            "To choose steal, write 1. To choose benefit, write 2.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == steal || n == benefit) {
                        return n;
                    }
                } catch(Exception ex) {}

                return null;
            });
        
        if(option == steal) {
            visualizer.showPlayers(players());
            visualizer.showDelimiter();
            visualizer.println("You chose to steal.");

            response.playerStolenFrom = getSanitizedInput(
                "From which player would you like to steal?",
                "Write the player's name to select him.",
                input -> {
                    if(players().stream().anyMatch(p -> p.name.equals(input))) {
                        return players().stream().filter(p -> p.name.equals(input)).findFirst().get();
                    }

                    return null;
            });
            
            visualizer.showOnBoardCharacters(board());
            visualizer.showDelimiter();

            response.character = getSanitizedInput(
                "What character would you like to steal?",
                "Write the characters name to steal it.",
                input -> {
                    Optional<GameCharacter> character = board().onBoardCharacters().stream().filter(ch -> ch.name() == input).findFirst();
                    if(character.isPresent()) {
                        return character.get();
                    }

                    return null;
            });
        return response;
        }

        if(option == benefit) {
            final int firstBuilding = 1;
            final int secondBuilding = 2;
            final int thirdBuilding = 3;

            visualizer.showDelimiter();
            visualizer.println("You chose to benefit from one of the buildings on the construction site.");

            response.building = getSanitizedInput(
                "Which building would you like to benefit from?",
                "Choose from 1 - 3. Beware, that you can benefit from certain buildings based on what is the current round. The current round now is: " + gameState().round + ".",
                input -> {
                    try {
                        Integer n = Integer.parseInt(input);
                        if(1 <= gameState().round && gameState().round <= 3) {
                            if (n == firstBuilding) { return board().constructionSite().first(); }
                        }
                        else if(4 <= gameState().round && gameState().round <= 6) {
                            if (n == firstBuilding) { return board().constructionSite().first(); }
                            if (n == secondBuilding) { return board().constructionSite().second(); }
                        }
                        else if(7 <= gameState().round && gameState().round <= 9) {
                            if (n == firstBuilding) { return board().constructionSite().first(); }
                            if (n == secondBuilding) { return board().constructionSite().second(); }
                            if (n == thirdBuilding) { return board().constructionSite().third(); }
                        }

                    } catch(Exception ex) {}

                    return null;
                });
            
            return response;
        }

        // shoudln't happen ever
        return null;
    }
}