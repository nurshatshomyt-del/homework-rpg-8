package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;
import com.narxoz.rpg.state.PoisonedState;

import java.util.List;

public class CombatFloor extends TowerFloor {

    private Monster monster;

    @Override
    protected String getFloorName() {
        return "Skeleton Crypt";
    }

    @Override
    protected void setup(List<Hero> party) {
        monster = new Monster("Skeleton Warrior", 35, 9);
        System.out.println("Setup: A monster appears - " + monster.getName());
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        System.out.println("Challenge: Combat starts!");
        int totalDamageTaken = 0;
        int round = 1;

        while (monster.isAlive() && hasAliveHero(party)) {
            System.out.println("Round " + round);

            for (Hero hero : party) {
                if (!hero.isAlive() || !monster.isAlive()) {
                    continue;
                }

                hero.onTurnStart();

                if (hero.canAct()) {
                    int damage = hero.calculateAttackDamage();
                    monster.takeDamage(damage);
                    System.out.println(hero.getName() + " attacks for " + damage + " damage. Monster HP: " + monster.getHp());
                } else {
                    System.out.println(hero.getName() + " skips the turn.");
                }

                hero.onTurnEnd();
            }

            if (monster.isAlive()) {
                Hero target = firstAliveHero(party);
                int rawDamage = monster.getAttackPower();
                int damage = target.calculateIncomingDamage(rawDamage);
                target.takeDamage(damage);
                totalDamageTaken += damage;

                System.out.println(monster.getName() + " attacks " + target.getName() + " for " + damage + " damage. HP: " + target.getHp());

                if (round == 1) {
                    target.setState(new PoisonedState(2));
                }
            }

            round++;
        }

        boolean cleared = !monster.isAlive();

        return new FloorResult(
                cleared,
                totalDamageTaken,
                cleared ? "Monster defeated." : "Party was defeated."
        );
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("Loot: Heroes find healing potions.");
        for (Hero hero : party) {
            if (hero.isAlive()) {
                hero.heal(5);
                System.out.println(hero.getName() + " heals 5 HP. HP: " + hero.getHp());
            }
        }
    }

    private boolean hasAliveHero(List<Hero> party) {
        for (Hero hero : party) {
            if (hero.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private Hero firstAliveHero(List<Hero> party) {
        for (Hero hero : party) {
            if (hero.isAlive()) {
                return hero;
            }
        }
        return null;
    }
}