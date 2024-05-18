package com.gdx.gnomearena.Core.Weapons;

import com.gdx.gnomearena.Core.*;

public class Dagger extends Weapon {


    @SuppressWarnings("unchecked")
    public Dagger() {
        super();
        damagePoints = 1;
        attackRangeDown = new Pair[]
                {
                        new Pair<>(0,1)
                };
        attackRangeUp = new Pair[]
                {
                        new Pair<>(0,-1),
                };
        attackRangeLeft = new Pair[]
                {
                        new Pair<>(-1,0),
                };
        attackRangeRight = new Pair[]
                {
                        new Pair<>(1, 0),
                };
        updateRanges();

    }

    @Override
    public boolean attack(Board board, char direction) {

        Pair<Integer, Integer> playerPos = board.getPlayersPosition();

        int attackCount = 0;

        for (Pair<Integer, Integer> ranges: attackRanges.get(direction))
        {
            int checkX = playerPos.getKey() + ranges.getKey();
            int checkY = playerPos.getValue() + ranges.getValue();
            if (board.isValid(checkX,checkY)) {
                Entity enemy = board.get(checkX, checkY);
                if (enemy instanceof Gnome && !board.isEmpty(checkX, checkY) && board.isValid(checkX, checkY)) {
                    enemy.takeDamage(board, damagePoints);
                    attackCount++;
                }
            }
        }

        if (attackCount == 0 )
            return false;


        return true;
    }

}
