package com.gdx.gnomearena.Model;

public abstract class Effect
{
    public int duration;
    public abstract void affect(Entity e);
    public abstract void endEffect(Entity e);


}
