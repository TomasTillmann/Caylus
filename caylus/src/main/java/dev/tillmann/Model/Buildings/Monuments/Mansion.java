package dev.tillmann.model.buildings.monuments;

import dev.tillmann.model.Monument;
import dev.tillmann.model.Resources;

public class Mansion extends Monument {
    @Override
    public void build() {
        owner().spend(Resources.empty().addStone(5).addGold(5));
        owner().awardPrestigePoints(20);
    }
}
