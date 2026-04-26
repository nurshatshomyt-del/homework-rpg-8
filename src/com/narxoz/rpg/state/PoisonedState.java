package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class PoisonedState implements HeroState {

    private int turnsLeft;

    public PoisonedState(int turnsLeft) {
        this.turnsLeft = turnsLeft;
    }

    @Override
    public String getName() {
        return "Poisoned";
    }

    @Override
    public int modifyOutgoingDamage(int basePower) {
        return Math.max(1, basePower - 3);
    }

    @Override
    public int modifyIncomingDamage(int rawDamage) {
        return rawDamage + 2;
    }

    @Override
    public void onTurnStart(Hero hero) {
        System.out.println(hero.getName() + " suffers poison damage!");
        hero.takeDamage(3);
    }

    @Override
    public void onTurnEnd(Hero hero) {
        turnsLeft--;

        if (turnsLeft <= 0) {
            hero.setState(new NormalState());
        }
    }

    @Override
    public boolean canAct() {
        return true;
    }
}