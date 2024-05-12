package com.gdx.gnomearena.Core;


public abstract class GnomeAttackPattern
{
    public Pair<Integer,Integer>[] attackRange;
    public int damagePoints;

    public abstract boolean attack(Board board, Pair<Integer,Integer> attackerPosition);
}