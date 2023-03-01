package dev.tillmann.caylus.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import dev.tillmann.model.BuildingsProvider;
import dev.tillmann.model.CharactersProvider;
import dev.tillmann.model.Monument;
import dev.tillmann.model.Player;
import dev.tillmann.model.Residence;
import dev.tillmann.model.Resources;
import dev.tillmann.model.Road;
import dev.tillmann.model.buildings.Building;
import dev.tillmann.model.buildings.ConstructionSite;
import dev.tillmann.model.buildings.YellowFlagBuilding;
import dev.tillmann.model.buildings.monuments.Mansion;
import dev.tillmann.model.buildings.stone.StoneBuilding;
import dev.tillmann.model.buildings.wooden.CoveredMarket;
import dev.tillmann.model.buildings.wooden.WoodenBuilding;
import dev.tillmann.model.characters.GameCharacter;

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

    public class WoodenBuildingResponse {
        public WoodenBuilding woodenBuilding;
    }

    public class StoneBuildingResponse {
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

    public class MonumentResponse {
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
        } catch(Exception ex) {}

        return null;
    }

    private boolean isPlayerNameValid(String playerName) {
        return !playerName.isBlank() && !playerName.contains("\\s") && playerName.length() <= 30;
    }

    public PlayersResponse getPlayers() {
        PlayersResponse response = new PlayersResponse();
        response.value = new ArrayList<>();
        response.value.add(new Player("Alfons"));
        response.value.add(new Player("Breta"));
        response.value.add(new Player("Radovan"));
        return response;

        // trash kod
/*         greetPlayers();
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

            for(int i = 1; i <= playersCount; ++i) {
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

        return response; */
        //
    }

    public CLI.PlayerPlanResponse getPlayerPlan(Player player) {
        PlayerPlanResponse response = new PlayerPlanResponse();
        response.building = BuildingsProvider.getRandomBuildings(b -> b instanceof YellowFlagBuilding, 1).get(0);
        return response;
    }

    public WoodenBuildingResponse getWoodenBuildingToBuild(Player player) {
        WoodenBuildingResponse response = new WoodenBuildingResponse();
        response.woodenBuilding = (WoodenBuilding)BuildingsProvider.getRandomBuildings(b -> b instanceof WoodenBuilding, 1).get(0);
        return response;
    }

    public CLI.FavorResponse getFavor(Player player, int round, ConstructionSite constructionSite) {
        FavorResponse response = new FavorResponse();
        response.stealCharacter = false;
        return response;
    }

    public BuildingToOwnResponse getBuildingToOwn(Player player) {
        BuildingToOwnResponse response = new BuildingToOwnResponse();
        response.building = (YellowFlagBuilding)BuildingsProvider.getRandomBuildings(b -> b instanceof YellowFlagBuilding, 1).get(0);
        return response;
    }

    public CLI.ProvostPositionResponse getProvostPosition(Player player, Road road) {
        ProvostPositionResponse response = new ProvostPositionResponse();
        response.provostNewPosition = road.provost() - 1;
        return response;
    }

    public ResourcesResponse getOneResource() {
        ResourcesResponse response = new ResourcesResponse();
        response.resources = Resources.empty().addFabric(1);
        return response;
    }

    public ResourcesResponse getWoodenBuildingResources(CoveredMarket coveredMarket) {
        ResourcesResponse response = new ResourcesResponse();
        response.resources = Resources.empty().addWood(2);
        return response;
    }

    public StoneBuildingResponse getStoneBuildingToBuild(Player player) {
        StoneBuildingResponse response = new StoneBuildingResponse();
        response.stoneBuilding = (StoneBuilding)BuildingsProvider.getRandomBuildings(b -> b instanceof StoneBuilding, 1).get(0); 
        return response;
    }

    public OptionResponse getTailorResponse(Player player) {
        OptionResponse response = new OptionResponse();
        response.option = 2;
        return response;
    }

    public OptionResponse getFoundryOption() {
        OptionResponse response = new OptionResponse();
        response.option = 2;
        return response;
    }

    public OptionResponse getAlchemistOption() {
        OptionResponse response = new OptionResponse();
        response.option = 2;
        return response;
    }

    public OptionResponse getChurchOption() {
        OptionResponse response = new OptionResponse();
        response.option = 2;
        return response;
    }

    public OptionResponse getJewelerOption() {
        OptionResponse response = new OptionResponse();
        response.option = 2;
        return response;
    }

    public CharacterResponse chooseCharacter(Player player, List<GameCharacter> characters) {
        CharacterResponse response = new CharacterResponse();
        response.character = CharactersProvider.getRandomCharacters(ch -> true, 1).get(0);
        return response;
    }

    public ConstructionSiteResponse getConstructionSiteResponse(Player player) {
        ConstructionSiteResponse response = new ConstructionSiteResponse();
        response.bundles = new ArrayList<>();
        response.bundles.add(Resources.empty().addFood(1).addFabric(1).addWood(1));
        return response;
    }

    public MonumentResponse toMonument(Residence residence) {
        MonumentResponse response = new MonumentResponse();
        response.monument = new Mansion();
        return response;
    }

    public void showResults(List<Player> players) {
        throw new UnsupportedOperationException();
    }
}
