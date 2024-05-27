package com.gdx.gnomearena.Model.Weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Model.Direction;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.Weapon;

//wide range + can hit multiple gnomes at once
public class Hammer extends Weapon
{
    @SuppressWarnings("unchecked")
    public Hammer()
    {
        super();
        damagePoints = 1;
        canMultishot = true;
        
        attackRanges.put(Direction.DOWN, new Pair[]
        {
                new Pair<>(0,1),
                new Pair<>(1,1),
                new Pair<>(-1,1),
        });
        attackRanges.put(Direction.UP, new Pair[]
        {
            new Pair<>(0,-1),
            new Pair<>(1,-1),
            new Pair<>(-1,-1),
        });
        attackRanges.put(Direction.LEFT, new Pair[]
        {
            new Pair<>(-1,0),
            new Pair<>(-1,-1),
            new Pair<>(-1,1),
        });
        attackRanges.put(Direction.RIGHT, new Pair[]
        {
            new Pair<>(1,0),
            new Pair<>(1,1),
            new Pair<>(1,-1),
        });
        skin = new Texture("weaponSprites/Hammer.png");
        attackSound =  Gdx.audio.newSound(Gdx.files.internal("sounds/HammerHitSound.mp3"));

    }
}
