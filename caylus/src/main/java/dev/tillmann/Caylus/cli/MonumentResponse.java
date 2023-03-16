package dev.tillmann.caylus.cli;

import java.util.Optional;

import dev.tillmann.model.Monument;
import dev.tillmann.model.MonumentsPile;
import dev.tillmann.model.Residence;

public class MonumentResponse extends Response {
    public Monument monument;

    public static MonumentResponse parse(Residence residence) { 
        MonumentResponse response = new MonumentResponse();
        response.monument = null;

        visualizer.println(residence.name());
        visualizer.showWhoseTurnIs(residence.owner());

        boolean yes = getSanitizedInput(String.format("Would %s like to turn this residence to monument? (yes/no)", residence.owner()), "Write yes or no",
        input -> {
            if(input.equals("yes")) {
                return true;
            }
            if(input.equals("no")) {
                return false;
            }

            return null;
        });

        if(yes) {
            response.monument = getSanitizedInput("Select to which monument.", "Write monuments name.",
            input -> {
                Optional<Monument> mon = MonumentsPile.Instance.remainingMonuments().stream().filter(m -> m.name().equals(input)).findFirst();
                if(mon.isPresent()) {
                    if(mon.get().owner().canSpend(mon.get().cost())) {
                        return mon.get();
                    }
                }

                return null;
            });
        }

        visualizer.showDelimiter();
        return response;
    }
}