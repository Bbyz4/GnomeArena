package com.gdx.gnomearena.Model.Items;

import com.gdx.gnomearena.Model.Board;
import com.gdx.gnomearena.Model.GameLogs;
import com.gdx.gnomearena.Model.Item;
import com.gdx.gnomearena.Model.Player;

public class Medkit extends Item
{
    public Medkit()
    {
        maxLifetime = 10;
    }

    @Override
    public boolean affect(Player user, Board board)
    {
        user.health = Math.min(user.health+1, user.maxHealth);
        GameLogs.addItemUsage(this);
        return true;
    }
}
