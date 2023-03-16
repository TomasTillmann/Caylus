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

    protected static <T> T getSanitizedInput(String message, String errorMessage, Function<String, T> convert) {
        String input;
        T value;

        try {
            visualizer.println(message);
            input = in.readLine();

            while((value = convert.apply(input)) == null) {
                visualizer.println(errorMessage);
                input = in.readLine();
            }
        }
        catch(IOException ex) { shutdown(); return null; }

        return value; 
    }
}