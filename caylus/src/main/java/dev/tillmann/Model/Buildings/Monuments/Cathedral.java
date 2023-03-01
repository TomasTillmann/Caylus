package dev.tillmann.model.buildings.monuments;

import dev.tillmann.model.Monument;
import dev.tillmann.model.Resources;

public class Cathedral extends Monument {
    @Override
    public void build() {
        owner().spend(Resources.empty().addGold(3).addStone(3));
        owner().awardPrestigePoints(15);
    }
}
