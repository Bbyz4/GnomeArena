package com.gdx.gnomearena.Model.Gnomes;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Model.Gnome;
import com.gdx.gnomearena.Model.GnomeAttackPatterns.BasicAttack;
import com.gdx.gnomearena.Model.GnomeMovePatterns.TrapMovement;

public class TrapGnome extends Gnome
{
    public TrapGnome()
    {
        xp = 1;
        cost = 1;
        health = 1;
        maxHealth = 1;
        move = new TrapMovement();
        attack = new BasicAttack();
        moveCooldown = 2;
        currentCooldown = 2;
        skin = new Texture("gnomeSprites/TrapGnome.png");
        spawnedItems = new ArrayList<>();
    }
}
