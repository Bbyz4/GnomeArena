package com.gdx.gnomearena.Model.Weapons;

import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Model.*;

public class Dagger extends Weapon {


    @SuppressWarnings("unchecked")
    public Dagger() {
        super();
        damagePoints = 1;
        
        attackRanges.put('S', new Pair[]
        {
                new Pair<>(0,1)
        });
        attackRanges.put('W', new Pair[]
        {
                new Pair<>(0,-1)
        });
        attackRanges.put('A', new Pair[]
        {
                new Pair<>(-1,0)
        });
        attackRanges.put('D', new Pair[]
        {
                new Pair<>(1, 0)
        });
        skin = new Texture("weaponSprites/Dagger.png");
    }

}
