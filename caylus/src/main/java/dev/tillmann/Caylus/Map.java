package dev.tillmann.Caylus;

import dev.tillmann.Model.Camp;
import dev.tillmann.Model.Buildings.ConstructionSite;
import dev.tillmann.Model.Buildings.GuildsBridge;

public class Map {

    public Map(Config config) {
        constructionSite = new ConstructionSite();
        guildsBridge = new GuildsBridge();
        camp = new Camp();

        road = new Road(config, constructionSite, guildsBridge);
    }

    private ConstructionSite constructionSite;
    public ConstructionSite constructionSite() { return constructionSite; }

    private GuildsBridge guildsBridge;
    public GuildsBridge guildsBridge() { return guildsBridge; }

    private Camp camp;
    public Camp camp() { return camp; }

    private Road road;
    public Road road() { return road; }
}
