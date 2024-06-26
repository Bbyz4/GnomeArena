package com.gdx.gnomearena.Model.TurnEntities;

import com.gdx.gnomearena.Model.Board;
import com.gdx.gnomearena.Model.Direction;
import com.gdx.gnomearena.Model.*;

public class MagicBullet extends NonBlockingEntity
{
    private int dealtDamage;

    public MagicBullet()
    {
        this(Direction.RIGHT, 1);
    }

    public MagicBullet(Direction dir, int damage)
    {
        dealtDamage = damage;
        currentDirection = dir;
    }

    @Override
    public void makeMove(Board board)
    {
        Pair<Integer,Integer> currentTile = board.getEntitiesPosition(this);
        Pair<Integer,Integer> nextTile = Direction.getFieldsFrontField(currentTile, currentDirection);

        if(board.isValid(nextTile.getKey(), nextTile.getValue()))
        {
            if(board.isEmpty(nextTile.getKey(), nextTile.getValue()))
            {
                board.moveEntity(currentTile.getKey(), currentTile.getValue(), nextTile.getKey(), nextTile.getValue());
            }
            else
            {
                if(board.get(nextTile.getKey(), nextTile.getValue()) instanceof Player)
                {
                    board.get(nextTile.getKey(), nextTile.getValue()).takeDamage(board, dealtDamage);
                }
                onDeath(board);
            }
        }
        else
        {
            this.onDeath(board);
        }
    }

    public void onReplace(Board board, Entity replacer)
    {
        if(replacer instanceof Player)
        {
            replacer.takeDamage(board, dealtDamage);
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
