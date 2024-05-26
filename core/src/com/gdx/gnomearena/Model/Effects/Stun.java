package com.gdx.gnomearena.Model.Effects;

import com.gdx.gnomearena.Model.Effect;
import com.gdx.gnomearena.Model.Entity;

public class Stun extends Effect {

    public Stun()
    {
        duration = 3;
    }

    @Override
    public void affect(Entity e)
    {
        e.immobilized=true;
        duration--;
        if (duration == 0)
        {
            endEffect(e);
        }
}

    @Override
    public void endEffect(Entity e)
    {
        e.immobilized = false;
        e.currentEffects.remove(this);
    }

}