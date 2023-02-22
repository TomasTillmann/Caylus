package dev.tillmann.Model.Buildings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.tillmann.Caylus.Player;

public class GuildsBridge extends StartingBuilding {
    private List<Player> passedPlayers = new ArrayList<>();

    @Override
    public void activate() {
        throw new UnsupportedOperationException("Unimplemented method 'activate'");
    }

    public void passed(Player player) {
        passedPlayers.add(player);
    }

    public List<Player> passedPlayers() {
        return Collections.unmodifiableList(passedPlayers);
    }
}
