package com.gdx.gnomearena.Core.Gnomes;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Core.Board;
import com.gdx.gnomearena.Core.Gnome;
import com.gdx.gnomearena.Core.GnomeAttackPatterns.BasicAttack;
import com.gdx.gnomearena.Core.GnomeMovePatterns.BasicMovement;
import com.gdx.gnomearena.Core.Items.Medkit;
import com.gdx.gnomearena.Core.Items.WeaponPickup;
import com.gdx.gnomearena.Core.Weapons.Spear;
import com.gdx.gnomearena.Core.Pair;

public class BasicGnome extends Gnome
{
    public BasicGnome()
    {
        xp = 1;
        health = 1;
        move = new BasicMovement();
        attack = new BasicAttack();
        moveCooldown = 2;
        currentCooldown = 2;
        skin = new Texture("gnomeSprites/TestGnome.png");
        spawnedItems = new ArrayList<>();

        spawnedItems.add(new Pair<>(Medkit::new, 0.5f));
        spawnedItems.add(new Pair<>(() -> new WeaponPickup(new Spear()), 0.2f));
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
    public void onSpawn(Board board)
    {
        //nice board bro
    }

    @Override
    public void onDeath(Board board) {
        for(int i=0; i<spawnedItems.size(); i++)
        {
            if(new Random().nextFloat() < spawnedItems.get(i).getValue())
            {
                Pair<Integer,Integer> entPos = board.getEntitiesPosition(this);
                board.spawnItem(spawnedItems.get(i).getKey().get(), entPos.getKey(), entPos.getValue());
            }
        }

        board.removeEntity(this);
        board.updateScore(xp);
    }
}
