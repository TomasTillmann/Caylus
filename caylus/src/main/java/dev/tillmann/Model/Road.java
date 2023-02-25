package dev.tillmann.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.tillmann.Model.Buildings.*;
import dev.tillmann.Model.Buildings.Starting.*;
import dev.tillmann.Model.Buildings.Wooden.Stonemason;
import dev.tillmann.Model.Buildings.YellowFlag.*;

public class Road {
    private int provostPosition;
    private Building[] buildings;
    private int lastBuildingPosition;

    private static final int ROAD_SIZE = 29;
    public static final int START = 0;
    public static final int CONSTRUCTION_SITE_POS = ROAD_SIZE - 1;
    public static final int GUILDS_BRIDGE_POS = 4;
    public static final int PROVOST_POSITION = 12;
    public static final int FAIRGROUND_POSITION = 0;
    public static final int LAWYER_POSITION = 1;
    public static final int CARPENTER_POSITION = 2;
    public static final int TOLL_POSITION = 3;
    public static final int GUILDS_BRIDGE_POSITION = 4;
    public static final int YELLOW_FLAG_BUILDINGS_START = 5;
    public static final int YELLOW_FLAG_BUILDINGS_END = 13;
    public static final int WOODEN_BUILDING_POSITION = 13;
    public static final int STONE_BUILDING_POSITION = 18;

    public int size() { return buildings.length; }

    public int provost() { return provostPosition; }
    public void setProvost(int provost) { this.provostPosition = provost; }

    public Road(GuildsBridge guildsBridge) {
        buildings = new Building[ROAD_SIZE];
        buildings[GUILDS_BRIDGE_POS] = guildsBridge;
        provostPosition = PROVOST_POSITION; 

        setupStartingBuildings();
        setupYellowFlagBuildings();
        setupWoodenBuilding();
        setupStoneBuilding();
    }

    public Building building(int i) {
        if(i < START || i >= buildings.length) {
            throw new IllegalArgumentException();
        }

        return buildings[i];
    }

    public List<Building> buildings(int from, int to) {
        if(from < START || to > this.buildings.length) { throw new IllegalArgumentException(); }

        List<Building> buildings = new ArrayList<>();
        for(int i = from; i < to; ++i) {
            if(this.buildings[i] != null) {
                buildings.add(this.buildings[i]);
            }
        }

        return buildings;
    }

    public void build(Building building) {
        updateLastBuildingPosition();
        buildings[lastBuildingPosition] = building;
    }

    private void setupStartingBuildings() {
        buildings[FAIRGROUND_POSITION] = BuildingsProvider.getStarting(b -> b instanceof Fairground).get(0);
        buildings[LAWYER_POSITION] = BuildingsProvider.getStarting(b -> b instanceof Lawyer).get(0);
        buildings[CARPENTER_POSITION] = BuildingsProvider.getStarting(b -> b instanceof Fairground).get(0);
        buildings[TOLL_POSITION] = BuildingsProvider.getStarting(b -> b instanceof Fairground).get(0);
        buildings[GUILDS_BRIDGE_POSITION] = BuildingsProvider.getStarting(b -> b instanceof Fairground).get(0);
    }

    private void updateLastBuildingPosition() {
        throw new UnsupportedOperationException();
    }

    private void setupYellowFlagBuildings() {
        List<YellowFlagBuilding> startingBuildings = BuildingsProvider.getYellowFlag();
        assert startingBuildings.size() == YELLOW_FLAG_BUILDINGS_END - YELLOW_FLAG_BUILDINGS_START;

        Collections.shuffle(startingBuildings);

        for(int i = YELLOW_FLAG_BUILDINGS_START; i < YELLOW_FLAG_BUILDINGS_END; ++i) {
            buildings[i] = startingBuildings.get(i - YELLOW_FLAG_BUILDINGS_START);
        }
    }

    private void setupWoodenBuilding() {
        buildings[WOODEN_BUILDING_POSITION] = BuildingsProvider.getWooden(b -> !(b instanceof Stonemason)).get(0);
    }

    private void setupStoneBuilding() {
        buildings[STONE_BUILDING_POSITION] = BuildingsProvider.getStone(1).get(0);
    }
}
