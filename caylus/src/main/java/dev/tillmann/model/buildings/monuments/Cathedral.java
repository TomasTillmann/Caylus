package dev.tillmann.model.buildings.monuments;

import dev.tillmann.model.Monument;
import dev.tillmann.model.Resources;

public class Cathedral extends Monument {
    @Override
    public void build() {
        owner().spend(cost());
        owner().awardPrestigePoints(15);
    }

    @Override
    public Resources cost() {
        return Resources.empty().addGold(3).addStone(3);
    }

    @Override
    public String name() {
        return "Cathedral";
    }
}
