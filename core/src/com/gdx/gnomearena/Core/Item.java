package com.gdx.gnomearena.Core;

import com.badlogic.gdx.graphics.Texture;

public abstract class Item
{
    public boolean affect(Entity user)
    {
        return false;
    };

    public Texture skin;
}
