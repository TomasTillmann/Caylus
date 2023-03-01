package dev.tillmann.caylus.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import dev.tillmann.model.GameCharacter;
import dev.tillmann.model.Monument;
import dev.tillmann.model.Player;
import dev.tillmann.model.Residence;
import dev.tillmann.model.Resources;
import dev.tillmann.model.buildings.Building;
import dev.tillmann.model.buildings.ConstructionSite;
import dev.tillmann.model.buildings.YellowFlagBuilding;
import dev.tillmann.model.buildings.stone.StoneBuilding;
import dev.tillmann.model.buildings.wooden.CoveredMarket;
import dev.tillmann.model.buildings.wooden.WoodenBuilding;

public final class CLI {
    private static PrintStream out = System.out;
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private static CLI instance = new CLI();
    public static CLI instance() { return instance; }
    
    private CLI() { }

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
        public Monument monument;
    }

    private void shutdown() {
        out.println("Oh, something went wrong!");
        System.exit(1);
    }

    private void greetPlayers() {
        out.println("Welcome to Caylus1303!");
    }

    private Integer tryGetPlayersCount(String players) {
        try {
            int count = Integer.parseInt(players);
            if(1 <= count && count <= 5) {
                return count;
            }
        } finally {}

        return null;
    }

    private boolean isPlayerNameValid(String playerName) {
        return !playerName.isBlank() && !playerName.contains("\\s") && playerName.length() <= 30;
    }

    public PlayersResponse getPlayers() {
        greetPlayers();
        String playersCountRaw;
        Integer playersCount;
        String playerName;
        PlayersResponse response = new PlayersResponse();
        response.value = new ArrayList<>();

        try{

            do {
            out.println("Choose number of players to play between 1-5.");
            playersCountRaw = in.readLine();

            } while((playersCount = tryGetPlayersCount(playersCountRaw)) == null);

            for(int i = 0; i < playersCount; ++i) {
                out.println("Write name for player number " + i + ".");
                Player player = new Player();

                do {
                    playerName = in.readLine();
                } while(isPlayerNameValid(playerName));

                player.name = playerName;
                response.value.add(player);
            }
        }
        catch(IOException ex) { shutdown(); }

        return response;
    }

    public CLI.PlayerPlanResponse getPlayerPlan(Player player) {
        throw new UnsupportedOperationException();
    }

    public WoodenBuildingToBuildResponse getWoodenBuildingToBuild(Player player) {
        throw new UnsupportedOperationException();
    }

    public CLI.FavorResponse getFavor(Player player, int round, ConstructionSite constructionSite) {
        throw new UnsupportedOperationException();
    }

    public BuildingToOwnResponse getBuildingToOwn(Player player) {
        throw new UnsupportedOperationException();
    }

    public CLI.ProvostPositionResponse getProvostPosition(Player player) {
        throw new UnsupportedOperationException();
    }

    public ResourcesResponse getOneResource() {
        throw new UnsupportedOperationException();
    }

    public ResourcesResponse getWoodenBuildingResources(CoveredMarket coveredMarket) {
        throw new UnsupportedOperationException();
    }

    public StoneBuildingToBuildResponse getStoneBuildingToBuild(Player player) {
        throw new UnsupportedOperationException();
    }

    public OptionResponse getTailorResponse(Player player) {
        throw new UnsupportedOperationException();
    }

    public OptionResponse getFoundryOption() {
        throw new UnsupportedOperationException();
    }

    public OptionResponse getAlchemistOption() {
        throw new UnsupportedOperationException();
    }

    public OptionResponse getChurchOption() {
        throw new UnsupportedOperationException();
    }

    public OptionResponse getJewelerOption() {
        throw new UnsupportedOperationException();
    }

    public CharacterResponse chooseCharacter(Player player, List<GameCharacter> characters) {
        throw new UnsupportedOperationException();
    }

    public ConstructionSiteResponse getConstructionSiteResponse(Player player) {
        throw new UnsupportedOperationException();
    }

    public ToMonumentsResponse toMonument(Residence residence) {
        throw new UnsupportedOperationException();
    }

    public void showResults(List<Player> players) {
        throw new UnsupportedOperationException();
    }
}
