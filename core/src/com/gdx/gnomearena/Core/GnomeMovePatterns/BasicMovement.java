package com.gdx.gnomearena.Core.GnomeMovePatterns;

import com.gdx.gnomearena.Core.Board;
import com.gdx.gnomearena.Core.GnomeMovePattern;
import com.gdx.gnomearena.Core.Gnomes.TestingGnome;
import com.gdx.gnomearena.Core.Pair;
import com.gdx.gnomearena.Core.GnomeAttackPatterns.BasicAttack;
import com.gdx.gnomearena.Core.GnomeAttackPattern;
public class BasicMovement extends GnomeMovePattern
{
    @SuppressWarnings("unchecked")
    public BasicMovement()
    {
        moveRange = new Pair[]
        {
            new Pair<>(1, 0),
            new Pair<>(-1,0),
            new Pair<>(0,1),
            new Pair<>(0,-1)
        };
    }

    @Override
    public Pair<Integer,Integer> move(Board board, Pair<Integer,Integer> myPosition)
    {
        Pair<Integer,Integer> playerPos = board.getPlayersPosition();


        for(Pair<Integer,Integer> ranges : moveRange)
        {
            if(board.isValid(myPosition.getKey() + ranges.getKey(), myPosition.getValue() + ranges.getValue()) && board.isEmpty(myPosition.getKey() + ranges.getKey(), myPosition.getValue() + ranges.getValue()) && Math.abs(playerPos.getKey()-myPosition.getKey()) + Math.abs(playerPos.getValue()-myPosition.getValue()) > Math.abs(playerPos.getKey()-myPosition.getKey()-ranges.getKey()) + Math.abs(playerPos.getValue()-myPosition.getValue()-ranges.getValue()))
            {
                return ranges;
            }
        }
        return null;
    }
}
