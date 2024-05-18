package com.gdx.gnomearena.Core;

import java.util.ArrayList;
import java.util.function.Supplier;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public abstract class Entity
{
    public int health;
    public int maxHealth;
    public List<Effect> currentEffects;
    public ArrayList<Pair<Supplier<Item>,Float>> spawnedItems;

    public Texture skin;

    public abstract void takeDamage(Board board, int damagepoints);

    public abstract void onDeath(Board board);

}
