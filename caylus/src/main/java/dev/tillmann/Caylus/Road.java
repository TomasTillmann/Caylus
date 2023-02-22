package dev.tillmann.Caylus;

import dev.tillmann.Caylus.Buildings.Building;

public class Road {

    private int provost;
    public int provost() { return provost; }
    void setProvost(int provost) { this.provost = provost; }

    public Building building(int i) {
        throw new UnsupportedOperationException();
    }
}
