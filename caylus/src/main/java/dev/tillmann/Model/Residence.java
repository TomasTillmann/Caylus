package dev.tillmann.Model;

import dev.tillmann.Model.Buildings.YellowFlagBuilding;

public class Residence {
    private YellowFlagBuilding yellowFlagBuilding;

    public Residence(YellowFlagBuilding yellowFlagBuilding) {
        this.yellowFlagBuilding = yellowFlagBuilding;
    }

    public Player owner() {
        return yellowFlagBuilding.owner();
    }

    public Monument toMonument(Monument monument) {
        monument.setResidence(this);
        return monument;
    }
}