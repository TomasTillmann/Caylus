package dev.tillmann.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharactersProvider {
    private static List<GameCharacter> allCharacters;

    static {
    }

    public static List<GameCharacter> getRandom(int howMany) {
        if(howMany < 0 || howMany > allCharacters.size()) { throw new UnsupportedOperationException(); }

        List<GameCharacter> result = new ArrayList<>(allCharacters);
        Collections.shuffle(result);
        return result.subList(0, howMany);
    }
}
