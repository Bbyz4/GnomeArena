package com.gdx.gnomearena.Core;

public abstract class Weapon
{
    int[][] attackRange;

    abstract void damage(Entity e);

    abstract void kill(Entity e);
}
