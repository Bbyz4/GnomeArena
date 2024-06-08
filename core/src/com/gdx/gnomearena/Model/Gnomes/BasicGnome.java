package com.gdx.gnomearena.Model.Gnomes;

import java.util.ArrayList;

import com.gdx.gnomearena.Model.Gnome;
import com.gdx.gnomearena.Model.GnomeAttackPatterns.BasicAttack;
import com.gdx.gnomearena.Model.GnomeMovePatterns.BasicMovement;

public class BasicGnome extends Gnome
{
    public BasicGnome()
    {
        xp = 1;
        cost = 1;
        health = 1;
        maxHealth = 1;
        move = new BasicMovement();
        attack = new BasicAttack();
        moveCooldown = 2;
        currentCooldown = 2;
        spawnedItems = new ArrayList<>();
    }
}
