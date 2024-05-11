package com.gdx.gnomearena.Core;

import javafx.util.Pair;

public abstract class GnomeMovePattern
{
    public Pair<Integer,Integer>[] moveRange;

    public abstract Pair<Integer,Integer> move(Board b, Pair<Integer,Integer> myPosition);
}
