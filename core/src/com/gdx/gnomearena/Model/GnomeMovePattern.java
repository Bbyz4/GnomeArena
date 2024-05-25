package com.gdx.gnomearena.Model;


public abstract class GnomeMovePattern
{
    public Pair<Integer,Integer>[] moveRange;

    public abstract Pair<Integer,Integer> move(Board board, Gnome mover);
}
