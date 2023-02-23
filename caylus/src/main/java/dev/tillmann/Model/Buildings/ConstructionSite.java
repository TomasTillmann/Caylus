package dev.tillmann.Model.Buildings;

import dev.tillmann.Model.Player;

public class ConstructionSite extends Building {
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
