package dev.tillmann.Model.Buildings.Monuments;

import dev.tillmann.Model.Monument;
import dev.tillmann.Model.Resources;

public class Mansion extends Monument {
    @Override
    public void build() {
        owner().spend(Resources.empty().addStone(5).addGold(5));
        owner().awardPrestigePoints(20);
    }
}
