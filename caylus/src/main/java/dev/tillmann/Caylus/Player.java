package dev.tillmann.Caylus;

public class Player {
    public void plan(Map map) {
        CLI.PlayerPlan response = CLI.getPlayerPlan();

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
