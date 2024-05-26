package com.gdx.gnomearena.Model;

public abstract class Effect
{
    public int duration;
    public boolean hasEnded;


    public void affect(Entity e)
    {
        if (duration == 0)
        {
            endEffect(e);
        }
        duration--;
    }
    public void endEffect(Entity e)
    {
        hasEnded = true;
    }

    public Effect(int duration)
    {
        hasEnded = false;
        this.duration = duration;
    }
}
