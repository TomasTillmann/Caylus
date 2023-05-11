package dev.tillmann.caylus.cli;

import dev.tillmann.model.Player;
import dev.tillmann.model.Road;

public class ProvostPositionResponse extends Response {
    public int provostNewPosition;

    public static ProvostPositionResponse parse(Player player, Road road) {
        visualizer.showWhoseTurnIs(player);

        ProvostPositionResponse response = new ProvostPositionResponse();
        response.provostNewPosition = getSanitizedInput(
            "By how much would you like to move the provost and to what direction? For each step you take (max 3), 1 worker from you will be returned to camp.",
            "For example, if you would like to move the provost by 3 to the left, write -3. Beware, that you can move the provost by 3 places only.",
            input -> {
                try {
                    Integer move = Integer.parseInt(input);
                    if(Math.abs(move) <= 3) {
                        if(player.resources().workers() >= Math.abs(move)) {
                            int newProvostPosition = board().road().provost() + move;
                            if(Road.START <= newProvostPosition && newProvostPosition < Road.ROAD_SIZE) {
                                return newProvostPosition;
                            }
                        }
                    }

                } catch(Exception ex) {}

                return null;
            });

        visualizer.showDelimiter();
        return response;
    }
}