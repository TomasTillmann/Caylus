package dev.tillmann.model.buildings.monuments;

import dev.tillmann.model.Monument;
import dev.tillmann.model.Resources;

public class Garden extends Monument {
    @Override
    public void build() {
        owner().spend(cost());
        owner().awardPrestigePoints(4);
    }

    @Override
    public Resources cost() {
        return Resources.empty().addGold(1).addWood(1);
    }

    @Override
    public String name() {
        return "Garden";
    }
}
