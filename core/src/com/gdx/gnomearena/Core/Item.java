package com.gdx.gnomearena.Core;

import com.badlogic.gdx.graphics.Texture;

public abstract class Item
{
    public boolean affect(Entity user)
    {
        return false;
    };

    public int maxLifetime;
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
