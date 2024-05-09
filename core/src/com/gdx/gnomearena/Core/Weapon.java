package com.gdx.gnomearena.Core;

import javafx.util.Pair;

public abstract class Weapon
{
    Pair<Integer,Integer>[] attackRange;

    abstract void damage(Entity e);

    abstract void kill(Entity e);
}
