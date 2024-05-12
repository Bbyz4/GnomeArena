package com.gdx.gnomearena.Core.Weapons;

import com.gdx.gnomearena.Core.Entity;
import com.gdx.gnomearena.Core.Player;
import com.gdx.gnomearena.Core.Weapon;

public class BasicWeapon extends Weapon {


    public BasicWeapon() {
        damagePoints = 1;
    }

    @Override
    public void damage(Entity e) {
        e.takeDamage(damagePoints);
    }



}
