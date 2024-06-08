package com.gdx.gnomearena.Model.TurnEntities;

import com.gdx.gnomearena.Model.NonBlockingEntity;
import com.gdx.gnomearena.Model.Board;
import com.gdx.gnomearena.Model.*;
import com.gdx.gnomearena.Model.Effects.Stun;
public class Trap extends NonBlockingEntity
{

    public Trap()
    {

    }

    @Override
    public void makeMove(Board board)
    {
    }

    public void onReplace(Board board, Entity replacer)
    {
        if(replacer instanceof Player)
        {
            replacer.currentEffects.add(new Stun());
        }
        onDeath(board);
    }

    @Override
    public void onSpawn(Board board)
    {
        return;
    }

    @Override
    public void takeDamage(Board board, int damagepoints)
    {
        return;
    }

    @Override
    public void onDeath(Board board)
    {
        board.removeEntity(this);
    }

}
