package com.gdx.gnomearena.Core;


public abstract class Gnome extends Entity
{
    public GnomeMovePattern move;
    public GnomeAttackPattern attack;

    public int moveCooldown;
    public int currentCooldown;
    public int xp;

    public abstract void makeMove(Board board);
    public abstract void onSpawn(Board board);
}
