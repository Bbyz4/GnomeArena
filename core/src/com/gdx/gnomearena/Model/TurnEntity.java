package com.gdx.gnomearena.Model;

public abstract class TurnEntity extends Entity
{
    public int moveCooldown;
    public int currentCooldown;

    public abstract void makeMove(Board board);
    public abstract void onSpawn(Board board);
}
