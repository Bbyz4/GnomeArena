package com.gdx.gnomearena.Core.Items;

import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Core.Item;
import com.gdx.gnomearena.Core.Player;

public class Medkit extends Item
{
    public Medkit()
    {
        skin = new Texture("itemSprites/Medkit.png");
        maxLifetime = 5;
    }

    @Override
    public boolean affect(Player user)
    {
        user.health = Math.min(user.health+1, user.maxHealth);
        return true;
    }
}
