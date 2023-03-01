package dev.tillmann.model.buildings;

import dev.tillmann.model.Player;
import dev.tillmann.model.Resources;

public abstract class BuildableBuilding extends Building {
    public enum Side {
        Construction,
        Setup;
    }
    public Side side = Side.Construction;

    public abstract void immidiateReward(Player player);
    public abstract Resources toBuildCost();
    public abstract void constructionActivate(Player player);
    public abstract void setupActivate(Player player);

    @Override
    public void activatePlayer(Player player) {
        switch(side) {
            case Construction: {
                constructionActivate(player);
                break;
            }

            case Setup: {
                setupActivate(player);
                break;
            }
        }
    }
}