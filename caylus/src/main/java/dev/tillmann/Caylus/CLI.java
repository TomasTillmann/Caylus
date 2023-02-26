package dev.tillmann.Caylus;

import java.util.List;
import java.util.Map;

import dev.tillmann.Model.GameCharacter;
import dev.tillmann.Model.Monument;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Residence;
import dev.tillmann.Model.Resources;
import dev.tillmann.Model.Buildings.Building;
import dev.tillmann.Model.Buildings.ConstructionSite;
import dev.tillmann.Model.Buildings.YellowFlagBuilding;
import dev.tillmann.Model.Buildings.Stone.StoneBuilding;
import dev.tillmann.Model.Buildings.Wooden.CoveredMarket;
import dev.tillmann.Model.Buildings.Wooden.WoodenBuilding;

public class CLI {
    public class PlayersResponse {
        public List<Player> value;
    }

    public class PlayerPlanResponse {
        public boolean passed;
        public Building building;
        public boolean constructionSite;
    }

    public class WoodenBuildingToBuildResponse {
        public WoodenBuilding woodenBuilding;
    }

    public class StoneBuildingToBuildResponse {
        public StoneBuilding stoneBuilding;
    }

    public class FavorResponse {
        public boolean stealCharacter;
        public GameCharacter character;
        public Player playerStolenFrom;
        public Building building;
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

    public class OptionResponse {
        public int option;
    }

    public class CharacterResponse {
        public GameCharacter character;
    }

    public class ConstructionSiteResponse {
        public List<Resources> bundles;
    }

    public class ToMonumentsResponse {
        public Map<Residence, Monument> residenceToMonument;
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

    public static CLI.FavorResponse getFavor(Player player, int round, ConstructionSite constructionSite) {
        throw new UnsupportedOperationException();
    }

    public static BuildingToOwnResponse getBuildingToOwn(Player player) {
        throw new UnsupportedOperationException();
    }

    public static CLI.ProvostPositionResponse getProvostPosition(Player player) {
        throw new UnsupportedOperationException();
    }

    public static ResourcesResponse getOneResource() {
        throw new UnsupportedOperationException();
    }

    public static ResourcesResponse getWoodenBuildingResources(CoveredMarket coveredMarket) {
        throw new UnsupportedOperationException();
    }

    public static StoneBuildingToBuildResponse getStoneBuildingToBuild(Player player) {
        throw new UnsupportedOperationException();
    }

    public static OptionResponse getTailorResponse(Player player) {
        throw new UnsupportedOperationException();
    }

    public static OptionResponse getFoundryOption() {
        throw new UnsupportedOperationException();
    }

    public static OptionResponse getAlchemistOption() {
        throw new UnsupportedOperationException();
    }

    public static OptionResponse getChurchOption() {
        throw new UnsupportedOperationException();
    }

    public static OptionResponse getJewelerOption() {
        throw new UnsupportedOperationException();
    }

    public static CharacterResponse chooseCharacter(Player player, List<GameCharacter> characters) {
        throw new UnsupportedOperationException();
    }

    public static ConstructionSiteResponse getConstructionSiteResponse(Player player) {
        throw new UnsupportedOperationException();
    }

    public static CLI.ToMonumentsResponse getResidencesToMonuments() {
        throw new UnsupportedOperationException();
    }

    public static void showResults(List<Player> players) {
        throw new UnsupportedOperationException();
    }
}
