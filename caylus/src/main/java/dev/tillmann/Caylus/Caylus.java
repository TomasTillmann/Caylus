package dev.tillmann.Caylus;

import java.util.List;

import dev.tillmann.Caylus.Buildings.Building;

public class Caylus {
    private Map map;
    private List<Player> players;
    private Config config;

    public Caylus(Config config) {
        this.config = config;
        players = CLI.getPlayers();
        map = new Map(config);
    }

    public void start() {
        for(int round = 1; round <= config.rounds; ++round) {
            planning();
            activation();
            delivery();
            stewardship();
        }
    }

    //todo: interface for state manipulation

    private void planning() {
        while(map.guildsBridge().passedPlayers().size() != players.size()) {
            for(Player player : planningPlayers()) {
                player.plan(map);
            }
        }
    }

    private void activation() {
        for(int i = 0; i < map.road().provost(); ++i) {
            Building building = map.road().building(i);
            building.activate();
        }
    }

    private void delivery() {
        map.constructionSite().deliver();
    }

    private void stewardship() {
        for(Player player : players) {
            player.stewardship();
        }
    }

    private List<Player> planningPlayers() {
        throw new UnsupportedOperationException();
    }
}
