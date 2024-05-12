package com.gdx.gnomearena.Core.Gnomes;

import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Core.Board;
import com.gdx.gnomearena.Core.Gnome;
import com.gdx.gnomearena.Core.GnomeAttackPatterns.BasicAttack;
import com.gdx.gnomearena.Core.GnomeMovePatterns.BasicMovement;
import com.gdx.gnomearena.Core.Pair;
import com.gdx.gnomearena.Core.Entity;

public class TestingGnome extends Gnome
{
    public TestingGnome()
    {
        health = 1;
        move = new BasicMovement();
        attack = new BasicAttack();
        moveCooldown = 2;
        currentCooldown = 2;
        skin = new Texture("gnomeSprites/TestGnome.png");
    }

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

            int gX = board.getEntitiesPosition(this).getKey();
            int gY = board.getEntitiesPosition(this).getValue();

            boolean didGnomeAttacked = attack.attack(board, new Pair<Integer, Integer>(gX, gY));

            if (!didGnomeAttacked) {

                Pair<Integer, Integer> newPos = move.move(board, new Pair<Integer, Integer>(gX, gY));
                if (newPos != null) {
                    board.moveEntity(gX, gY, gX + newPos.getKey(), gY + newPos.getValue());
                }
            }

        }
    }

    @Override
    public void takeDamage(Board board, int damagePoints) {
        health = Math.max(0,health-damagePoints);
        if (health == 0)
            this.onDeath(board);
    }

    @Override
    public void onDeath(Board board) {
        board.removeEntity(this);

        //TESTING
        System.out.println("DEAD");
    }
}
