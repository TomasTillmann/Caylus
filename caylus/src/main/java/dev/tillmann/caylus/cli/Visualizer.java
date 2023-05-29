package dev.tillmann.caylus.cli;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dev.tillmann.model.*;
import dev.tillmann.model.buildings.Building;
import dev.tillmann.model.buildings.ConstructionSite;
import dev.tillmann.model.buildings.stone.StoneBuilding;
import dev.tillmann.model.buildings.wooden.WoodenBuilding;
import dev.tillmann.model.characters.GameCharacter;

/**
 * Is responsible for showing the internal state of the Caylus game.
 * Visualizes the game state to CL.
 */
public class Visualizer {
    private static PrintStream out = System.out;

    private static Visualizer visualizer = new Visualizer();
    public static Visualizer instance() { return visualizer; };

    private Visualizer() { }

    void showDelimiter() {
        out.println("#######################################################################");
    }

    void showPlayers(List<Player> players) {
        showDelimiter();
        out.println("Players:");
        for(Player player : players) {
            out.println(player.visualize());
        }
        showDelimiter();
    }

    void showRemainingResources() {
        showDelimiter();
        out.println("Remaining resources:");
        out.println("Wood: " + Resources.remainingWood());
        out.println("Food: " + Resources.remainingFood());
        out.println("Fabric: " + Resources.remainingFabric());
        out.println("Stone: " + Resources.remainingStone());
        out.println("Gold: " + Resources.remainingGold());
        out.println("Workers: " + Resources.remainingWorkers());
        out.println();
        showDelimiter();
    }

    void showRoad(Road road) {
        showDelimiter();
        out.println("Road:");
        int i = 1;
        int j = 0;
        for(Building building : road.buildings(b -> true)) {
            if(j == road.provost()) {

                out.println("(X)" + "\n" + "PROVOST");
            }
            else if(building == null) {
                out.println("(X)" + "\n" + "Empty");
            }
            else {
                out.print("(" + i + ")" + "\n" + building.visualize());
            }
            i++;
            j++;
            out.println();
        }
        showDelimiter();
    }

    void showConstructionSite(ConstructionSite site) {
        showDelimiter();
        out.println("Construction site:");
        out.println(site.visualize());
        showDelimiter();
    }

    void showOnBoardCharacters(Board board) {
        showDelimiter();
        if(board.onBoardCharacters().size() == 0) {
            out.println("No characters remaining.");
            return;
        }

        out.println("Characters:");
        for(GameCharacter character : board.onBoardCharacters()) {
            out.println(character.name());
        }
        showDelimiter();
    }

    void showRemainingMonuments() {
        showDelimiter();
        out.println("Remaining monuments:");
        int i = 1;
        for(Monument monument : MonumentsPile.Instance.remainingMonuments()) {
            out.println(String.format("(%s) %s", i++, monument.name()));
        }
        showDelimiter();
    }

    void showRemainingWoodenBuildings() {
        showDelimiter();
        out.println("Remaining wooden buildings:");
        showBuildings(BuildingsPile.Instance.remainingBuildings().stream().filter(b -> b instanceof WoodenBuilding).collect(Collectors.toList()));
        showDelimiter();
    }

    void showRemainingStoneBuildings() {
        showDelimiter();
        out.println("Remaining stone buildings:");
        showBuildings(BuildingsPile.Instance.remainingBuildings().stream().filter(b -> b instanceof StoneBuilding).collect(Collectors.toList()));
        showDelimiter();
    }

    private void showBuildings(List<Building> toShow) {
        int i = 1;
        for(Building building : toShow) {
            out.println("(" + i++ + ")");
            out.println(building.visualize());
        }
        out.println();
    }

    void showState(GameState state) {
        showDelimiter();
        out.println("Game State:");
        out.println("Round: " + state.round);
        out.println("Phase: " + state.phase);
        showDelimiter();
    }

    void showShutdownMessage() {
        out.println("Oh, something went wrong!");
    }

    public void println(String message) {
        out.println(message);
    }

    public void println() {
        out.println();
    }

    public void showResults(List<Player> players) {
        Collections.sort(players, (p1, p2) -> Integer.compare(p1.prestigePoints(), p2.prestigePoints()));

        out.println("Results: ");
        int rank = 1;
        for(Player player : players) {
            out.println(rank + ") " + player.name + ", with " + player.prestigePoints() + " prestige points.");
        }

        out.println();
        out.println("ggs");
    }

    void showWhoseTurnIs(Player player) {
        out.println(String.format("It's %s's turn!", player.name));
        out.println();
    }

    void showCharacters(List<GameCharacter> characters) {
        showDelimiter();
        for(int i = 0; i < characters.size(); ++i) {
            out.println(String.format("(%s) %s", i+1, characters.get(i).name()));
        }
        showDelimiter();
    }
}
