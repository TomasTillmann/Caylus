package dev.tillmann.model;

import java.util.Collections;
import java.util.List;

import dev.tillmann.caylus.Config;
import dev.tillmann.model.buildings.ConstructionSite;
import dev.tillmann.model.buildings.starting.GuildsBridge;

public class Board {
    private ConstructionSite constructionSite;
    public ConstructionSite constructionSite() { return constructionSite; }

    private GuildsBridge guildsBridge;
    public GuildsBridge guildsBridge() { return guildsBridge; }

    private Camp camp;
    public Camp camp() { return camp; }

    private Road road;
    public Road road() { return road; }

    private List<GameCharacter> remainingCharacters;
    public List<GameCharacter> remainingCharacters() { return Collections.unmodifiableList(remainingCharacters); }

    public GameCharacter drawCharacter(GameCharacter character) {
        if(!remainingCharacters.contains(character)) {
            throw new IllegalArgumentException();
        }

        remainingCharacters.remove(character);
        return character;
    }

    public Board(Config config, List<Player> players, List<GameCharacter> remainingCharacters) {
        this.remainingCharacters = remainingCharacters;
        constructionSite = new ConstructionSite();
        guildsBridge = new GuildsBridge(this, players);
        camp = new Camp(players.size());
        road = new Road(guildsBridge);
    }
}
