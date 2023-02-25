package dev.tillmann.Model.Buildings;

import dev.tillmann.Model.BuildingsProvider;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Buildings.Starting.StartingBuilding;
import dev.tillmann.Model.Buildings.Stone.StoneBuilding;
import dev.tillmann.Model.Buildings.Wooden.WoodenBuilding;

public class ConstructionSite extends Building {
    private StartingBuilding first;
    private WoodenBuilding second;
    private StoneBuilding third;

    public ConstructionSite() {
        first = BuildingsProvider.getStarting(1).get(0);
        second = BuildingsProvider.getWooden(1).get(0);
        third = BuildingsProvider.getStone(1).get(0);
    }

    @Override
    public void activate() {
        throw new UnsupportedOperationException("Unimplemented method 'activate'");
    }

    public void deliver() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void activatePlayer(Player player) {
        throw new UnsupportedOperationException("Unimplemented method 'activatePlayer'");
    }
}
