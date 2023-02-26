package dev.tillmann.Model.Buildings.Monuments;

import dev.tillmann.Model.Monument;
import dev.tillmann.Model.Resources;

public class Statue extends Monument {
    @Override
    public void build() {
        owner().spend(Resources.empty().addGold(1).addStone(2));
        owner().awardPrestigePoints(7);
    }
}
