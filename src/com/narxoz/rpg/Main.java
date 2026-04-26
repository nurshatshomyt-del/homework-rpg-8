package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.CombatFloor;
import com.narxoz.rpg.floor.RestFloor;
import com.narxoz.rpg.floor.TowerFloor;
import com.narxoz.rpg.floor.TrapFloor;
import com.narxoz.rpg.state.BerserkState;
import com.narxoz.rpg.state.NormalState;
import com.narxoz.rpg.tower.TowerRunResult;
import com.narxoz.rpg.tower.TowerRunner;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Hero knight = new Hero("Knight", 70, 13, 3, new NormalState());
        Hero barbarian = new Hero("Barbarian", 65, 15, 2, new BerserkState(2));

        List<Hero> party = List.of(knight, barbarian);

        List<TowerFloor> floors = List.of(
                new CombatFloor(),
                new TrapFloor(),
                new RestFloor(),
                new CombatFloor()
        );

        TowerRunner runner = new TowerRunner(floors);
        TowerRunResult result = runner.run(party);

        System.out.println("\n=== Final Tower Result ===");
        System.out.println("Floors cleared: " + result.getFloorsCleared());
        System.out.println("Heroes surviving: " + result.getHeroesSurviving());
        System.out.println("Reached top: " + result.isReachedTop());
    }
}