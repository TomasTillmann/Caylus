package dev.tillmann.Caylus;

import java.util.List;

import dev.tillmann.Model.Buildings.Building;
import dev.tillmann.Model.Buildings.WoodenBuilding;

public class CLI {
    public class Players {
        public List<Player> value;
    }

    public class PlayerPlan {
        public boolean passed;
        public Building building;
    }

    public class WoodenBuildingToBuild {
        public WoodenBuilding woodenBuilding;
    }

    public static List<Player> getPlayers() {
        throw new UnsupportedOperationException();
    }

    public static CLI.PlayerPlan getPlayerPlan(Player player) {
        throw new UnsupportedOperationException();
    }

    public static WoodenBuildingToBuild getWoodenBuildingToBuild(Player player) {
        throw new UnsupportedOperationException();
    }
}
