package com.gdx.gnomearena.Model;

import java.util.HashMap;
import java.util.Map;

public abstract class Weapon
{
    public Map<Direction, Pair<Integer, Integer>[]> attackRanges;

    public boolean canMultishot = false;

    public Weapon()
    {
        attackRanges = new HashMap<>();
    }

    public int damagePoints;
    public boolean attack(Board board, Direction direction)
    {
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
                    if(!canMultishot)
                    {
                        break;
                    }
                }
            }
        }

        if (attackCount == 0 )
            return false;

        GameLogs.addWeaponDamage(this);
        return true;
    }
}
