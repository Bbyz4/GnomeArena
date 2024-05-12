package com.gdx.gnomearena.Core.Weapons;

import com.gdx.gnomearena.Core.*;
import org.graalvm.nativebridge.In;

public class BasicWeapon extends Weapon {


    public BasicWeapon() {
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

    }

    @Override
    public boolean attack(Board board, char direction) {

        Pair<Integer, Integer> playerPos = board.getPlayersPosition();

        int attackCount = 0;
        switch (direction) {
            case 'W':
                for (Pair<Integer, Integer> ranges: attackRangeUp) {
                    int checkX = playerPos.getKey() + ranges.getKey();
                    int checkY = playerPos.getValue() + ranges.getValue();
                    Entity enemy = board.get(checkX,checkY);
                    if (enemy instanceof Gnome && !board.isEmpty(checkX, checkY) && board.isValid(checkX,checkY)) {
                        enemy.takeDamage(board, damagePoints);
                        attackCount++;
                    }
                }
                break;
            case 'A':
                for (Pair<Integer, Integer> ranges: attackRangeLeft) {
                    int checkX = playerPos.getKey() + ranges.getKey();
                    int checkY = playerPos.getValue() + ranges.getValue();
                    Entity enemy = board.get(checkX,checkY);
                    if (enemy instanceof Gnome && !board.isEmpty(checkX, checkY) && board.isValid(checkX,checkY)) {
                        enemy.takeDamage(board, damagePoints);
                        attackCount++;

                    }
                }
                break;
            case 'S':
                for (Pair<Integer, Integer> ranges: attackRangeDown) {
                    int checkX = playerPos.getKey() + ranges.getKey();
                    int checkY = playerPos.getValue() + ranges.getValue();
                    Entity enemy = board.get(checkX,checkY);
                    if (enemy instanceof Gnome && !board.isEmpty(checkX, checkY) && board.isValid(checkX,checkY)) {
                        enemy.takeDamage(board, damagePoints);
                        attackCount++;

                    }
                }
                break;
            case 'D':
                for (Pair<Integer, Integer> ranges: attackRangeRight) {
                    int checkX = playerPos.getKey() + ranges.getKey();
                    int checkY = playerPos.getValue() + ranges.getValue();
                    Entity enemy = board.get(checkX,checkY);
                    if (enemy instanceof Gnome && !board.isEmpty(checkX, checkY) && board.isValid(checkX,checkY)) {
                        enemy.takeDamage(board, damagePoints);
                        attackCount++;
                    }
                }
                break;
            default:
                break;
        }
        if (attackCount == 0 )
            return false;


        //TESTING
        System.out.println(attackCount);

        return true;
    }

}
