package dev.tillmann.Model.Buildings;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Residence;
import dev.tillmann.Model.Buildings.Starting.StartingBuilding;

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
}