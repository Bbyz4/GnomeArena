package com.gdx.gnomearena.Core.Items;

import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Core.Entity;
import com.gdx.gnomearena.Core.Item;

public class Medkit extends Item
{
    public Medkit()
    {
        skin = new Texture("itemSprites/Medkit.png");
    }

    @Override
    public boolean affect(Entity user)
    {
        user.health = Math.min(user.health+1, user.maxHealth);
        return true;
    }
}
