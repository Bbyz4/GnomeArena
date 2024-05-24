package com.gdx.gnomearena.Model.GnomeAttackPatterns;

import com.gdx.gnomearena.Model.Board;
import com.gdx.gnomearena.Model.GnomeAttackPattern;
import com.gdx.gnomearena.Model.Pair;

public class BasicAttack extends GnomeAttackPattern {


    @SuppressWarnings("unchecked")
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
            if (attackerPosition.getKey() + ranges.getKey()  == playerPos.getKey() && attackerPosition.getValue() + ranges.getValue() == playerPos.getValue()) {
                board.get(playerPos.getKey(), playerPos.getValue()).takeDamage(board, damagePoints);
                return true;
            }

        }

        return false;
    }


}
