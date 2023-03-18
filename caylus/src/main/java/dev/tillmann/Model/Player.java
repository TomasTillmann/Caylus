package dev.tillmann.model;

import java.util.ArrayList;
import java.util.List;

import dev.tillmann.caylus.cli.CharacterResponse;
import dev.tillmann.caylus.cli.FavorResponse;
import dev.tillmann.caylus.cli.PlayerPlanResponse;
import dev.tillmann.model.buildings.Building;
import dev.tillmann.model.characters.GameCharacter;

public class Player implements Visualizable {
    private Board board;
    public void setBoard(Board board) { this.board = board; }

    public int prestigePoints;

    private Resources resources = Resources.empty();
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

    @Override
    public String visualize() {
        String visualization = "";
        visualization += "Name: " + name + "\n";

        visualization += "Characters: \n";
        for(GameCharacter character : characters()) {
            visualization += character.name() + "\n"; 
        }
        if(characters().size() != 0) { visualization += "\n"; }

        visualization += "Owned residences: \n";
        for(Residence residence : ownedResidences()) {
            visualization += residence.name() + "\n";
        }
        if(ownedResidences().size() != 0) { visualization += "\n"; }

        visualization += "Owned monuments: \n";
        for(Monument monument : ownedMonuments()) {
            visualization += monument.name() + "\n";
        }
        if(ownedMonuments().size() != 0) { visualization += "\n"; }

        visualization += "Resources: \n";
        visualization += resources().visualize();

        return visualization;
    }

    public void plan() {
        PlayerPlanResponse response = PlayerPlanResponse.parse(this);

        if(response.passed) {
            board.guildsBridge().passed(this);
            return;
        }

        if(response.deliver) {
            board.constructionSite().plan(this);
            return;
        }

        Resources workers = Resources.empty().addWorkers(response.building.workersCost());
        spend(workers);
        board.camp().returnWorkers(response.building.workersCost());

        response.building.plan(this);
    }

    public void stewardship() {
        throw new UnsupportedOperationException();
    }

    public boolean canSpend(Resources resources) {
        Resources newResources = resources().sub(resources);
        return newResources.food() >= 0 && newResources.wood() >= 0 && newResources.stone() >= 0 && newResources.workers() >= 0 && newResources.gold() >= 0;
    }

    public void spend(Resources resources) {
        if(!canSpend(resources)) {
            throw new IllegalArgumentException("\nCan't spend this resources:" + resources.visualize());
        }

        this.resources = this.resources.sub(resources);
        Resources.returnBack(this.resources);
    }

    public void gain(Resources resources) {
        this.resources = this.resources.add(resources);
        if(resources.take() != true) { throw new UnsupportedOperationException(); }
    }

    public void getFavor() {
        { FavorResponse response = FavorResponse.parse(this);

        if(response.stealCharacter) {
            characters().add(response.character);
            response.playerStolenFrom.characters().remove(response.character);
            return;
        }

        response.building.benefit(this); }

        if(board.onBoardCharacters().size() != 0) {
            CharacterResponse response = CharacterResponse.chooseCharacter(this, board.onBoardCharacters());
            characters().add(board.drawCharacter(response.character));
        }
    }

    public void awardPrestigePoints(int points) {
        prestigePoints += points;
    }
}
