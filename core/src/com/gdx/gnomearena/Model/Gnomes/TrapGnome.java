package com.gdx.gnomearena.Model.Gnomes;

import java.util.ArrayList;

import com.gdx.gnomearena.Model.Gnome;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.GnomeAttackPatterns.BasicAttack;
import com.gdx.gnomearena.Model.GnomeMovePatterns.TrapMovement;
import com.gdx.gnomearena.Model.Items.EntitySpawningItem;
import com.gdx.gnomearena.Model.TurnEntities.Bomb;

public class TrapGnome extends Gnome
{
    public TrapGnome()
    {
        xp = 1;
        cost = 3;
        health = 2;
        maxHealth = 2;
        move = new TrapMovement();
        attack = new BasicAttack();
        moveCooldown = 2;
        currentCooldown = 2;
        spawnedItems = new ArrayList<>();

        spawnedItems.add(new Pair<>(() -> new EntitySpawningItem(new Bomb(5,2)), 1f));
    }
}
