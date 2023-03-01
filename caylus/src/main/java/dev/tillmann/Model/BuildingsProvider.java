package dev.tillmann.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import dev.tillmann.model.buildings.Building;
import dev.tillmann.model.buildings.YellowFlagBuilding;
import dev.tillmann.model.buildings.starting.*;
import dev.tillmann.model.buildings.stone.*;
import dev.tillmann.model.buildings.wooden.*;

public class BuildingsProvider {
    private static Board map;
    public static void provideBoard(Board map) {
        BuildingsProvider.map = map;
    }

    private static List<Building> buildings = new ArrayList<>();
    private static List<Building> buildingsOut = new ArrayList<>();

    static {
        // set starting
        buildings.add(new Carpenter(map));
        buildings.add(new Toll(map));
        buildings.add(new Fairground());
        buildings.add(new Lawyer());
        //

        // set yellowFlag
        buildings.add(new YellowFlagBuilding(new Farm()));
        buildings.add(new YellowFlagBuilding(new Guild3()));
        buildings.add(new YellowFlagBuilding(new Carpenter(map)));
        buildings.add(new YellowFlagBuilding(new Guild4()));
        buildings.add(new YellowFlagBuilding(new Market()));
        buildings.add(new YellowFlagBuilding(new Quarry()));
        buildings.add(new YellowFlagBuilding(new Sawmill()));
        //

        // set wooden
        buildings.add(new CoveredMarket());
        buildings.add(new Manor());
        buildings.add(new SpinningMill());
        buildings.add(new Stonemason(map));
        buildings.add(new Tailor());
        buildings.add(new WoodenFarm());
        buildings.add(new WoodenMarket());
        buildings.add(new WoodenQuarry());
        buildings.add(new WoodenSawmill());
        //

        // set stone
        buildings.add(new Alchemist());
        buildings.add(new Church());
        buildings.add(new Foundry());
        buildings.add(new GoldMine());
        buildings.add(new Jeweler());
        buildings.add(new StoneFarm());
        buildings.add(new StoneGuild());
        buildings.add(new StoneQuarry());
        buildings.add(new StoneSawmill());
        //
    }

    public static List<Building> getBuildings(Predicate<Building> predicate) {
        return getBuildings(predicate, buildings.size());
    }

    public static List<Building> getBuildings(Predicate<Building> predicate, int howMany) {
        if(howMany < 0) { throw new UnsupportedOperationException(); }

        List<Building> result = new ArrayList<Building>(buildings).stream().filter(b -> predicate.test(b)).toList();
        Collections.shuffle(result);
        result = result.subList(0, Math.min(result.size(), howMany));

        for(Building building : result) {
            buildings.remove(building);
            buildingsOut.add(building);
        }

        return result;
    }

    public static void returnBuildings(List<Building> buildings) {
        for(Building building : buildings) {
            buildings.add(building);
            buildingsOut.remove(building);
        }
    }
}
