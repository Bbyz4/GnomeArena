package com.gdx.gnomearena.Core;


public abstract class GnomeMovePattern
{
    public Pair<Integer,Integer>[] moveRange;

    public abstract Pair<Integer,Integer> move(Board board, Pair<Integer,Integer> myPosition);
}
