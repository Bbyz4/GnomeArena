package com.gdx.gnomearena.Core.Items;

import com.gdx.gnomearena.Core.Board;
import com.gdx.gnomearena.Core.Item;
import com.gdx.gnomearena.Core.Player;
import com.gdx.gnomearena.Core.Weapon;
import com.gdx.gnomearena.Core.Weapons.Dagger;

public class WeaponPickup extends Item
{
    Weapon spawnedWeapon;
    
    public WeaponPickup()
    {
        this(new Dagger());
    }

    public WeaponPickup(Weapon w)
    {
        spawnedWeapon = w;
        skin = w.skin;
    }

    @Override
    public boolean onPickup(Player user, Board board)
    {
        user.setHeldWeapon(spawnedWeapon);
        return true;
    }
}
