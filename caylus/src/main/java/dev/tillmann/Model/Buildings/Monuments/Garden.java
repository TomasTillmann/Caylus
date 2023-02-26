package dev.tillmann.Model.Buildings.Monuments;

import dev.tillmann.Model.Monument;
import dev.tillmann.Model.Resources;

public class Garden extends Monument {
    @Override
    public void build() {
        owner().spend(Resources.empty().addGold(1).addWood(1));
        owner().awardPrestigePoints(4);
    }
}
