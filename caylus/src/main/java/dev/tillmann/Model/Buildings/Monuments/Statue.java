package dev.tillmann.model.buildings.monuments;

import dev.tillmann.model.Monument;
import dev.tillmann.model.Resources;

public class Statue extends Monument {
    @Override
    public void build() {
        owner().spend(Resources.empty().addGold(1).addStone(2));
        owner().awardPrestigePoints(7);
    }
}
