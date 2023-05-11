package dev.tillmann.caylus.cli;

import java.util.ArrayList;
import java.util.List;
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

        visualizer.showWhoseTurnIs(player);
        visualizer.println(String.format("%s got a favor!", player.name));

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

                visualizer.showDelimiter();
                return null;
            });
        
        if(option == steal) {
            visualizer.println(String.format("%s chose to steal.", player.name));
            response.stealCharacter = true;

            List<Player> playersToStealFrom = new ArrayList<Player>(players());
            playersToStealFrom.remove(player);
            visualizer.showPlayers(playersToStealFrom);
            response.playerStolenFrom = getSanitizedInput(
                "From which player would you like to steal?",
                "Write the player's name to select him.",
                input -> {
                    if(players().stream().anyMatch(p -> p.name.equals(input))) {
                        return players().stream().filter(p -> p.name.equals(input)).findFirst().get();
                    }

                    visualizer.showDelimiter();
                    return null;
            });
            
            visualizer.showCharacters(response.playerStolenFrom.characters());
            visualizer.showDelimiter();

            response.character = getSanitizedInput(
                "What character would you like to steal?",
                "Write the characters name to steal it.",
                input -> {
                    Optional<GameCharacter> character = response.playerStolenFrom.characters().stream().filter(ch -> ch.name().equals(input)).findFirst();
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

            visualizer.showConstructionSite(board().constructionSite());
            visualizer.println(String.format("%s chose to benefit from one of the buildings on the construction site.", player.name));

            response.building = getSanitizedInput(
                "Which building would you like to benefit from?",
                "Choose from 1 - 3. Beware, that you can benefit from certain buildings based on what is the current round. The current round now is: " + gameState().round + ".",
                input -> {
                    try {
                        Integer n = Integer.parseInt(input);
                        if(1 <= gameState().round && gameState().round <= 3) {
                            if (n.equals(firstBuilding)) { return board().constructionSite().first(); }
                        }
                        else if(4 <= gameState().round && gameState().round <= 6) {
                            if (n.equals(firstBuilding)) { return board().constructionSite().first(); }
                            if (n == secondBuilding) { return board().constructionSite().second(); }
                        }
                        else if(7 <= gameState().round && gameState().round <= 9) {
                            if (n.equals(firstBuilding)) { return board().constructionSite().first(); }
                            if (n.equals(secondBuilding)) { return board().constructionSite().second(); }
                            if (n.equals(thirdBuilding)) { return board().constructionSite().third(); }
                        }

                    } catch(Exception ex) {}

                    visualizer.showDelimiter();
                    return null;
                });
            
            visualizer.showDelimiter();
            return response;
        }

        // shoudln't happen ever
        return null;
    }
}