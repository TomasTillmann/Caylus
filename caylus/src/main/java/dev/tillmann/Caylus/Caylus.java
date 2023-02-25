package dev.tillmann.Caylus;

import java.util.Collections;
import java.util.List;

import dev.tillmann.Model.BuildingsProvider;
import dev.tillmann.Model.GameCharacter;
import dev.tillmann.Model.Board;
import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;
import dev.tillmann.Model.Road;
import dev.tillmann.Model.Buildings.Building;

public class Caylus {
    private Board board;
    private List<Player> players;
    private Config config;

    public Caylus(Config config) {
        this.config = config;

        players = CLI.getPlayers().value;
        List<GameCharacter> characters = CharactersProvider.getRandom(players.size() + 3);
        Collections.shuffle(players);
        initPlayers(characters);

        board = new Board(config, players, characters);

        BuildingsProvider.provideBoard(board);
        Resources.provideCamp(board.camp());
    }

    public void start() {
        for(int round = 1; round <= config.rounds; ++round) {
            planning();
            activation();
            delivery();
            stewardship();
        }
    }

    private void initPlayers(List<GameCharacter> characters) {
        int workersPerPlayer;
        
        // calculate resources
        if(players.size() == 2  || players.size() == 5) {
            workersPerPlayer = 10;
        }
        else if(players.size() == 3 || players.size() == 4) {
            workersPerPlayer = 6;
        }
        else {
            throw new UnsupportedOperationException();
        }

        // add resources
        Resources startingResources = Resources.empty().addWorkers(workersPerPlayer).addWood(2).addFood(1).addStone(1);
        for(Player player : players) {
            player.info.resources.add(startingResources);
        }
         
        // choose characters
        for (int i = players.size() - 1; i >= 0; --i) {
            Player player = players.get(i);
            CLI.CharacterResponse response = CLI.chooseCharacter(player, characters);
            player.info.characters.add(response.character);

            characters.remove(response.character);
        }
    }

    private void planning() {
        while(board.guildsBridge().stillPlanningPlayers().size() != 0) {
            for(Player player : board.guildsBridge().stillPlanningPlayers()) {
                player.plan(board);
            }
        }
    }

    private void activation() {
        int workers = 0;
        List<Building> buildings;

        // before provost
        buildings = board.road().buildings(Road.START, board.road().provost());
        for(Building building : buildings) {
            building.activate();
            workers += building.spendWorkers();
        }

        // after provost
        buildings = board.road().buildings(board.road().provost(), board.road().size());
        for(int i = board.road().provost(); i < board.road().size(); ++i) {
            workers += board.road().building(i).spendWorkers();
        }

        // return workers to camp
        board.camp().addWorkers(workers);
    }

    private void delivery() {
        board.constructionSite().deliver();
    }

    private void stewardship() {
        for(Player player : players) {
            player.stewardship();
        }
    }
}
