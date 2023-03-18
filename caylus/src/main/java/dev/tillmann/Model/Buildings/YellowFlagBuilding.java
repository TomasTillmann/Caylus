package dev.tillmann.model.buildings;

import java.util.List;

import dev.tillmann.model.Player;
import dev.tillmann.model.Residence;
import dev.tillmann.model.buildings.starting.StartingBuilding;

public class YellowFlagBuilding extends StartingBuilding {
    private StartingBuilding building;
    public StartingBuilding building() { return building; }

    public YellowFlagBuilding(StartingBuilding building) {
        this.building = building;
    }

    @Override
    protected void activatePlayer(Player player) {
        building.activatePlayer(player);
    }

    @Override
    public String description() {
        return building.description();
    }

    @Override
    public List<Player> plannedPlayers() { return building.plannedPlayers(); }

    @Override
    public int workersCost() {
        return building.workersCost();
    }

    @Override
    public void activate() {
        building.activate();
    }

    public Residence toResidence() {
        if(!hasOwner()) {
            throw new UnsupportedOperationException();
        }

        return new Residence(this);
    }

    @Override
    public String name() {
        return "Yellow flag " + building.name();
    }
}