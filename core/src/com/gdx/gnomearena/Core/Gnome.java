package com.gdx.gnomearena.Core;


public abstract class Gnome extends Entity
{
    public GnomeMovePattern move;
    public GnomeAttackPattern attack;

    public int moveCooldown;
    public int currentCooldown;
    public int xp;

    public void makeMove(Board board){}
    public void onSpawn(){}
}
