package com.gdx.gnomearena.Core.Weapons;

import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Core.Pair;

//it's just a dagger, but with extra range
public class Spear extends Dagger
{
    @SuppressWarnings("unchecked")
    public Spear()
    {
        super();
        skin = new Texture("weaponSprites/Spear.png");
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
    }
}
