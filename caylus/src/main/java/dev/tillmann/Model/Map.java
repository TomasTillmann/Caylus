package dev.tillmann.Model;

import dev.tillmann.Caylus.Config;
import dev.tillmann.Model.Buildings.ConstructionSite;
import dev.tillmann.Model.Buildings.Starting.GuildsBridge;

public class Map {
    private ConstructionSite constructionSite;
    public ConstructionSite constructionSite() { return constructionSite; }

    private GuildsBridge guildsBridge;
    public GuildsBridge guildsBridge() { return guildsBridge; }

    private Camp camp;
    public Camp camp() { return camp; }

    private Road road;
    public Road road() { return road; }

    public Map(Config config) {
        constructionSite = new ConstructionSite();
        guildsBridge = new GuildsBridge(this);
        camp = new Camp();

        road = new Road(constructionSite, guildsBridge);

        setup();
    }

    private void setup() {

    }
}
