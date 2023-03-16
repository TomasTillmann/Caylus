package dev.tillmann.model.buildings.monuments;

import dev.tillmann.model.Monument;
import dev.tillmann.model.Resources;

public class Statue extends Monument {
    @Override
    public void build() {
        owner().spend(cost());
        owner().awardPrestigePoints(7);
    }

    @Override
    public Resources cost() {
        return Resources.empty().addGold(1).addStone(2);
    }

    @Override
    public String name() {
        return "Statue";
    }
}
