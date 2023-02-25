package dev.tillmann.Model.Buildings.YellowFlag;

import dev.tillmann.Model.Player;
import dev.tillmann.Model.Resources;

public class Farm extends YellowFlagBuilding {
    @Override
    protected void activatePlayer(Player player) {
        player.info.resources = player.info.resources.add(resourcesGain());
    }

    private Resources resourcesGain() {
        return Resources.empty().addFood(1);
    }
}