package com.gdx.gnomearena.Model.GnomeMovePatterns;

import com.gdx.gnomearena.Model.GnomeMovePattern;
import com.gdx.gnomearena.Model.Pair;

public class DiagonalMovement extends GnomeMovePattern
{
    @SuppressWarnings("unchecked")
    public DiagonalMovement()
    {
        moveRange = new Pair[]
        {
            new Pair<>(1, 1),
            new Pair<>(-1,1),
            new Pair<>(1,-1),
            new Pair<>(-1,-1),
            new Pair<>(1, 0),
            new Pair<>(-1,0),
            new Pair<>(0,1),
            new Pair<>(0,-1)
        };
    }
}
