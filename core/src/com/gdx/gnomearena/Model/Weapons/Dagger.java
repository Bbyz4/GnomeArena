package com.gdx.gnomearena.Model.Weapons;

import com.gdx.gnomearena.Model.*;

public class Dagger extends Weapon {


    @SuppressWarnings("unchecked")
    public Dagger() {
        super();
        damagePoints = 1;
        
        attackRanges.put(Direction.DOWN, new Pair[]
        {
                new Pair<>(0,1)
        });
        attackRanges.put(Direction.UP, new Pair[]
        {
                new Pair<>(0,-1)
        });
        attackRanges.put(Direction.LEFT, new Pair[]
        {
                new Pair<>(-1,0)
        });
        attackRanges.put(Direction.RIGHT, new Pair[]
        {
                new Pair<>(1, 0)
        });
    }

}
