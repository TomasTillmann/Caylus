package dev.tillmann.Model.Buildings.Monuments;

import dev.tillmann.Model.Monument;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Cathedral extends Monument {
    @Override
    public void build() {
        owner().spend(Resources.empty().addGold(3).addStone(3));
        owner().awardPrestigePoints(15);
    }
}
