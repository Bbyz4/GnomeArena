package com.gdx.gnomearena.Model.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Model.Board;
import com.gdx.gnomearena.Model.Item;
import com.gdx.gnomearena.Model.Player;
import com.gdx.gnomearena.Model.SoundsManager;

public class Medkit extends Item
{
    public Medkit()
    {
        skin = new Texture("itemSprites/Medkit.png");
        maxLifetime = 10;
        onUseSound = Gdx.audio.newSound(Gdx.files.internal("sounds/MedKitUseSound.mp3"));
    }

    @Override
    public boolean affect(Player user, Board board)
    {
        user.health = Math.min(user.health+1, user.maxHealth);
        SoundsManager.addSound(onUseSound);
        return true;
    }
}
