package com.gdx.gnomearena.Model.Gnomes;

import java.util.ArrayList;

import com.gdx.gnomearena.Model.Gnome;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.GnomeAttackPatterns.BasicAttack;
import com.gdx.gnomearena.Model.GnomeMovePatterns.DiagonalMovement;
import com.gdx.gnomearena.Model.Items.Medkit;

public class ScoutGnome extends Gnome
{
    public ScoutGnome()
    {
        xp = 1;
        cost = 2;
        health = 1;
        maxHealth = 1;
        move = new DiagonalMovement();
        attack = new BasicAttack();
        moveCooldown = 1;
        currentCooldown = 1;
        spawnedItems = new ArrayList<>();

        spawnedItems.add(new Pair<>(Medkit::new, 0.5f));
    }
}
