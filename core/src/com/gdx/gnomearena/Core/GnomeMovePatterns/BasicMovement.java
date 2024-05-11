package com.gdx.gnomearena.Core.GnomeMovePatterns;

import com.gdx.gnomearena.Core.Board;
import com.gdx.gnomearena.Core.GnomeMovePattern;

import javafx.util.Pair;

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
    public Pair<Integer,Integer> move(Board b, Pair<Integer,Integer> myPosition)
    {
        Pair<Integer,Integer> playerPos = b.getPlayersPosition();

        for(Pair<Integer,Integer> ranges : moveRange)
        {
            if(b.isValid(myPosition.getKey() + ranges.getKey(), myPosition.getValue() + ranges.getValue()) && b.isEmpty(myPosition.getKey() + ranges.getKey(), myPosition.getValue() + ranges.getValue()) && Math.abs(playerPos.getKey()-myPosition.getKey()) + Math.abs(playerPos.getValue()-myPosition.getValue()) > Math.abs(playerPos.getKey()-myPosition.getKey()-ranges.getKey()) + Math.abs(playerPos.getValue()-myPosition.getValue()-ranges.getValue()))
            {
                return ranges;
            }
        }
        return null;
    }
}
