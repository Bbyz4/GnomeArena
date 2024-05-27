package com.gdx.gnomearena.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Model.Items.WeaponPickup;
import com.gdx.gnomearena.Model.Weapons.Dagger;

import java.util.ArrayList;
import java.util.Iterator;

public class Player extends Entity
{
    Weapon heldWeapon;
    Item heldItem;
    boolean isPlayerDead;
    Weapon droppedWeapon;
    Sound takeDamageSound;
    Player()
    {
        health = 3;
        maxHealth = 3;
        heldWeapon = new Dagger();
        heldItem = null;
        skin = new Texture("playerSprites/Player.png");
        isPlayerDead = false;
        currentDirection = Direction.UP;
        immobilized = false;
        currentEffects = new ArrayList<>();
        takeDamageSound = Gdx.audio.newSound(Gdx.files.internal("sounds/PlayerTakeDamageSound.mp3"));
    }

    public Item getHeldItem()
    {
        return heldItem;
    }

    public Weapon getHeldWeapon()
    {
        return heldWeapon;
    }

    public Direction getPlayerDirection()
    {
        return currentDirection;
    }

    public void setHeldItem(Item item)
    {
       heldItem = item; 
    }

    public void setHeldWeapon(Weapon w)
    {
        droppedWeapon = heldWeapon;
        heldWeapon = w;
    }

    public void setPlayerDirection(Direction dir)
    {
        currentDirection = dir;
    }

    void makeMove(Board board, int keycode)
    {
        for (Iterator<Effect> iterator = currentEffects.iterator(); iterator.hasNext(); )
        {
            Effect effect = iterator.next();
            effect.affect(this);
            if(effect.hasEnded)
            {
                iterator.remove();
            }
        }

        if (immobilized)
            return;

        if(keycode==-1)
        {
            return;
        }


        if(keycode==Input.Keys.E)
        {
            if(heldItem!=null)
            {
                if(heldItem.affect(this,board))
                {
                    heldItem=null;
                }
            }
        }

        int pX = board.getPlayersPosition().getKey();
        int pY = board.getPlayersPosition().getValue();

        if(Direction.getDirection(keycode)!=null)
        {
            currentDirection = Direction.getDirection(keycode);
        }
        else
        {
            return;
        }
        //boolean didAttack = heldWeapon.attack(board,Direction.getDirection(keycode));
        if(!heldWeapon.attack(board, Direction.getDirection(keycode)))
        {
            Pair<Integer,Integer> targetTile = Direction.getFieldsFrontField(board.getPlayersPosition(), currentDirection);
            if(board.isValid(targetTile.getKey(), targetTile.getValue()) && board.isEmpty(targetTile.getKey(), targetTile.getValue()))
            {
                board.moveEntity(pX, pY, targetTile.getKey(), targetTile.getValue());

                //drop the previous weapon
                if(droppedWeapon!=null)
                {
                    board.spawnItem(new WeaponPickup(droppedWeapon), pX, pY);
                    droppedWeapon=null;
                }
            }
        }

        pX = board.getPlayersPosition().getKey();
        pY = board.getPlayersPosition().getValue();

        if(board.getItem(pX, pY)!=null)
        {
            Item tempItem = board.getItem(pX, pY);
            if(!tempItem.onPickup(this, board))
            {
                heldItem = tempItem;
            }
            board.removeItem(pX, pY);
        }
    }

    @Override
    public void takeDamage(Board board, int damagePoints) {
        health = Math.max(0, health - damagePoints);
        if (health == 0) {
            onDeath(board);
        }

        board.soundList.add(takeDamageSound);

    }

    @Override
    public void onDeath(Board board) {
        isPlayerDead = true;

    }
}
