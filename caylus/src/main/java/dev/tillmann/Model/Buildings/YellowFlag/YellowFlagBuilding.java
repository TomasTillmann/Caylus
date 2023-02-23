package dev.tillmann.Model.Buildings.YellowFlag;

import dev.tillmann.Model.Buildings.Starting.StartingBuilding;

public abstract class YellowFlagBuilding extends StartingBuilding {
    private boolean toResidence;

    public void markAsToResidence() {
        toResidence = true;
    }
}
