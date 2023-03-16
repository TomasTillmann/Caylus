package dev.tillmann.model;

import java.util.Collections;
import java.util.List;

import dev.tillmann.caylus.Config;
import dev.tillmann.caylus.cli.Response;
import dev.tillmann.model.buildings.ConstructionSite;
import dev.tillmann.model.buildings.starting.GuildsBridge;
import dev.tillmann.model.characters.GameCharacter;

public class Board {
    private ConstructionSite constructionSite;
    public ConstructionSite constructionSite() { return constructionSite; }

    private GuildsBridge guildsBridge;
    public GuildsBridge guildsBridge() { return guildsBridge; }

    private Camp camp;
    public Camp camp() { return camp; }

    private Road road;
    public Road road() { return road; }

    private List<GameCharacter> onBoardCharacters;
    public List<GameCharacter> onBoardCharacters() { return Collections.unmodifiableList(onBoardCharacters); }

    public GameCharacter drawCharacter(GameCharacter character) {
        if(!onBoardCharacters.contains(character)) {
            throw new IllegalArgumentException();
        }

        onBoardCharacters.remove(character);
        return character;
    }

    public Board(Config config, List<Player> players, List<GameCharacter> onBoardCharacters, Camp camp) {
        this.onBoardCharacters = onBoardCharacters;
        this.camp = camp; 

        BuildingsPile.Instance.setupBuildings(this);
        Response.provideBoard(this);

        constructionSite = new ConstructionSite();
        guildsBridge = new GuildsBridge(this, players);
        road = new Road(guildsBridge);
    }
}
