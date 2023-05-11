package dev.tillmann.caylus.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;

import dev.tillmann.model.Board;
import dev.tillmann.model.GameState;
import dev.tillmann.model.Player;

public abstract class Response {
    protected static Visualizer visualizer = Visualizer.instance(); 
    protected static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private static Board board;
    public static void provideBoard(Board board) {
        Response.board = board;
    }
    protected static Board board() { return board; }

    private static List<Player> players;
    public static void providePlayers(List<Player> players) {
        Response.players = players;
    }
    protected static List<Player> players() { return players; }

    private static GameState gameState;
    public static void provideGameState(GameState gameState) {
        Response.gameState = gameState;
    }
    protected static GameState gameState() { return gameState; }

    protected Response() { }

    private static void shutdown() {
        visualizer.showShutdownMessage();
        System.exit(1);
    }

    private static void command(String input) {
        try {
            if(input.length() > 2 && input.charAt(0) == '-' && input.charAt(1) == '-') {
                visualizer.println();
                switch(input) {
                    case "--road": {
                        visualizer.showRoad(board().road());
                        break;
                    }

                    case "--monumentsLeft": {
                        visualizer.showRemainingMonuments();
                        break;
                    }

                    case "--charactersLeft": {
                        visualizer.showOnBoardCharacters(board);
                        break;
                    }

                    case "--players": {
                        visualizer.showPlayers(players);
                        break;
                    }

                    case "--state": {
                        visualizer.showState(gameState);
                        break;
                    }

                    case "--constructionSite": {
                        visualizer.showConstructionSite(board().constructionSite());
                        break;
                    }

                    case "--resourcesLeft": {
                        visualizer.showRemainingResources();
                        break;
                    }

                    case "--woodenLeft": {
                        visualizer.showRemainingWoodenBuildings();
                        break;
                    }

                    case "--stoneLeft": {
                        visualizer.showRemainingStoneBuildings();
                        break;
                    }
                }
            }
        }
        catch(Exception e) { visualizer.println("You can't use commands at this stage of the game."); }
    }

    protected static <T> T getSanitizedInput(String message, String errorMessage, Function<String, T> convert) {
        String input;
        T value;

        try {
            visualizer.println(message);
            input = in.readLine();
            command(input);

            while((value = convert.apply(input)) == null) {
                visualizer.println(errorMessage);
                input = in.readLine();
                command(input);
            }
        }
        catch(IOException ex) { shutdown(); return null; }

        return value; 
    }
}