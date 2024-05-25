package com.gdx.gnomearena.Model.GnomeAttackPatterns;

import com.gdx.gnomearena.Model.GnomeAttackPattern;
import com.gdx.gnomearena.Model.Pair;

public class BasicAttack extends GnomeAttackPattern {


    @SuppressWarnings("unchecked")
    public BasicAttack() {
        damagePoints = 1;
        attackRange = new Pair[]
        {
                new Pair<>(1, 0),
                new Pair<>(-1,0),
                new Pair<>(0,1),
                new Pair<>(0,-1)
        };
    }

}
