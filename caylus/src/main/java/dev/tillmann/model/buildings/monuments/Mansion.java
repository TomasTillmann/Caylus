package dev.tillmann.model.buildings.monuments;

import dev.tillmann.model.Monument;
import dev.tillmann.model.Resources;

public class Mansion extends Monument {
    @Override
    public void build() {
        owner().spend(cost());
        owner().awardPrestigePoints(20);
    }

    @Override
    public Resources cost() {
        return Resources.empty().addStone(5).addGold(5);
    }


    @Override
    public String name() {
        return "Mansion";
    }
}
