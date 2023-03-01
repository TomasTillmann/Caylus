package dev.tillmann.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.tillmann.caylus.cli.CLI;
import dev.tillmann.model.buildings.*;
import dev.tillmann.model.buildings.starting.*;
import dev.tillmann.model.buildings.stone.StoneBuilding;
import dev.tillmann.model.buildings.wooden.Stonemason;
import dev.tillmann.model.buildings.wooden.WoodenBuilding;

public class Road {
    private int provostPosition;
    private Building[] buildings;
    private int lastBuildingPosition = WOODEN_BUILDING_POSITION;

    private List<Residence> residences = new ArrayList<>();
    public List<Residence> residences() { return Collections.unmodifiableList(residences); }

    private List<Monument> monuments = new ArrayList<>();
    public List<Monument> monuments() { return Collections.unmodifiableList(monuments); }


    public static final int ROAD_SIZE = 29;
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
        // update last building position
        lastBuildingPosition += lastBuildingPosition == STONE_BUILDING_POSITION - 1 ? 2 : 1;
        //

        buildings[lastBuildingPosition] = building;
    }

    public void updateProvostAfterRound() {
        provostPosition = lastBuildingPosition + 1;
    }

    private void setupStartingBuildings() {
        buildings[FAIRGROUND_POSITION] = BuildingsProvider.getRandomBuildings(b -> b instanceof Fairground, 1).get(0);
        buildings[LAWYER_POSITION] = BuildingsProvider.getRandomBuildings(b -> b instanceof Lawyer, 1).get(0);
        buildings[CARPENTER_POSITION] = BuildingsProvider.getRandomBuildings(b -> b instanceof Fairground, 1).get(0);
        buildings[TOLL_POSITION] = BuildingsProvider.getRandomBuildings(b -> b instanceof Fairground, 1).get(0);
        buildings[GUILDS_BRIDGE_POSITION] = BuildingsProvider.getRandomBuildings(b -> b instanceof Fairground, 1).get(0);
    }

    private void setupYellowFlagBuildings() {
        List<Building> startingBuildings = BuildingsProvider.getBuildings(b -> b instanceof YellowFlagBuilding);
        assert startingBuildings.size() == YELLOW_FLAG_BUILDINGS_END - YELLOW_FLAG_BUILDINGS_START;

        Collections.shuffle(startingBuildings);

        for(int i = YELLOW_FLAG_BUILDINGS_START; i < YELLOW_FLAG_BUILDINGS_END; ++i) {
            buildings[i] = startingBuildings.get(i - YELLOW_FLAG_BUILDINGS_START);
        }
    }

    private void setupWoodenBuilding() {
        buildings[WOODEN_BUILDING_POSITION] = BuildingsProvider.getRandomBuildings(b -> (b instanceof WoodenBuilding) && !(b instanceof Stonemason), 1).get(0);
    }

    private void setupStoneBuilding() {
        buildings[STONE_BUILDING_POSITION] = BuildingsProvider.getRandomBuildings(b -> b instanceof StoneBuilding, 1).get(0);
    }

    public void yellowFlagToResidences() {
        YellowFlagBuilding building; 
        Residence residence;

        for(int i = Road.YELLOW_FLAG_BUILDINGS_START; i < Road.YELLOW_FLAG_BUILDINGS_END; ++i) {
            building = (YellowFlagBuilding)buildings[i];
            if(building.hasOwner()) {
                residence = building.toResidence();

                residences.add(residence);
                building.owner().ownedResidences().add(residence);

                buildings[i] = null;
            }
        }
    }

    public void residencesToMonuments() {
        Residence residence;
        Monument monument;

        for(int i = 0; i < residences.size(); ++i) {
            residence = residences.get(i);
            CLI.MonumentResponse response = CLI.instance().toMonument(residence);

            if(response.monument != null) {
                monument = residence.toMonument(response.monument);

                monuments.add(monument);
                residence.owner().ownedMonuments().add(monument);

                monument.build();

                residences.remove(i);
                i--;
            }
        }
    }
}
