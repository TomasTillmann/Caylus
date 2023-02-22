package dev.tillmann.Caylus;

import java.util.ArrayList;
import java.util.List;

import dev.tillmann.Model.Buildings.*;

public class Road {
    private int provostPosition;
    private Building[] buildings;
    private int lastBuildingPosition;

    public Road(Config config, ConstructionSite constructionSite, GuildsBridge guildsBridge) {
        buildings = new Building[config.roadSize];
        buildings[config.constructionSitePosition] = constructionSite;
        buildings[config.guildsBridgePosition] = guildsBridge;

        provostPosition = config.provostPosition;
    }

    public int provost() { return provostPosition; }
    void setProvostPosition(int provost) { this.provostPosition = provost; }

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
}
