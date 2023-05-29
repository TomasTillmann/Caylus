package dev.tillmann.caylus.cli;

import java.util.List;

import dev.tillmann.model.Player;
import dev.tillmann.model.characters.GameCharacter;

public class CharacterResponse extends Response {
    public GameCharacter character;

    /**
     * Which character would the player like to choose.
     * 
     * @param player
     * @param characters
     * @return
     */
    public static CharacterResponse chooseCharacter(Player player, List<GameCharacter> characters) {
        CharacterResponse response = new CharacterResponse();
        visualizer.showCharacters(characters);
        visualizer.showWhoseTurnIs(player);

        response.character = getSanitizedInput(
                "Choose one of these characters.",
                "Choose by wrting the number, e.g 1.",
                input -> {
                    try {
                        Integer n = Integer.parseInt(input) - 1;
                        if (0 <= n && n < characters.size()) {
                            return characters.get(n);
                        }
                    } catch (NumberFormatException ex) {
                    }

                    return null;
                });

        visualizer.showDelimiter();
        return response;
    }
}
