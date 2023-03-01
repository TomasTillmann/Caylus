package dev.tillmann.model.buildings.monuments;

import dev.tillmann.model.Monument;
import dev.tillmann.model.Resources;

public class Theatre extends Monument {
    @Override
    public void build() {
        owner().spend(Resources.empty().addGold(3).addWood(4));
        owner().awardPrestigePoints(12);
        owner().getFavor();
    }
}
