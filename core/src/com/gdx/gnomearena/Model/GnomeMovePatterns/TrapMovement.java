package com.gdx.gnomearena.Model.GnomeMovePatterns;

import com.gdx.gnomearena.Model.Board;
import com.gdx.gnomearena.Model.Gnome;
import com.gdx.gnomearena.Model.GnomeMovePattern;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.TurnEntities.Trap;

import java.util.Random;

public class TrapMovement extends GnomeMovePattern {
    @SuppressWarnings("unchecked")
    public TrapMovement()
    {
        moveRange = new Pair[]
                {
                        new Pair<>(1, 1),
                        new Pair<>(-1, 1),
                        new Pair<>(1, -1),
                        new Pair<>(-1, -1),
                        new Pair<>(1, 0),
                        new Pair<>(-1, 0),
                        new Pair<>(0, 1),
                        new Pair<>(0, -1)
                };
    }


    @Override
    public boolean move(Board board, Gnome mover)
    {
        Random rand = new Random();
        Pair<Integer,Integer> gnomeOldPosition = board.getEntitiesPosition(mover);
        int randomNumber = rand.nextInt(4);
        boolean didGnomeMove = super.move(board,mover);
        System.out.println(gnomeOldPosition);
        System.out.println(board.getEntitiesPosition(mover));
        if (randomNumber == 0 && didGnomeMove)
        {
            board.spawnEntity(new Trap(), gnomeOldPosition.getKey(), gnomeOldPosition.getValue());
        }
        return didGnomeMove;

    }

}