package dev.tillmann.Model.Buildings;

public abstract class YellowFlagBuilding extends StartingBuilding {
    private boolean toResidence;

    public void markAsToResidence() {
        toResidence = true;
    }
}
