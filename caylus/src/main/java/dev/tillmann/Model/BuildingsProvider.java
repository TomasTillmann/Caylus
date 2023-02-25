package dev.tillmann.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import dev.tillmann.Model.Buildings.Building;
import dev.tillmann.Model.Buildings.Starting.*;
import dev.tillmann.Model.Buildings.Stone.*;
import dev.tillmann.Model.Buildings.Wooden.*;
import dev.tillmann.Model.Buildings.YellowFlag.*;

public class BuildingsProvider {
    private static Board map;
    public static void provideBoard(Board map) {
        BuildingsProvider.map = map;
    }

    private static List<StartingBuilding> startingBuildings = new ArrayList<>();
    private static List<YellowFlagBuilding> yellowFlagBuildings = new ArrayList<>();
    private static List<WoodenBuilding> woodenBuildings = new ArrayList<>();
    private static List<StoneBuilding> stoneBuildings = new ArrayList<>();

    static {
        // set starting
        startingBuildings.add(new Carpenter(map));
        startingBuildings.add(new Toll(map));
        startingBuildings.add(new Fairground());
        startingBuildings.add(new Lawyer());
        //

        // set yellowFlag
        startingBuildings.add(new Farm());
        startingBuildings.add(new Guild3());
        startingBuildings.add(new Guild4());
        startingBuildings.add(new Market());
        startingBuildings.add(new Quarry());
        startingBuildings.add(new Sawmill());
        //

        // set wooden
        woodenBuildings.add(new CoveredMarket());
        woodenBuildings.add(new Manor());
        woodenBuildings.add(new SpinningMill());
        woodenBuildings.add(new Stonemason(map));
        woodenBuildings.add(new Tailor());
        woodenBuildings.add(new WoodenFarm());
        woodenBuildings.add(new WoodenMarket());
        woodenBuildings.add(new WoodenQuarry());
        woodenBuildings.add(new WoodenSawmill());
        //

        // set stone
        stoneBuildings.add(new Alchemist());
        stoneBuildings.add(new Church());
        stoneBuildings.add(new Foundry());
        stoneBuildings.add(new GoldMine());
        stoneBuildings.add(new Jeweler());
        stoneBuildings.add(new StoneFarm());
        stoneBuildings.add(new StoneGuild());
        stoneBuildings.add(new StoneQuarry());
        stoneBuildings.add(new StoneSawmill());
        //
    }

    public static List<StartingBuilding> getStarting() {
        return getStarting(startingBuildings.size());
    }

    public static List<StartingBuilding> getStarting(int howMany) {
        return getBuildings(howMany, b -> true, startingBuildings);
    }

    public static List<StartingBuilding> getStarting(Predicate<StartingBuilding> predicate) {
        return getBuildings(startingBuildings.size(), predicate, startingBuildings);
    }

    public static void returnStarting(List<StartingBuilding> buildings) {
        returnBuildings(buildings, startingBuildings);
    }

    public static List<WoodenBuilding> getWooden() {
        return getWooden(woodenBuildings.size());
    }

    public static List<WoodenBuilding> getWooden(int howMany) {
        return getBuildings(howMany, b -> true, woodenBuildings);
    }

    public static List<WoodenBuilding> getWooden(Predicate<WoodenBuilding> predicate) {
        return getBuildings(woodenBuildings.size(), predicate, woodenBuildings);
    }

    public static void returnWooden(List<WoodenBuilding> buildings) {
        returnBuildings(buildings, woodenBuildings);
    }

    public static List<StoneBuilding> getStone() {
        return getStone(stoneBuildings.size());
    }

    public static List<StoneBuilding> getStone(int howMany) {
        return getBuildings(howMany, b -> true, stoneBuildings);
    }

    public static List<StoneBuilding> getStone(Predicate<StoneBuilding> predicate) {
        return getBuildings(stoneBuildings.size(), predicate, stoneBuildings);
    }

    public static void returnStone(List<StoneBuilding> buildings) {
        returnBuildings(buildings, stoneBuildings);
    }

    public static List<YellowFlagBuilding> getYellowFlag() {
        return getYellowFlag(yellowFlagBuildings.size());
    }

    public static List<YellowFlagBuilding> getYellowFlag(int howMany) {
        return getBuildings(howMany, b -> true, yellowFlagBuildings);
    }

    public static List<YellowFlagBuilding> getYellowFlag(Predicate<YellowFlagBuilding> predicate) {
        return getBuildings(yellowFlagBuildings.size(), predicate, yellowFlagBuildings);
    }

    private static <T extends Building> List<T> getBuildings(int howMany, Predicate<T> predicate, List<T> toChooseFrom) {
        if(howMany < 0 || howMany > toChooseFrom.size()) { throw new UnsupportedOperationException(); }

        List<T> result = new ArrayList<T>(toChooseFrom).stream().filter(b -> predicate.test(b)).toList();
        Collections.shuffle(result);
        result = result.subList(0, Math.min(result.size(), howMany));

        for(T building : result) {
            startingBuildings.remove(building);
        }

        return result;
    }

    private static <T extends Building> void returnBuildings(List<T> buildings, List<T> whereToReturn) {
        for(T building : buildings) {
            whereToReturn.add(building);
        }
    }
}
