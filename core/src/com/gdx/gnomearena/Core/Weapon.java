package com.gdx.gnomearena.Core;


public abstract class Weapon
{
    public Pair<Integer,Integer>[] attackRangeUp;
    public Pair<Integer,Integer>[] attackRangeDown;
    public Pair<Integer,Integer>[] attackRangeLeft;
    public Pair<Integer,Integer>[] attackRangeRight;


    public int damagePoints;
    public abstract boolean attack(Board board, char direction);

}
