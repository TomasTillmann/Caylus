package dev.tillmann.model;

import java.util.ArrayList;
import java.util.List;

import dev.tillmann.caylus.cli.CLI;
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
            visualization += character.name(); 
        }
        visualization += "\n";
        if(characters().size() != 0) { visualization += "\n"; }

        visualization += "Owned residences: \n";
        for(Residence residence : ownedResidences()) {
            visualization += residence;
        }
        visualization += "\n";
        if(ownedResidences().size() != 0) { visualization += "\n"; }

        visualization += "Owned monuments: \n";
        for(Monument monument : ownedMonuments()) {
            visualization += monument;
        }
        visualization += "\n";
        if(ownedMonuments().size() != 0) { visualization += "\n"; }

        visualization += "Resources: \n";
        visualization += resources().visualize();

        return visualization;
    }

    public void plan() {
        CLI.PlayerPlanResponse response = CLI.instance().getPlayerPlan(this);

        if(response.passed) {
            board.guildsBridge().passed(this);
            return;
        }

        if(response.deliver) {
            board.constructionSite().plan(this);
            return;
        }

        response.building.plan(this);
    }

    public void stewardship() {
        throw new UnsupportedOperationException();
    }

    public void spend(Resources resources) {
        this.resources = this.resources.sub(resources);
    }

    public void gain(Resources resources) {
        this.resources = this.resources.add(resources);
    }

    public void getFavor() {
        { CLI.FavorResponse response = CLI.instance().getFavor(this);

        if(response.stealCharacter) {
            characters().add(response.character);
            response.playerStolenFrom.characters().remove(response.character);
            return;
        }

        response.building.benefit(this); }

        if(board.onBoardCharacters().size() != 0) {
            CLI.CharacterResponse response = CLI.instance().chooseCharacter(this, board.onBoardCharacters());
            characters().add(board.drawCharacter(response.character));
        }
    }

    public void awardPrestigePoints(int points) {
        prestigePoints += points;
    }
}
