package com.gdx.gnomearena.Model.Gnomes;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Model.Gnome;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.GnomeAttackPatterns.BasicAttack;
import com.gdx.gnomearena.Model.GnomeMovePatterns.DiagonalMovement;
import com.gdx.gnomearena.Model.Items.EntitySpawningItem;
import com.gdx.gnomearena.Model.Items.Medkit;
import com.gdx.gnomearena.Model.TurnEntities.Bomb;

public class ScoutGnome extends Gnome
{
    public ScoutGnome()
    {
        xp = 1;
        health = 1;
        move = new DiagonalMovement();
        attack = new BasicAttack();
        moveCooldown = 1;
        currentCooldown = 1;
        skin = new Texture("gnomeSprites/ScoutGnome.png");
        spawnedItems = new ArrayList<>();

        spawnedItems.add(new Pair<>(Medkit::new, 0.4f));
        spawnedItems.add(new Pair<>(() -> new EntitySpawningItem(new Bomb(5,2)), 0.2f));
    }
}
