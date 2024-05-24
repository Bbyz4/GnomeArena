package com.gdx.gnomearena.Model.Weapons;

import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.Weapon;

//it's just a dagger, but with extra range
public class Spear extends Weapon
{
    @SuppressWarnings("unchecked")
    public Spear()
    {
        super();
        damagePoints = 1;
        attackRangeDown = new Pair[]
                {
                        new Pair<>(0,1),
                        new Pair<>(0,2)
                };
        attackRangeUp = new Pair[]
                {
                        new Pair<>(0,-1),
                        new Pair<>(0,-2),
                };
        attackRangeLeft = new Pair[]
                {
                        new Pair<>(-1,0),
                        new Pair<>(-2,0),
                };
        attackRangeRight = new Pair[]
                {
                        new Pair<>(1, 0),
                        new Pair<>(2, 0),
                };
        updateRanges();
        skin = new Texture("weaponSprites/Spear.png");
    }
}
