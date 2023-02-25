package dev.tillmann.Model;

import java.util.Collections;
import java.util.List;

import dev.tillmann.Model.Buildings.*;
import dev.tillmann.Model.Buildings.Starting.GuildsBridge;
import dev.tillmann.Model.Buildings.Starting.StartingBuilding;

public class Road {
    private int provostPosition;
    private Building[] buildings;
    private int lastBuildingPosition;

    private static final int ROAD_SIZE = 29;
    private static final int CONSTRUCTION_SITE_POS = ROAD_SIZE - 1;
    private static final int GUILDS_BRIDGE_POS = 4;
    private static final int PROVOST_POSITION = 13;

    public int size() { return buildings.length; }

    public Road(ConstructionSite constructionSite, GuildsBridge guildsBridge) {
        buildings = new Building[ROAD_SIZE];
        buildings[CONSTRUCTION_SITE_POS] = constructionSite;
        buildings[GUILDS_BRIDGE_POS] = guildsBridge;
        provostPosition = PROVOST_POSITION; 

        setupStartingBuildings();
    }

    public int provost() { return provostPosition; }
    public void setProvost(int provost) { this.provostPosition = provost; }

    public Building building(int i) {
        if(i < 0 || i >= buildings.length) {
            throw new IllegalArgumentException("outside of bounds of the array");
        }

        return buildings[i];
    }

    public void build(Building building) {
        updateLastBuildingPosition();
        buildings[lastBuildingPosition] = building;
    }

    private void updateLastBuildingPosition() {
        throw new UnsupportedOperationException();
    }

    private void setupStartingBuildings() {
        List<StartingBuilding> startingBuildings = BuildingsProvider.getStarting();
        Collections.shuffle(startingBuildings);
    }
}
