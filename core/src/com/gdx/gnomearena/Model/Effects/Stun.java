package com.gdx.gnomearena.Model.Effects;

import com.gdx.gnomearena.Model.Effect;
import com.gdx.gnomearena.Model.Entity;

public class Stun extends Effect {

    public Stun()
    {
        super(3);
    }

    @Override
    public void affect(Entity e)
    {
        e.immobilized=true;
        super.affect(e);
}

    @Override
    public void endEffect(Entity e)
    {
        e.immobilized = false;
        super.endEffect(e);
    }

}