package dev.tillmann.Caylus;

import dev.tillmann.Model.Resources;

public class Player {
    Resources resources;

    public void plan(Map map) {
        CLI.PlayerPlan response = CLI.getPlayerPlan(this);

        if(response.passed) {
            map.guildsBridge().passed(this);
            return;
        }

        response.building.plan(this);
    }

    public void stewardship() {
        throw new UnsupportedOperationException();
    }
}
