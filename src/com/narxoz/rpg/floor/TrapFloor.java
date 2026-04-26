package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.StunnedState;

import java.util.List;

public class TrapFloor extends TowerFloor {

    @Override
    protected String getFloorName() {
        return "Lightning Trap";
    }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("Setup: The floor is covered with magical traps.");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("Challenge: Lightning strikes the party!");

        int totalDamage = 0;

        for (Hero hero : party) {
            if (hero.isAlive()) {
                int damage = hero.calculateIncomingDamage(8);
                hero.takeDamage(damage);
                totalDamage += damage;

                System.out.println(hero.getName() + " takes " + damage + " trap damage. HP: " + hero.getHp());

                hero.setState(new StunnedState(1));
            }
        }

        return new FloorResult(true, totalDamage, "The party survived the trap.");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("Loot: The party finds an old magic stone.");
    }
}