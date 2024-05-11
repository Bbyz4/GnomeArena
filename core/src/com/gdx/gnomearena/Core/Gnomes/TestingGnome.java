package com.gdx.gnomearena.Core.Gnomes;

import com.gdx.gnomearena.Core.Board;
import com.gdx.gnomearena.Core.Gnome;
import com.gdx.gnomearena.Core.GnomeMovePatterns.BasicMovement;
import com.gdx.gnomearena.Core.Pair;

public class TestingGnome extends Gnome
{
    public TestingGnome()
    {
        health = 69;
        move = new BasicMovement();
        attack = null;
        moveCooldown = 2;
        currentCooldown = 2;
    }

    @Override
    public void makeMove(Board b)
    {
       currentCooldown--;
        if(currentCooldown==0)
        {
            currentCooldown = moveCooldown;
            if(health==0)
            {
                onDeath();
                return;
            }

            int gX = b.getEntitiesPosition(this).getKey();
            int gY = b.getEntitiesPosition(this).getValue();
            
            //TESTING
            System.out.println(this + " POSITION BEFORE MOVE:");
            System.out.println(b.getEntitiesPosition(this));

            Pair<Integer,Integer> newPos = move.move(b, new Pair<Integer,Integer>(gX, gY));
            if(newPos!=null)
            {
                b.moveEntity(gX, gY, gX+newPos.getKey(), gY+newPos.getValue());
            }
            //TESTING
            System.out.println(this + " POSITION AFTER MOVE:");
            System.out.println(b.getEntitiesPosition(this));
        } 
    }
}
