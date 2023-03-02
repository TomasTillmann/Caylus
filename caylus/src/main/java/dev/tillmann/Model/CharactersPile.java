package dev.tillmann.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import dev.tillmann.model.characters.*;

public class CharactersPile {
    private static List<GameCharacter> characters = new ArrayList<>();
    private static List<GameCharacter> charactersOut = new ArrayList<>();

    static {
        characters.add(new Earlyriser());
        characters.add(new Nightworker());
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

        List<GameCharacter> result = new ArrayList<>(characters.stream().filter(ch -> predicate.test(ch)).toList());
        Collections.shuffle(result);
        result = result.subList(0, Math.min(howMany, result.size()));

        for(GameCharacter character : result) {
            characters.remove(character);
            charactersOut.add(character);
        }

        return result;
    }

    public static void returnCharacters(List<GameCharacter> charactersToReturn) {
        for(GameCharacter character : charactersToReturn) {
            characters.add(character);
            charactersOut.remove(character);
        }
    }
}