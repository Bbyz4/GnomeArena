package com.gdx.gnomearena.Core;

import javafx.util.Pair;

public abstract class GnomeMovePattern
{
    Pair<Integer,Integer>[] moveRange;

    public abstract void move(Board b, Pair<Integer,Integer> movedPosition);
}
