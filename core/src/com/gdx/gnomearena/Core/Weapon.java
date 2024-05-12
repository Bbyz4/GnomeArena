package com.gdx.gnomearena.Core;


public abstract class Weapon
{
    public Pair<Integer,Integer>[] attackRange;

    public int damagePoints;

    public abstract void damage(Entity e);

}
