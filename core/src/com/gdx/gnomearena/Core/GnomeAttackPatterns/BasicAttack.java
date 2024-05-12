package com.gdx.gnomearena.Core.GnomeAttackPatterns;

import com.gdx.gnomearena.Core.Board;
import com.gdx.gnomearena.Core.GnomeAttackPattern;
import com.gdx.gnomearena.Core.Pair;
import org.graalvm.nativebridge.In;

public class BasicAttack extends GnomeAttackPattern {


    public BasicAttack() {
        damagePoints = 1;
        attackRange = new Pair[]
                {
                        new Pair<>(1, 0),
                        new Pair<>(-1,0),
                        new Pair<>(0,1),
                        new Pair<>(0,-1)
                };
    }

    @Override
    public boolean attack(Board board, Pair<Integer, Integer> attackerPosition) {

        Pair<Integer,Integer> playerPos = board.getPlayersPosition();

        for (Pair<Integer,Integer> ranges: attackRange) {

            if (ranges.getKey() == playerPos.getKey() && ranges.getValue() == playerPos.getValue()) {
                board.get(playerPos.getKey(), playerPos.getValue()).takeDamage(damagePoints);
                return true;
            }

        }

        return false;
    }


}
