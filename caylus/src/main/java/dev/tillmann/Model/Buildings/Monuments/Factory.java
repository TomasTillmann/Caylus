package dev.tillmann.model.buildings.monuments;

import dev.tillmann.model.Monument;
import dev.tillmann.model.Resources;

public class Factory extends Monument {
    @Override
    public void build() {
        owner().spend(cost());
        owner().awardPrestigePoints(5);
    }

    @Override
    public Resources cost() {
        return Resources.empty().addGold(1).addFabric(1);
    }

    @Override
    public String name() {
        return "Factory";
    }
}
