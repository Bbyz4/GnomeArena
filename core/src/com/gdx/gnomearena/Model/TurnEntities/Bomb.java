package com.gdx.gnomearena.Model.TurnEntities;

import java.util.ArrayList;
import java.util.List;

import com.gdx.gnomearena.Model.Board;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.TurnEntity;

public class Bomb extends TurnEntity
{
    private int boomTime;
    private final int dealtDamage;
    private final List<Pair<Integer,Integer>> damageRange;

    public Bomb()
    {
        this(5,2);
    }

    public Bomb(int explosionTime, int damage)
    {
        boomTime = explosionTime;
        moveCooldown = 1;
        currentCooldown = 1;
        dealtDamage = 2;
        health = 1;
        maxHealth = 1;

        damageRange = new ArrayList<>();
        
        damageRange.add(new Pair<>(1, 0));
        damageRange.add(new Pair<>(-1, 0));
        damageRange.add(new Pair<>(0, 1));
        damageRange.add(new Pair<>(0, -1));
        damageRange.add(new Pair<>(1, 1));
        damageRange.add(new Pair<>(1, -1));
        damageRange.add(new Pair<>(-1, 1));
        damageRange.add(new Pair<>(-1, -1));
    }

    @Override
    public void makeMove(Board board)
    {
        boomTime--;

        if(boomTime<=0)
        {
            Pair<Integer,Integer> bombsPosition = board.getEntitiesPosition(this);
            for(Pair<Integer,Integer> range : damageRange)
            {
                Pair<Integer,Integer> attackedTile = new Pair<>(bombsPosition.getKey()+range.getKey(), bombsPosition.getValue()+range.getValue());
                if(board.isValid(attackedTile.getKey(), attackedTile.getValue()) && !board.isEmpty(attackedTile.getKey(), attackedTile.getValue()))
                {
                    board.get(attackedTile.getKey(), attackedTile.getValue()).takeDamage(board, dealtDamage);
                }
            }

            onDeath(board);
        }
    }

    @Override
    public void onSpawn(Board board)
    {

    }

    @Override
    public void takeDamage(Board board, int damagepoints)
    {
        //cant touch this
    }

    @Override
    public void onDeath(Board board)
    {
        board.removeEntity(this);
    }

}
