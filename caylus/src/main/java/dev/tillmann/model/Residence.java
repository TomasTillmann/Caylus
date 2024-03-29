package dev.tillmann.model;

import dev.tillmann.model.buildings.YellowFlagBuilding;

public class Residence {
    private YellowFlagBuilding yellowFlagBuilding;

    public Residence(YellowFlagBuilding yellowFlagBuilding) {
        this.yellowFlagBuilding = yellowFlagBuilding;
    }

    public String name() {
        return "Residence " + yellowFlagBuilding.building().name();
    }

    public Player owner() {
        return yellowFlagBuilding.owner();
    }

    public Monument toMonument(Monument monument) {
        monument.setResidence(this);
        return monument;
    }
}