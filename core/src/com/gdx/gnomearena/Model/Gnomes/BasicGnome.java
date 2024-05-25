package com.gdx.gnomearena.Model.Gnomes;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Model.Gnome;
import com.gdx.gnomearena.Model.GnomeAttackPatterns.BasicAttack;
import com.gdx.gnomearena.Model.GnomeMovePatterns.BasicMovement;

public class BasicGnome extends Gnome
{
    public BasicGnome()
    {
        xp = 1;
        health = 1;
        move = new BasicMovement();
        attack = new BasicAttack();
        moveCooldown = 2;
        currentCooldown = 2;
        skin = new Texture("gnomeSprites/BasicGnome.png");
        spawnedItems = new ArrayList<>();
    }
}
