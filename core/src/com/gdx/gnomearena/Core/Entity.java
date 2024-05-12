package com.gdx.gnomearena.Core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public abstract class Entity
{
    public int health;
    public List<Effect> currentEffects;

    public Texture skin;

    public abstract void takeDamage(int damagepoints);

    public abstract void onDeath();

}
