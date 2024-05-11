package com.gdx.gnomearena.Core;


public abstract class GnomeAttackPattern
{
    Pair<Integer,Integer>[] attackRange;

    public abstract void attack(Board b, Pair<Integer,Integer> attackerPosition, Pair<Integer,Integer> attackedPosition);
}
