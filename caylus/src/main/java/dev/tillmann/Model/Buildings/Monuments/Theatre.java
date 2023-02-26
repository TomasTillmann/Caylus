package dev.tillmann.Model.Buildings.Monuments;

import dev.tillmann.Model.Monument;
import dev.tillmann.Model.Resources;

public class Theatre extends Monument {
    @Override
    public void build() {
        owner().spend(Resources.empty().addGold(3).addWood(4));
        owner().awardPrestigePoints(12);
        owner().getFavor();
    }
}
