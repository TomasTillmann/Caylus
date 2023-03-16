package dev.tillmann.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import dev.tillmann.model.characters.*;

public class CharactersPile {
    public static CharactersPile Instance = new CharactersPile();

    private List<GameCharacter> characters = new ArrayList<>();
    private List<GameCharacter> charactersOut = new ArrayList<>();

    public CharactersPile() {
        setup();
    }

    public void setup() {
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

    public List<GameCharacter> getCharacters(Predicate<GameCharacter> predicate) {
        return getRandomCharacters(predicate, characters.size());
    }

    public List<GameCharacter> getRandomCharacters(Predicate<GameCharacter> predicate, int howMany) {
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

    public void returnCharacters(List<GameCharacter> charactersToReturn) {
        for(GameCharacter character : charactersToReturn) {
            returnCharacter(character);
        }
    }

    public void returnCharacter(GameCharacter character) {
        characters.add(character);
        charactersOut.remove(character);
    }
}