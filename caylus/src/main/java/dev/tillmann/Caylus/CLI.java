package dev.tillmann.Caylus;

import java.util.List;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;
import dev.tillmann.Model.Buildings.Building;
import dev.tillmann.Model.Buildings.CoveredMarket;
import dev.tillmann.Model.Buildings.WoodenBuilding;
import dev.tillmann.Model.Buildings.YellowFlagBuilding;

public class CLI {
    public class PlayersResponse {
        public List<Player> value;
    }

    public class PlayerPlanResponse {
        public boolean passed;
        public Building building;
    }

    public class WoodenBuildingToBuildResponse {
        public WoodenBuilding woodenBuilding;
    }

    public class FavorResponse {
    }

    public class BuildingToOwnResponse {
        public YellowFlagBuilding building;
    }

    public class ProvostPositionResponse {
        public int provostNewPosition;
        public int provostDifference;
    }

    public class ResourcesResponse {
        public Resources resources;
    }

    public static PlayersResponse getPlayers() {
        throw new UnsupportedOperationException();
    }

    public static CLI.PlayerPlanResponse getPlayerPlan(Player player) {
        throw new UnsupportedOperationException();
    }

    public static WoodenBuildingToBuildResponse getWoodenBuildingToBuild(Player player) {
        throw new UnsupportedOperationException();
    }

    public static CLI.FavorResponse getFavor(Player player) {
        throw new UnsupportedOperationException();
    }

    public static BuildingToOwnResponse getBuildingToOwn(Player player) {
        throw new UnsupportedOperationException();
    }

    public static CLI.ProvostPositionResponse getProvostPosition(Player player) {
        throw new UnsupportedOperationException();
    }

    public static ResourcesResponse getOneResourceGain(Player player) {
        throw new UnsupportedOperationException();
    }

    public static ResourcesResponse spendOneResource(Player player) {
        throw new UnsupportedOperationException();
    }

    public static ResourcesResponse getWoodenBuildingResources(CoveredMarket coveredMarket) {
        throw new UnsupportedOperationException();
    }
}
