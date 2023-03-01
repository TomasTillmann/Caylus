package dev.tillmann.model.characters;

import dev.tillmann.model.Visualizable;

public abstract class GameCharacter implements Visualizable {
    @Override
    public String visualize() {
        return this.getClass().toString();
    }
}