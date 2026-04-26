package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.BerserkState;

import java.util.List;

public class RestFloor extends TowerFloor {

    @Override
    protected String getFloorName() {
        return "Silent Shrine";
    }

    @Override
    protected void announce() {
        System.out.println("\n--- A peaceful shrine appears. This floor uses a custom announce hook. ---");
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("Setup: The shrine prepares healing energy.");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("Challenge: No enemy here. Heroes recover.");

        for (Hero hero : party) {
            if (hero.isAlive()) {
                hero.heal(10);
                System.out.println(hero.getName() + " heals 10 HP. HP: " + hero.getHp());
                hero.setState(new BerserkState(2));
            }
        }

        return new FloorResult(true, 0, "The party rested.");
    }

    @Override
    protected boolean shouldAwardLoot(FloorResult result) {
        return false;
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("This will not print because shouldAwardLoot returns false.");
    }

    @Override
    protected void cleanup(List<Hero> party) {
        System.out.println("Cleanup: The shrine fades away.");
    }
}