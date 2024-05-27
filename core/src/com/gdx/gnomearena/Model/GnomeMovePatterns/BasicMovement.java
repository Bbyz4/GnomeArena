package com.gdx.gnomearena.Model.GnomeMovePatterns;

import java.util.Arrays;
import java.util.Collections;

import com.gdx.gnomearena.Model.Board;
import com.gdx.gnomearena.Model.Gnome;
import com.gdx.gnomearena.Model.GnomeMovePattern;
import com.gdx.gnomearena.Model.Pair;
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
    public boolean move(Board board, Gnome mover)
    {
        Collections.shuffle(Arrays.asList(moveRange));
        return super.move(board, mover);
    }
}
