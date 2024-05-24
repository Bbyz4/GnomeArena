package com.gdx.gnomearena.Model;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;

public abstract class Weapon
{
    public Pair<Integer,Integer>[] attackRangeUp;
    public Pair<Integer,Integer>[] attackRangeDown;
    public Pair<Integer,Integer>[] attackRangeLeft;
    public Pair<Integer,Integer>[] attackRangeRight;

    public Map<Character, Pair<Integer, Integer>[]> attackRanges;

    public Texture skin;

    public boolean canMultishot = false;

    public Weapon()
    {
        attackRanges = new HashMap<>();
    }

    public void updateRanges()
    {
        attackRanges.put('W', attackRangeUp);
        attackRanges.put('A', attackRangeLeft);
        attackRanges.put('S', attackRangeDown);
        attackRanges.put('D', attackRangeRight);
    }

    public int damagePoints;
    public boolean attack(Board board, char direction)
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


        return true;
    }
}
