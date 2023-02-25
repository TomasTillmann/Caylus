package dev.tillmann.Model.Buildings;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

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