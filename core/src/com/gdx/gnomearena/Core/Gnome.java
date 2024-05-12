package com.gdx.gnomearena.Core;


public abstract class Gnome extends Entity
{
    public GnomeMovePattern move;
    public GnomeAttackPattern attack;

    public int moveCooldown;
    public int currentCooldown;

    public void makeMove(Board board){}
    public void onSpawn(){}
    public void onDeath(){}
}
