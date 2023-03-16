package dev.tillmann.caylus.cli;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import dev.tillmann.model.Player;

public class PlayersResponse extends Response {
    public List<Player> value;

    public static PlayersResponse parse() {
        PlayersResponse response = new PlayersResponse();
        response.value = new ArrayList<>();

        // welcome
        visualizer.println("Welcome to Caylus1303!");

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
        
        visualizer.println();
        return response;
    }
}