package dev.tillmann.caylus.cli;

import java.io.PrintStream;
import java.util.List;

import dev.tillmann.model.*;
import dev.tillmann.model.buildings.Building;
import dev.tillmann.model.buildings.ConstructionSite;
import dev.tillmann.model.buildings.stone.StoneBuilding;
import dev.tillmann.model.buildings.wooden.WoodenBuilding;
import dev.tillmann.model.characters.GameCharacter;

public class Visualizer {
    private static PrintStream out = System.out;

    private static Visualizer visualizer = new Visualizer();
    public static Visualizer instance() { return visualizer; };

    private Visualizer() { }

    void showDelimiter() {
        out.println("=======================================================================");
        out.println();
    }

    void showBigDelimiter() {
        out.println();
        out.println("=======================================================================");
        out.println("=======================================================================");
        out.println();
    }

    void showPlayers(List<Player> players) {
        out.println("Players:");
        for(Player player : players) {
            out.println(player.visualize());
        }
        out.println();
    }

    void showRemainingResources() {
        out.println("Remaining resources:");
        out.println("Wood: " + Resources.remainingWood());
        out.println("Food: " + Resources.remainingFood());
        out.println("Fabric: " + Resources.remainingFabric());
        out.println("Stone: " + Resources.remainingStone());
        out.println("Gold: " + Resources.remainingGold());
        out.println("Workers: " + Resources.remainingWorkers());
        out.println();
    }

    void showRoad(Road road) {
        out.println("Road:");
        int i = 1;
        for(Building building : road.buildings(b -> true)) {
            if(building == null) {
                out.println("(X)" + "\n" + "Empty");
            }
            else {
                out.print("(" + i + ")" + "\n" + building.visualize());
            }
            i++;
            out.println();

            // todo: print some info what does this building do
        }
        out.println();
    }

    void showConstructionSite(ConstructionSite site) {
        out.println("Construction site:");
        out.println(site.visualize());
        out.println();
    }

    void showOnBoardCharacters(Board board) {
        if(board.onBoardCharacters().size() == 0) {
            out.println("No characters remaining.");
            return;
        }

        out.println("Characters:");
        for(GameCharacter character : board.onBoardCharacters()) {
            out.println(character.name());
        }
        out.println();
    }

    void showRemainingMonuments() {
        out.println("Remaining monuments:");
        for(Monument monument : MonumentsPile.Instance.remainingMonuments()) {
            out.print(monument.name());
        }
        out.println();
    }

    void showRemainingWoodenBuildings() {
        out.println("Remaining wooden buildings:");
        showBuildings(BuildingsPile.Instance.remainingBuildings().stream().filter(b -> b instanceof WoodenBuilding).toList());
    }

    void showRemainingStoneBuildings() {
        out.println("Remaining stone buildings:");
        showBuildings(BuildingsPile.Instance.remainingBuildings().stream().filter(b -> b instanceof StoneBuilding).toList());
    }

    void showBuildings(List<Building> toShow) {
        int i = 1;
        for(Building building : toShow) {
            out.println("(" + i++ + ")");
            out.print(building.visualize());
        }
        out.println();
    }

    void showState(GameState state) {
        out.println("Game State:");
        out.println("Round: " + state.round);
        out.println("Phase: " + state.phase);
        out.println();
    }

    void showTurn(Player player) {
        out.println("It's " + player.name + "'s turn.");
        out.println();
    }

    void showShutdownMessage() {
        out.println("Oh, something went wrong!");
    }

    void println(String message) {
        out.println(message);
    }

    void println() {
        out.println();
    }

    public void showResults(List<Player> players) {
        throw new UnsupportedOperationException();
    }
}
