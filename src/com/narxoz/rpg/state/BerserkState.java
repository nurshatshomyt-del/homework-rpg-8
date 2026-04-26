package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class BerserkState implements HeroState {

    private int turnsLeft;

    public BerserkState(int turnsLeft) {
        this.turnsLeft = turnsLeft;
    }

    @Override
    public String getName() {
        return "Berserk";
    }

    @Override
    public int modifyOutgoingDamage(int basePower) {
        return basePower + 6;
    }

    @Override
    public int modifyIncomingDamage(int rawDamage) {
        return rawDamage + 3;
    }

    @Override
    public void onTurnStart(Hero hero) {
        System.out.println(hero.getName() + " is filled with rage!");
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