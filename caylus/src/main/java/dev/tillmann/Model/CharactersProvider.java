package dev.tillmann.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import dev.tillmann.model.characters.*;

public class CharactersProvider {
    private static List<GameCharacter> characters;

    static {
        characters.add(new EarlyRiser());
        characters.add(new NightWorker());
        characters.add(new Journeyman());
        characters.add(new Thief());
        characters.add(new Bailiff());
        characters.add(new Architect());
        characters.add(new Deliveryman());
        characters.add(new Chamberlain());
        characters.add(new Foreman());
        characters.add(new Goldsmith());
        characters.add(new Daylaborer());
    }

    public static List<GameCharacter> getCharacters(Predicate<GameCharacter> predicate) {
        return getRandomCharacters(predicate, characters.size());
    }

    public static List<GameCharacter> getRandomCharacters(Predicate<GameCharacter> predicate, int howMany) {
        if(howMany < 0) { throw new UnsupportedOperationException(); }

        List<GameCharacter> result = new ArrayList<>(characters).stream().filter(ch -> predicate.test(ch)).toList();
        Collections.shuffle(result);

        return result.subList(0, Math.min(howMany, result.size()));
    }
}