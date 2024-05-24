package com.gdx.gnomearena.Model.Items;

import com.gdx.gnomearena.Model.Board;
import com.gdx.gnomearena.Model.Item;
import com.gdx.gnomearena.Model.Player;
import com.gdx.gnomearena.Model.Weapon;
import com.gdx.gnomearena.Model.Weapons.Dagger;

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
