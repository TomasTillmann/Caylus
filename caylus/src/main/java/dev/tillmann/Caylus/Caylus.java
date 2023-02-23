package dev.tillmann.Caylus;

import java.util.List;

import dev.tillmann.Model.Map;
import dev.tillmann.Model.Player;

public class Caylus {
    private Map map;
    private List<Player> players;
    private Config config;

    public Caylus(Config config) {
        this.config = config;
        players = CLI.getPlayers().value;
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
        // before provost
        for(int i = 0; i < map.road().provost(); ++i) {
            map.road().building(i).activate();
        }

        // after provost
        for(int i = map.road().provost(); i < map.road().size(); ++i) {
            map.road().building(i).spendWorkers();
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
