package dev.tillmann.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dev.tillmann.model.buildings.Building;
import dev.tillmann.model.buildings.YellowFlagBuilding;
import dev.tillmann.model.buildings.starting.*;
import dev.tillmann.model.buildings.stone.*;
import dev.tillmann.model.buildings.wooden.*;

/**
All building that can be build are on this pile.
 */
public class BuildingsPile {
    public static BuildingsPile Instance = new BuildingsPile();

    public void setupBuildings(Board board) {
        // set starting
        buildings.add(new Carpenter(board));
        buildings.add(new Toll(board));
        buildings.add(new Fairground());
        buildings.add(new Lawyer());
        //

        // set yellowFlag
        buildings.add(new YellowFlagBuilding(new Farm()));
        buildings.add(new YellowFlagBuilding(new Farm()));
        buildings.add(new YellowFlagBuilding(new Guild3()));
        buildings.add(new YellowFlagBuilding(new Carpenter(board)));
        buildings.add(new YellowFlagBuilding(new Guild4()));
        buildings.add(new YellowFlagBuilding(new Market()));
        buildings.add(new YellowFlagBuilding(new Market()));
        buildings.add(new YellowFlagBuilding(new Quarry()));
        buildings.add(new YellowFlagBuilding(new Sawmill()));
        //

        // set wooden
        buildings.add(new CoveredMarket());
        buildings.add(new Manor());
        buildings.add(new SpinningMill());
        buildings.add(new Stonemason(board));
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


    private List<Building> buildings = new ArrayList<>();
    public List<Building> remainingBuildings() { return Collections.unmodifiableList(buildings); }

    private List<Building> buildingsOut = new ArrayList<>();

    /**
    Draws all buildings which satisfy given condition.
     */
    public List<Building> getBuildings(Predicate<Building> predicate) {
        return getRandomBuildings(predicate, buildings.size());
    }

    /**
    Returns {@code howMany} random buildings which satisfy given condition
     @param howMany how many buildings to draw
     @param predicate predicate the drawn buildings must hold
     */
    public List<Building> getRandomBuildings(Predicate<Building> predicate, int howMany) {
        if(howMany < 0) { throw new UnsupportedOperationException(); }

        List<Building> result = new ArrayList<Building>(buildings.stream().filter(b -> predicate.test(b)).collect(Collectors.toList()));
        Collections.shuffle(result);
        result = result.subList(0, Math.min(result.size(), howMany));

        for(Building building : result) {
            buildings.remove(building);
            buildingsOut.add(building);
        }

        return result;
    }

    public void returnBuildings(List<Building> buildingsToReturn) {
        for(Building building : buildingsToReturn) {
            returnBuilding(building);
        }
    }

    public void returnBuilding(Building building) {
        buildings.add(building);
        buildingsOut.remove(building);
    }
}
