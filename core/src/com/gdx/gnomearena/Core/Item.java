package com.gdx.gnomearena.Core;

import com.badlogic.gdx.graphics.Texture;

public abstract class Item
{
    //methods return true if an item should be removed from players inventory after an action, false otherwise
    public boolean onPickup(Player user, Board board)
    {
        return false;
    }

    public boolean affect(Player user, Board board)
    {
        return false;
    };

    public int maxLifetime = 10;
    public int currentLifetime = 0;

    public void passRound(Board board)
    {
        currentLifetime++;
        if(currentLifetime==maxLifetime)
        {
            Pair<Integer,Integer> pos = board.getItemsPosition(this);
            board.removeItem(pos.getKey(), pos.getValue());
        }
    }

    public Texture skin;
}
