package dev.tillmann.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dev.tillmann.model.buildings.monuments.*;

public class MonumentsPile {
    public static MonumentsPile Instance = new MonumentsPile();

    private List<Monument> monuments = new ArrayList<>();
    public List<Monument> remainingMonuments() { return Collections.unmodifiableList(monuments); }

    private List<Monument> monumentsOut = new ArrayList<>();

    public MonumentsPile() {
        setup();
    }

    public void setup() {
        monuments.add(new Cathedral());
        monuments.add(new Factory());
        monuments.add(new Garden());
        monuments.add(new Granary());
        monuments.add(new Mansion());
        monuments.add(new Statue());
        monuments.add(new Theatre());
    }

    public List<Monument> getMonuments(Predicate<Monument> predicate) {
        return getRandomMonuments(predicate, monuments.size());
    }

    public List<Monument> getRandomMonuments(Predicate<Monument> predicate, int howMany) {
        if(howMany < 0) { throw new UnsupportedOperationException(); }

        List<Monument> result = new ArrayList<>(monuments.stream().filter(ch -> predicate.test(ch)).collect(Collectors.toList()));
        Collections.shuffle(result);
        result = result.subList(0, Math.min(howMany, result.size()));

        for(Monument monument : monuments) {
            monuments.remove(monument);
            monumentsOut.add(monument);
        }

        return result;
    }

    public void returnMonuments(List<Monument> monumentsToReturn) {
        for(Monument monument : monumentsToReturn) {
            returnMonument(monument);
        }
    }

    public void returnMonument(Monument monument) {
        monuments.add(monument);
        monumentsOut.remove(monument);
    }
}
