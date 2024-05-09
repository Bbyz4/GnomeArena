package com.gdx.gnomearena.Core;

import javafx.util.Pair;

public abstract class Gnome extends Entity
{
    GnomeMovePattern move;
    GnomeAttackPattern attack;

    public void makeMove(Board b)
    {
        if(health==0)
        {
            onDeath();
            return;
        }

        int gX = b.getEntitiesPosition(this).getKey();
        int gY = b.getEntitiesPosition(this).getKey();

        for(int i=0; i<attack.attackRange.length; i++)
        {
            if(b.getPlayersPosition() == new Pair<Integer,Integer>(gX + attack.attackRange[i].getKey(), gY + attack.attackRange[i].getValue()))
            {
                attack.attack(b, new Pair<Integer,Integer>(gX, gY), b.getPlayersPosition());
                return;
            }
        }
        
        move.move(b, new Pair<Integer,Integer>(gX, gY));
    }   
    public void onSpawn(){}
    public void onDeath(){}
}
