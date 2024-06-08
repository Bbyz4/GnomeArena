package com.gdx.gnomearena.Model;

import java.util.ArrayList;
import java.util.function.Supplier;

public abstract class Entity
{
    public Direction currentDirection;
    public int health;
    public int maxHealth;
    public ArrayList<Effect> currentEffects;
    public ArrayList<Pair<Supplier<Item>,Float>> spawnedItems;
    public boolean immobilized;


    public abstract void takeDamage(Board board, int damagepoints);
    public abstract void onDeath(Board board);

}
