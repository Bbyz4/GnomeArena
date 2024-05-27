package com.gdx.gnomearena.Model.Weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Model.Direction;
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

        attackRanges.put(Direction.DOWN, new Pair[]
        {
                new Pair<>(0,1),
                new Pair<>(0,2)
        });
        attackRanges.put(Direction.UP, new Pair[]
        {
                new Pair<>(0,-1),
                new Pair<>(0,-2)
        });
        attackRanges.put(Direction.LEFT, new Pair[]
        {
                new Pair<>(-1,0),
                new Pair<>(-2,0)
        });
        attackRanges.put(Direction.RIGHT, new Pair[]
        {
                new Pair<>(1, 0),
                new Pair<>(2, 0)
        });
        skin = new Texture("weaponSprites/Spear.png");
        attackSound =  Gdx.audio.newSound(Gdx.files.internal("sounds/SpearHitSound.mp3"));

    }
}
