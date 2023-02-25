package dev.tillmann.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.tillmann.Model.Buildings.Building;
import dev.tillmann.Model.Buildings.Starting.*;
import dev.tillmann.Model.Buildings.Stone.*;
import dev.tillmann.Model.Buildings.Wooden.*;
import dev.tillmann.Model.Buildings.YellowFlag.*;

public class BuildingsProvider {
    private static Map map;
    public static void setMap(Map map) {
        BuildingsProvider.map = map;
    }

    private static List<StartingBuilding> startingBuildings = new ArrayList<>();
    private static List<WoodenBuilding> woodenBuildings = new ArrayList<>();
    private static List<StoneBuilding> stoneBuildings = new ArrayList<>();

    public BuildingsProvider() {
        // set starting
        startingBuildings.add(new Carpenter(map));
        startingBuildings.add(new Toll(map));
        startingBuildings.add(new GuildsBridge(map));
        startingBuildings.add(new Fairground());
        startingBuildings.add(new Lawyer());
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
        return getBuilding(howMany, startingBuildings);
    }

    public static List<WoodenBuilding> getWooden() {
        return getWooden(woodenBuildings.size());
    }

    public static void returnStarting(List<StartingBuilding> buildings) {
        returnBuildings(buildings, startingBuildings);
    }

    public static List<WoodenBuilding> getWooden(int howMany) {
        return getBuilding(howMany, woodenBuildings);
    }

    public static void returnWooden(List<WoodenBuilding> buildings) {
        returnBuildings(buildings, woodenBuildings);
    }

    public static List<StoneBuilding> getStone() {
        return getStone(stoneBuildings.size());
    }

    public static List<StoneBuilding> getStone(int howMany) {
        return getBuilding(howMany, stoneBuildings);
    }

    public static void returnStone(List<StoneBuilding> buildings) {
        returnBuildings(buildings, stoneBuildings);
    }

    private static <T extends Building> List<T> getBuilding(int howMany, List<T> toChooseFrom) {
        if(howMany < 0 || howMany >= toChooseFrom.size()) { throw new UnsupportedOperationException(); }

        List<T> result = new ArrayList<T>(toChooseFrom);
        Collections.shuffle(result);
        result = result.subList(0, howMany);

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
