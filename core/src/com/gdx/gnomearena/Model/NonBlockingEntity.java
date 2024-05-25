package com.gdx.gnomearena.Model;

//An Entity, that does not block the tile it is currently standing on. Instead, it can be replaced when other Entity tries to get to that tile
public abstract class NonBlockingEntity extends TurnEntity
{
    public abstract void onReplace(Board board, Entity replacer);
}
