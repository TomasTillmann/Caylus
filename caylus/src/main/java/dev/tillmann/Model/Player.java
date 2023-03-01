package dev.tillmann.model;

import java.util.ArrayList;
import java.util.List;

import dev.tillmann.caylus.cli.CLI;
import dev.tillmann.model.buildings.Building;

public class Player {
    private GameState gameState;
    public void setGameState(GameState gameState) { this.gameState = gameState; }

    private Board board;
    public void setBoard(Board board) { this.board = board; }

    public int prestigePoints;

    private Resources resources;
    public Resources resources() { return resources; }
    
    private List<Building> ownedBuildings = new ArrayList<>();
    public List<Building> ownedBuildings() { return ownedBuildings; }

    private List<Residence> ownedResidences = new ArrayList<>();
    public List<Residence> ownedResidences() { return ownedResidences; }

    private List<Monument> ownedMonuments = new ArrayList<>();
    public List<Monument> ownedMonuments() { return ownedMonuments; }

    private List<GameCharacter> characters = new ArrayList<>();
    public List<GameCharacter> characters() { return characters; }

    public String name;

    public Player(String name) {
        this.name = name;
    }

    public void plan() {
        CLI.PlayerPlanResponse response = CLI.instance().getPlayerPlan(this);

        if(response.passed) {
            board.guildsBridge().passed(this);
            return;
        }

        if(response.constructionSite) {
            board.constructionSite().plan(this);
            return;
        }

        response.building.plan(this);
    }

    public void stewardship() {
        throw new UnsupportedOperationException();
    }

    public void spend(Resources resources) {
        resources = resources.sub(resources);
    }

    public void gain(Resources resources) {
        resources = resources.add(resources);
    }

    public void getFavor() {
        { CLI.FavorResponse response = CLI.instance().getFavor(this, gameState.round, board.constructionSite());

        if(response.stealCharacter) {
            characters().add(response.character);
            response.playerStolenFrom.characters().remove(response.character);
            return;
        }

        response.building.benefit(this); }

        if(board.remainingCharacters().size() != 0) {
            CLI.CharacterResponse response = CLI.instance().chooseCharacter(this, board.remainingCharacters());
            characters().add(board.drawCharacter(response.character));
        }
    }

    public void awardPrestigePoints(int points) {
        prestigePoints += points;
    }
}
