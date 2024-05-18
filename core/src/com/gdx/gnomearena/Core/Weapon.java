package com.gdx.gnomearena.Core;

import java.util.HashMap;
import java.util.Map;

public abstract class Weapon
{
    public Pair<Integer,Integer>[] attackRangeUp;
    public Pair<Integer,Integer>[] attackRangeDown;
    public Pair<Integer,Integer>[] attackRangeLeft;
    public Pair<Integer,Integer>[] attackRangeRight;

    public Map<Character, Pair<Integer, Integer>[]> attackRanges;

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
    public abstract boolean attack(Board board, char direction);

}
