package dev.tillmann.Model;

import java.util.List;

import dev.tillmann.Caylus.Config;
import dev.tillmann.Model.Buildings.ConstructionSite;
import dev.tillmann.Model.Buildings.Starting.GuildsBridge;

public class Board {
    private ConstructionSite constructionSite;
    public ConstructionSite constructionSite() { return constructionSite; }

    private GuildsBridge guildsBridge;
    public GuildsBridge guildsBridge() { return guildsBridge; }

    private Camp camp;
    public Camp camp() { return camp; }

    private Road road;
    public Road road() { return road; }

    private List<GameCharacter> characters;
    public List<GameCharacter> characters() { return characters; }

    public Board(Config config, List<Player> players, List<GameCharacter> characters) {
        this.characters = characters;
        constructionSite = new ConstructionSite();
        guildsBridge = new GuildsBridge(this, players);
        camp = new Camp();
        road = new Road(guildsBridge);
    }
}