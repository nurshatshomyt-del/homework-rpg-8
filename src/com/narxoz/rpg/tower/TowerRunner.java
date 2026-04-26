package com.narxoz.rpg.tower;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.FloorResult;
import com.narxoz.rpg.floor.TowerFloor;

import java.util.List;

public class TowerRunner {

    private final List<TowerFloor> floors;

    public TowerRunner(List<TowerFloor> floors) {
        this.floors = floors;
    }

    public TowerRunResult run(List<Hero> party) {
        int floorsCleared = 0;

        for (TowerFloor floor : floors) {
            if (countAliveHeroes(party) == 0) {
                break;
            }

            FloorResult result = floor.explore(party);

            System.out.println("Floor result: " + result.getSummary());
            System.out.println("Damage taken: " + result.getDamageTaken());

            if (result.isCleared()) {
                floorsCleared++;
            } else {
                break;
            }
        }

        int survivors = countAliveHeroes(party);
        boolean reachedTop = floorsCleared == floors.size() && survivors > 0;

        return new TowerRunResult(floorsCleared, survivors, reachedTop);
    }

    private int countAliveHeroes(List<Hero> party) {
        int count = 0;

        for (Hero hero : party) {
            if (hero.isAlive()) {
                count++;
            }
        }

        return count;
    }
}