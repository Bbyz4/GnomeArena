package com.gdx.gnomearena.Model;

import java.util.Random;

public abstract class Gnome extends TurnEntity
{
    public GnomeMovePattern move;
    public GnomeAttackPattern attack;
    
    public int xp;
    public int cost;

    @Override
    public void makeMove(Board board)
    {
        currentCooldown--;
        if(currentCooldown==0) {
            currentCooldown = moveCooldown;
            if (health == 0) {
                onDeath(board);
                return;
            }

            boolean didGnomeAttack = attack.attack(board, this);

            if (!didGnomeAttack) {
                boolean didGnomeMove = move.move(board, this);
                if (!didGnomeMove)
                {
                    currentCooldown = 1;
                }
            }

        }
    }

    @Override
    public void onSpawn(Board board)
    {

    }

    @Override
    public void takeDamage(Board board, int damagePoints) {
        health = Math.max(0,health-damagePoints);
        if (health == 0)
            this.onDeath(board);
    }

    @Override
    public void onDeath(Board board)
    {
        float randItem = new Random().nextFloat();
        float currentItem = 0f;

        for(int i=0; i<spawnedItems.size(); i++)
        {
            if(randItem < spawnedItems.get(i).getValue() + currentItem)
            {
                Pair<Integer,Integer> entPos = board.getEntitiesPosition(this);
                if(board.getItem(entPos.getKey(), entPos.getValue())==null)
                {
                    board.spawnItem(spawnedItems.get(i).getKey().get(), entPos.getKey(), entPos.getValue());
                }
                break;
            }
            else
            {
                currentItem += spawnedItems.get(i).getValue();
            }
        }

        board.removeEntity(this);
        LevelManager.increaseScore(xp);
    }
}
