package com.gdx.gnomearena.Model.Gnomes;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Model.Gnome;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.GnomeAttackPatterns.MagicShootingAttack;
import com.gdx.gnomearena.Model.GnomeMovePatterns.BasicMovement;
import com.gdx.gnomearena.Model.Items.WeaponPickup;
import com.gdx.gnomearena.Model.Weapons.Hammer;
import com.gdx.gnomearena.Model.Weapons.Spear;

public class WizardGnome extends Gnome
{

    public WizardGnome()
    {
        xp = 2;
        health = 1;
        maxHealth = 1;
        move = new BasicMovement();
        attack = new MagicShootingAttack(5);
        moveCooldown = 2;
        currentCooldown = 2;
        skin = new Texture("gnomeSprites/WizardGnome.png");
        spawnedItems = new ArrayList<>();

        spawnedItems.add(new Pair<>(() -> new WeaponPickup(new Spear()), 0.2f));
        spawnedItems.add(new Pair<>(() -> new WeaponPickup(new Hammer()), 0.2f));
    }

}
