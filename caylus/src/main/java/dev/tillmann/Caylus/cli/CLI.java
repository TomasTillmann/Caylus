package dev.tillmann.caylus.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import dev.tillmann.model.Board;
import dev.tillmann.model.BuildingsPile;
import dev.tillmann.model.CharactersPile;
import dev.tillmann.model.GameState;
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

    private Board board;
    public void provideBoard(Board board) { this.board = board; }

    private List<Player> players;
    public void providePlayers(List<Player> players) { this.players = players; }

    private GameState gameState;
    public void provideGameState(GameState gameState) { this.gameState = gameState; }
    
    private CLI() { }

    public class PlayersResponse {
        public List<Player> value;
    }

    public class PlayerPlanResponse {
        public boolean passed;
        public Building building;
        public boolean deliver;
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
        public Building building;
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

    private void showDelimiter() {
        out.println("=======================================================================");
    }

    private void showPlayers() {
        out.println("Players:");
        for(Player player : players) {
            out.println(player.visualize());
        }
        out.println();
    }

    private void showRemainingResources() {
        out.println("Remaining resources:");
        out.println("Wood: " + Resources.remainingWood());
        out.println("Food: " + Resources.remainingFood());
        out.println("Fabric: " + Resources.remainingFabric());
        out.println("Stone: " + Resources.remainingStone());
        out.println("Gold: " + Resources.remainingGold());
        out.println("Workers: " + Resources.remainingWorkers());
        out.println();
    }

    private void showRoad() {
        out.println("Road:");
        int i = 1;
        for(Building building : board.road().buildings(b -> true)) {
            if(building == null) {
                out.println("(X)" + "\n" + "Empty");
            }
            else {
                out.println("(" + i++ + ")" + "\n" + building.visualize());
            }
            out.println();

            // todo: print some info what does this building do
        }
        out.println();
    }

    private void showConstructionSite() {
        out.println("Construction site:");
        out.println(board.constructionSite().visualize());
        out.println();
    }

    private void showOnBoardCharacters() {
        if(board.onBoardCharacters().size() == 0) {
            out.println("No characters remaining.");
            return;
        }

        out.println("Characters:");
        for(GameCharacter character : board.onBoardCharacters()) {
            out.println(character.name());
        }
        out.println();
    }

    private void showRemainingWoodenBuildings() {
        out.println("Remaining wooden buildings:");
        showBuildings(BuildingsPile.remainingBuildings().stream().filter(b -> b instanceof WoodenBuilding).toList());
    }

    private void showRemainingStoneBuildings() {
        out.println("Remaining stone buildings:");
        showBuildings(BuildingsPile.remainingBuildings().stream().filter(b -> b instanceof StoneBuilding).toList());
    }

    private void showBuildings(List<Building> toShow) {
        int i = 1;
        for(Building building : toShow) {
            out.println("(" + i + ")");
            out.print(building.visualize());
        }
        out.println();
    }

    private void showGameState() {
        out.println("Round: " + gameState.round);
        out.println("Phase: " + gameState.phase);
        out.println();
    }

    private void showState() {
        out.println("Game State:");

        showGameState();
        showDelimiter();

        showPlayers();
        showDelimiter();

        showOnBoardCharacters();
        showDelimiter();

        showConstructionSite();
        showDelimiter();

        showRoad();
        showDelimiter();

        showRemainingResources();
        showDelimiter();

        showRemainingWoodenBuildings();
        showDelimiter();

        showRemainingStoneBuildings();
        showDelimiter();

        out.println();
    }

    private void showTurn(Player player) {
        out.println("It's " + player.name + "'s turn.");
        out.println();
    }

    private void shutdown() {
        out.println("Oh, something went wrong!");
        System.exit(1);
    }

    private <T> T getSanitizedInput(String message, String errorMessage, Function<String, T> convert) {
        String input;
        T value;

        try {
            out.println(message);
            input = in.readLine();

            while((value = convert.apply(input)) == null) {
                out.println(errorMessage);
                input = in.readLine();
            }
        }
        catch(IOException ex) { shutdown(); return null; }

        return value; 
    }

    public PlayersResponse getPlayers() {
        PlayersResponse response = new PlayersResponse();
        response.value = new ArrayList<>();

        // welcome
        out.println("Welcome to Caylus1303!");

        // get players count
        int playersCount = getSanitizedInput(
            "Specify number of players: (2-5).",
            "Number of players has to be a number between 2 and 5.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(2 <= n && n <= 5) { return n; }
                }
                catch(Exception e) { }
                return null;
            });

        // get players
        HashSet<String> usedNames = new HashSet<>();
        for(int i = 1; i <= playersCount; ++i) {
            String name = getSanitizedInput(
                "Get name for player number " + i + ".",
                "The name can't be already in use, empty or longer than 30 characters.",
                input -> {
                    if(usedNames.contains(input) || input.isBlank() || input.length() > 30) {
                        return null;
                    }

                    return input;
                });
            
            usedNames.add(name);
            response.value.add(new Player(name));
        }
        out.println();

        return response;
    }

    public CLI.PlayerPlanResponse getPlayerPlan(Player player) {
        final int pass = 1;
        final int plan = 2;
        final int deliver = 3;

        showState();
        showTurn(player);

        PlayerPlanResponse response = new PlayerPlanResponse();

        // get player's decision
        int option = getSanitizedInput(
            "Choose what would you like to do:\npass(1)\nplan(2)\ndeliver(3)",
            "You can choose only one of these options. For example, to pass, write 1.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == pass || n == plan || n == deliver) {
                        return n;
                    }
                } catch(Exception ex) { }
                return null;
            });
        
        if(option == pass) {
            response.passed = true;
            return response;
        }

        if(option == deliver) {
            response.deliver = true;
            return response;
        }

        // resolve if he would like to plan
        showRoad();
        showDelimiter();

        int buildingIndex = getSanitizedInput(
            "Select the building you would like to plan on. You can't select empty building.",
            "To select the building, type the number above it. For example, write 1.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input) - 1;
                    try {
                        Building building = board.road().building(n);
                        if(building != null) {
                            return n;
                        }

                    } catch(IllegalArgumentException ex) {}
                } catch(Exception ex) {}

                return null;
            });
        
        response.building = board.road().building(buildingIndex);

        return response;
    }

    public WoodenBuildingResponse getWoodenBuildingToBuild(Player player) {
        WoodenBuildingResponse response = new WoodenBuildingResponse();
        showState();

        showTurn(player);

        showRemainingWoodenBuildings();
        showDelimiter();

        int option = getSanitizedInput(
            "Select which wooden building would you like to build.",
            "Select by writing the number associated with the building. For example, write 1.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input) - 1;
                    try {
                        BuildingsPile.remainingBuildings().stream().filter(b -> b instanceof WoodenBuilding).toList().get(n);
                        return n;
                    } catch(Exception ex) {}
                } catch(Exception ex) { }

                return null;
            });

            response.woodenBuilding = (WoodenBuilding)BuildingsPile.remainingBuildings().stream().filter(b -> b instanceof WoodenBuilding).toList().get(option);

            // actually take it out of the pile to claim it's ownership
            BuildingsPile.getBuildings(b -> b == response.woodenBuilding);

            return response;
    }

    public CLI.FavorResponse getFavor(Player player) {
        FavorResponse response = new FavorResponse();
        final int steal = 1;
        final int benefit = 2;

        showGameState();
        showTurn(player);
        out.println("You got a favor!");

        int option = getSanitizedInput(
            "Select if you would rather:\n1.Steal character from another player\n2.Benefit from one of the buildings on construction site",
            "To choose steal, write 1. To choose benefit, write 2.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == steal || n == benefit) {
                        return n;
                    }
                } catch(Exception ex) {}

                return null;
            });
        
        if(option == steal) {
            showPlayers();
            showDelimiter();
            out.println("You chose to steal.");

            response.playerStolenFrom = getSanitizedInput(
                "From which player would you like to steal?",
                "Write the player's name to select him.",
                input -> {
                    if(players.stream().anyMatch(p -> p.name == input)) {
                        return players.stream().filter(p -> p.name == input).findFirst().get();
                    }

                    return null;
                });
            
            showOnBoardCharacters();
            showDelimiter();

            response.character = getSanitizedInput(
                "What character would you like to steal?",
                "Write the characters name to steal it.",
                input -> {
                    Optional<GameCharacter> character = board.onBoardCharacters().stream().filter(ch -> ch.name() == input).findFirst();
                    if(character.isPresent()) {
                        return character.get();
                    }

                    return null;
                });
            
            return response;
        }

        if(option == benefit) {
            final int firstBuilding = 1;
            final int secondBuilding = 2;
            final int thirdBuilding = 3;

            showConstructionSite();
            showDelimiter();
            out.println("You chose to benefit from one of the buildings on the construction site.");

            response.building = getSanitizedInput(
                "Which building would you like to benefit from?",
                "Choose from 1 - 3. Beware, that you can benefit from certain buildings based on what is the current round. The current round now is: " + gameState.round + ".",
                input -> {
                    try {
                        Integer n = Integer.parseInt(input);
                        if(1 <= gameState.round && gameState.round <= 3) {
                            if (n == firstBuilding) { return board.constructionSite().first(); }
                        }
                        else if(4 <= gameState.round && gameState.round <= 6) {
                            if (n == firstBuilding) { return board.constructionSite().first(); }
                            if (n == secondBuilding) { return board.constructionSite().second(); }
                        }
                        else if(7 <= gameState.round && gameState.round <= 9) {
                            if (n == firstBuilding) { return board.constructionSite().first(); }
                            if (n == secondBuilding) { return board.constructionSite().second(); }
                            if (n == thirdBuilding) { return board.constructionSite().third(); }
                        }

                    } catch(Exception ex) {}

                    return null;
                });
            
            return response;
        }

        // shoudln't happen ever
        shutdown();
        return null;
    }

    public BuildingToOwnResponse getBuildingToOwn(Player player) {
        showGameState();
        showTurn(player);
        showDelimiter();

        BuildingToOwnResponse response = new BuildingToOwnResponse();

        response.building = getSanitizedInput(
            "Select the building you would like to own. You can't own empty building or building which is already owned.",
            "To select the building, type the number above it. For example, write 1. Beware, you can't own empty building or building which is already owned.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input) - 1;
                    try {
                        Building building = board.road().building(n);
                        if(building != null) {
                            if(!building.hasOwner()) {
                                return building;
                            }
                        }

                    } catch(IllegalArgumentException ex) {}
                } catch(Exception ex) {}

                return null;
            });

        return response;
    }

    public CLI.ProvostPositionResponse getNewProvostPosition(Player player, Road road) {
        showGameState();
        showTurn(player);

        ProvostPositionResponse response = new ProvostPositionResponse();
        response.provostNewPosition = getSanitizedInput(
            "By how much would you like to move the provost and to what direction? For each step you take (max 3), 1 worker from you will be returned to camp.",
            "For example, if you would like to move the provost by 3 to the left, write -3. Beware, that you can move the provost by 3 places only.",
            input -> {
                try {
                    Integer move = Integer.parseInt(input);
                    if(Math.abs(move) <= 3) {
                        if(player.resources().workers() >= Math.abs(move)) {
                            int newProvostPosition = board.road().provost() + move;
                            if(Road.START <= newProvostPosition && newProvostPosition < Road.ROAD_SIZE) {
                                return newProvostPosition;
                            }
                        }
                    }

                } catch(Exception ex) {}

                return null;
            });

        return response;
    }

    // todo: exclude gold and workers - change name than - only allow wood, stone, fabric, food
    public ResourcesResponse getOneResource(Player player) {
        ResourcesResponse response = new ResourcesResponse();
        showGameState();
        showTurn(player);

        response.resources = getSanitizedInput(
            "Which resource would you like?\n1.Food\n2.Wood\n3.Stone\n4.Fabric.",
            "For example, to select Food, write 1.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == 1) {
                        return Resources.empty().addFood(1);
                    }
                    if(n == 2) {
                        return Resources.empty().addWood(1);
                    }
                    if(n == 3) {
                        return Resources.empty().addStone(1);
                    }
                    if(n == 4) {
                        return Resources.empty().addFabric(1);
                    }
                } catch(Exception ex) {}

                return null;

            });

        return response;
    }

    public StoneBuildingResponse getStoneBuildingToBuild(Player player) {
        StoneBuildingResponse response = new StoneBuildingResponse();
        showState();

        showTurn(player);

        showRemainingStoneBuildings();
        showDelimiter();

        int option = getSanitizedInput(
            "Select which wooden building would you like to build.",
            "Select by writing the number associated with the building. For example, write 1.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input) - 1;
                    try {
                        BuildingsPile.remainingBuildings().stream().filter(b -> b instanceof StoneBuilding).toList().get(n);
                        return n;
                    } catch(Exception ex) {}
                } catch(Exception ex) { }

                return null;
            });

            response.stoneBuilding = (StoneBuilding)BuildingsPile.remainingBuildings().stream().filter(b -> b instanceof StoneBuilding).toList().get(option);

            // actually take it out of the pile to claim it's ownership
            BuildingsPile.getBuildings(b -> b == response.stoneBuilding);

            return response;
    }

    public OptionResponse getTailorOption(Player player) {
        showTurn(player);
        showDelimiter();
        out.println("Tailor activation.");

        OptionResponse response = new OptionResponse();
        response.option = getSanitizedInput(
            "Would you like to spend 1 cloth and gain 4PP (1) or spend 2 cloth and gain 6PP (2)?",
            "Write 1 for the first option, or 2 for the second.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == 1) {
                        if(player.resources().fabric() >= 1) {
                            return n;
                        }
                    }
                    if(n == 2) {
                        if(player.resources().fabric() >= 2) {
                            return n;
                        }
                    }
                } catch(Exception ex) {}

                return null;
            });

        return response;
    }

    public OptionResponse getFoundryOption(Player player) {
        showTurn(player);
        showDelimiter();
        out.println("Foundry activation.");

        OptionResponse response = new OptionResponse();
        response.option = getSanitizedInput(
            "Would you like to spend 1 worker and gain 1 gold (1) or spend 3 workers and gain 2 gold (2)?",
            "Write 1 for the first option, or 2 for the second.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == 1) {
                        if(player.resources().workers() >= 1) {
                            return n;
                        }
                    }
                    if(n == 2) {
                        if(player.resources().workers() >= 3) {
                            return n;
                        }
                    }
                } catch(Exception ex) {}

                return null;
            });

        return response;
    }

    public OptionResponse getAlchemistOption(Player player) {
        showTurn(player);
        showDelimiter();
        out.println("Alchemist activation.");

        OptionResponse response = new OptionResponse();
        response.option = getSanitizedInput(
            "Would you like to spend 1 food / stone / wood / fabric and gain 1 gold (1) or spend 2 food / stone / wood / fabric and gain 2 gold (2)?",
            "Write 1 for the first option, or 2 for the second.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    Resources r = player.resources();

                    if(n == 1) {
                        if(r.food() >= 1 || r.stone() >= 1 || r.wood() >= 1 || r.fabric() >= 1 && (n == 1 || n == 2)) {
                            return n;
                        }
                    }
                } catch(Exception ex) {}

                return null;
            });

        return response;
    }

    public OptionResponse getChurchOption(Player player) {
        showTurn(player);
        showDelimiter();
        out.println("Church activation.");

        OptionResponse response = new OptionResponse();
        response.option = getSanitizedInput(
            "Would you like to spend 2 workers and gain 4PP (1) or spend 4 workers and gain 6PP (2)?",
            "Write 1 for the first option, or 2 for the second.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == 1) {
                        if(player.resources().workers() >= 2) {
                            return n;
                        }
                    }
                    if(n == 2) {
                        if(player.resources().workers() >= 4) {
                            return n;
                        }
                    }
                } catch(Exception ex) {}

                return null;
            });

        return response;
    }

    public OptionResponse getJewelerOption(Player player) {
        showTurn(player);
        showDelimiter();
        out.println("Jeweler activation.");

        OptionResponse response = new OptionResponse();
        response.option = getSanitizedInput(
            "Would you like to spend 1 gold and gain 5PP (1) or spend 2 gold and gain 9PP (2)?",
            "Write 1 for the first option, or 2 for the second.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input);
                    if(n == 1) {
                        if(player.resources().gold() >= 1) {
                            return n;
                        }
                    }
                    if(n == 2) {
                        if(player.resources().gold() >= 2) {
                            return n;
                        }
                    }
                } catch(Exception ex) {}

                return null;
            });

        return response;
    }

    public CharacterResponse chooseCharacter(Player player, List<GameCharacter> characters) {
        CharacterResponse response = new CharacterResponse();
        showTurn(player);
        showDelimiter();

        // show characters
        for(int i = 0; i < characters.size(); ++i) {
            out.println(i + "." + characters.get(i).name());
        }
        //

        response.character = getSanitizedInput(
            "Choose one of these characters.",
            "Choose by wrting the number, e.g 1.",
            input -> {
                try {
                    Integer n = Integer.parseInt(input) - 1;
                    if(0 <= n && n < characters.size()) {
                        return characters.get(n);
                    }
                } catch(Exception ex) {}

                return null;
            });

        return response;
    }

    public ConstructionSiteResponse getConstructionSiteResponse(Player player) {
        ConstructionSiteResponse response = new ConstructionSiteResponse();
        response.bundles = new ArrayList<>();

        showGameState();
        showTurn(player);
        showDelimiter();

        while(getSanitizedInput("Would you like to deliver a bundle? (yes / no)", "Write yes or no.", 
        input -> {
            if(input == "yes") {
                return true;
            }

            if(input == "no") {
                return false;
            }

            return null;
        })) {
            Optional<Resources> bundle;
            bundle = getSanitizedInput("Choose a bundle. (food, wood, stone, fabric, gold)", "Write the names of the resources seperated by space. For example, writing: food fabric gold, you delivered just that. Beware you can deliver only what you have in resources. If you have realised, you can't deliver a bundle, type stop.",
            input -> {
                String[] tokens = input.trim().split(" ");
                if(tokens.length == 1){ 
                    if(tokens[0] == "stop") {
                        return Optional.empty();
                    }
                }

                Resources possibleBundle = Resources.empty();
                if(tokens.length == 3) {
                    int checksum = 0;
                    for(int i = 0; i < tokens.length; ++i) {
                        String entry = tokens[i];
                        if(entry == "food") {
                            if(possibleBundle.food() == 1) {
                                return null;
                            }

                            possibleBundle.addFood(1);
                            checksum += 1;
                        }
                        if(entry == "wood") {
                            if(possibleBundle.wood() == 1) {
                                return null;
                            }

                            possibleBundle.addWood(1);
                            checksum += 1;
                        }
                        if(entry == "stone") {
                            if(possibleBundle.stone() == 1) {
                                return null;
                            }

                            possibleBundle.addStone(1);
                            checksum += 1;
                        }
                        if(entry == "fabric") {
                            if(possibleBundle.fabric() == 1) {
                                return null;
                            }

                            possibleBundle.addFabric(1);
                            checksum += 1;
                        }
                        if(entry == "gold") {
                            if(possibleBundle.gold() == 1) {
                                return null;
                            }

                            possibleBundle.addGold(1);
                            checksum += 1;
                        }
                    }

                    if(checksum == 3) {
                        return Optional.of(possibleBundle);
                    }
                }

                return null;
            });

            if(bundle.isPresent()) {
                response.bundles.add(bundle.get());
            }
            else {
                // user realised he can't deliver a bundle and written stop
                break;
            }
        }

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
