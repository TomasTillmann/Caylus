package dev.tillmann.Model;

import dev.tillmann.Caylus.*;

public class Player {
    public Info info;

    public void plan(Board map) {
        CLI.PlayerPlanResponse response = CLI.getPlayerPlan(this);

        if(response.passed) {
            map.guildsBridge().passed(this);
            return;
        }

        if(response.constructionSite) {
            map.constructionSite().plan(this);
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
        CLI.FavorResponse response = CLI.getFavor(this);
        //todo: ...
    }

    public void awardPrestigePoints(int points) {
        info.prestigePoints += points;
    }

}
