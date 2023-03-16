package dev.tillmann.caylus.cli;

import java.util.List;

import dev.tillmann.model.Player;
import dev.tillmann.model.characters.GameCharacter;

public class CharacterResponse extends Response {
    public GameCharacter character;

    public static CharacterResponse chooseCharacter(Player player, List<GameCharacter> characters) {
        CharacterResponse response = new CharacterResponse();

        // show characters
        for(int i = 0; i < characters.size(); ++i) {
            visualizer.println((i + 1) + "." + characters.get(i).name());
        }
        //

        response.character = getSanitizedInput(
            "Choose one of these characters.",
            "Choose by wrting the number, e.g 1.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input) - 1;
                    if(0 <= n && n < characters.size()) {
                        return characters.get(n);
                    }
                } catch(Exception ex) {}

                return null;
            });

        return response;
    }
}