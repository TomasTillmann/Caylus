package dev.tillmann.Model;

import java.util.ArrayList;
import java.util.List;

import dev.tillmann.Caylus.*;
import dev.tillmann.Model.Buildings.Building;

public class Player {
    private GameState gameState;
    private Board board;

    public Info info;
    
    private List<Building> ownedBuildings = new ArrayList<>();
    public List<Building> ownedBuildings() { return ownedBuildings; }

    private List<Residence> ownedResidences = new ArrayList<>();
    public List<Residence> ownedResidences() { return ownedResidences; }

    private List<Monument> ownedMonuments = new ArrayList<>();
    public List<Monument> ownedMonuments() { return ownedMonuments; }

    private List<GameCharacter> characters = new ArrayList<>();
    public List<GameCharacter> characters() { return characters; }

    public void plan(Board board, GameState gameState) {
        this.gameState = gameState;

        CLI.PlayerPlanResponse response = CLI.getPlayerPlan(this);

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
        info.resources = info.resources.sub(resources);
    }

    public void gain(Resources resources) {
        info.resources = info.resources.add(resources);
    }

    public void getFavor() {
        { CLI.FavorResponse response = CLI.getFavor(this, gameState.round, board.constructionSite());

        if(response.stealCharacter) {
            characters().add(response.character);
            response.playerStolenFrom.characters().remove(response.character);
            return;
        }

        response.building.benefit(this); }

        if(board.remainingCharacters().size() != 0) {
            CLI.CharacterResponse response = CLI.chooseCharacter(this, board.remainingCharacters());
            characters().add(board.drawCharacter(response.character));
        }
    }

    public void awardPrestigePoints(int points) {
        info.prestigePoints += points;
    }
}
