package dev.tillmann.caylus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.tillmann.caylus.cli.CharacterResponse;
import dev.tillmann.caylus.cli.PlayersResponse;
import dev.tillmann.caylus.cli.Response;
import dev.tillmann.caylus.cli.Visualizer;
import dev.tillmann.model.*;
import dev.tillmann.model.buildings.Building;
import dev.tillmann.model.buildings.monuments.Factory;
import dev.tillmann.model.buildings.monuments.Garden;
import dev.tillmann.model.buildings.monuments.Granary;
import dev.tillmann.model.characters.GameCharacter;

public class Caylus {
    private Board board;
    private GameState gameState;
    private List<Player> players;
    private Config config;

    public Caylus(Config config) {
        this.config = config;

        gameState = new GameState();
        Response.provideGameState(gameState);

        players = PlayersResponse.parse().value;

        Camp camp = new Camp(players.size());
        
        List<GameCharacter> characters = CharactersPile.Instance.getRandomCharacters(ch -> true, players.size() + 3);

        List<GameCharacter> remainingCharacters = initPlayers(characters);

        board = new Board(config, players, remainingCharacters, camp);


        for(Player player : players) {
            player.setBoard(board);
        }

        Response.providePlayers(players);
    }

    public void start() {
        for(gameState.round = 1; gameState.round <= config.rounds; ++gameState.round) {
            gameState.phase = RoundPhase.Planning;
            planning();

            gameState.phase = RoundPhase.Activation;
            activation();

            gameState.phase = RoundPhase.Delivery;
            delivery();

            gameState.phase = RoundPhase.Stewardship;
            stewardship();
        }

        end();
    }

    private List<GameCharacter> initPlayers(List<GameCharacter> characters) {
        Collections.shuffle(players);
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
            player.gain(startingResources);
        }
         
        // choose characters
        for (int i = players.size() - 1; i >= 0; --i) {
            Player player = players.get(i);
            CharacterResponse response = CharacterResponse.chooseCharacter(player, characters);

            player.characters().add(response.character);
            characters.remove(response.character);
        }

        // remaining characters
        return characters;
    }

    private void planning() {
        List<Player> stillPlanningPlayers;
        while(board.guildsBridge().stillPlanningPlayers().size() != 0) {
            stillPlanningPlayers = new ArrayList<>(board.guildsBridge().stillPlanningPlayers());

            for(Player player : stillPlanningPlayers) {
                player.plan();
            }
        }
    }

    private void activation() {
        int workers = 0;

        // before provost
        for(Building building : board.road().buildings(Road.START, board.road().provost())) {
            building.activate();
            workers += building.spendWorkers();
        }

        // after provost
        for(Building building : board.road().buildings(board.road().provost(), Road.ROAD_SIZE)) {
            workers += building.spendWorkers();
        }

        // return spent workers back to camp
        board.camp().returnWorkers(workers);
    }

    private void delivery() {
        board.constructionSite().deliver();
    }

    private void stewardship() {
        board.road().yellowFlagToResidences();
        board.road().residencesToMonuments();
        recruitment();
        board.road().updateProvostAfterRound();
    }

    private void recruitment() {
        for(Player player : players) {
            player.gain(Resources.empty().addWorkers(board.camp().recruitmentWorkersCount));
            player.gain(Resources.empty().addWorkers(player.ownedResidences().size()));

            if(player.ownedMonuments().stream().anyMatch(b -> b instanceof Garden)) {
                player.gain(Resources.empty().addWorkers(2));
            }

            if(player.ownedMonuments().stream().anyMatch(b -> b instanceof Granary)) {
                player.gain(Resources.empty().addFood(1));
            }

            if(player.ownedMonuments().stream().anyMatch(b -> b instanceof Factory)) {
                player.gain(Resources.empty().addFabric(1));
            }
        }
    }

    private void end() {
        for(Player player : players) {
            player.awardPrestigePoints(player.resources().gold() * 2);
        }

        Visualizer.instance().showResults(players);
    }
}
