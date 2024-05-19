package com.gdx.gnomearena.Core.Weapons;

import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Core.*;

public class Dagger extends Weapon {


    @SuppressWarnings("unchecked")
    public Dagger() {
        super();
        damagePoints = 1;
        attackRangeDown = new Pair[]
                {
                        new Pair<>(0,1)
                };
        attackRangeUp = new Pair[]
                {
                        new Pair<>(0,-1),
                };
        attackRangeLeft = new Pair[]
                {
                        new Pair<>(-1,0),
                };
        attackRangeRight = new Pair[]
                {
                        new Pair<>(1, 0),
                };
        updateRanges();
        skin = new Texture("weaponSprites/Dagger.png");
    }

}
