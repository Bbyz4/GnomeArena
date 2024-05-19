package com.gdx.gnomearena.Core;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.gdx.gnomearena.Core.Weapons.Dagger;

public class Player extends Entity
{
    Weapon heldWeapon;
    Item heldItem;
    Direction currentDirection;
    public boolean isPlayerDead;
    Player()
    {
        health = 3;
        maxHealth = 3;
        heldWeapon = new Dagger();
        heldItem = null;
        skin = new Texture("otherSprites/Player.png");
        isPlayerDead = false;
        currentDirection = Direction.UP;
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
        heldWeapon = w;
    }

    public void setPlayerDirection(Direction dir)
    {
        currentDirection = dir;
    }

    void makeMove(Board board, int keycode)
    {
        int pX = board.getPlayersPosition().getKey();
        int pY = board.getPlayersPosition().getValue();
        boolean didPlayerAttack;

        if(Direction.getDirection(keycode)!=null)
        {
            currentDirection = Direction.getDirection(keycode);
            //TESTING
            System.out.println("DIRECTION CHANGED TO " + currentDirection);
        }

        switch(keycode) {
            case Input.Keys.W:
                didPlayerAttack = heldWeapon.attack(board,'W');
                if (!didPlayerAttack) {
                    if (board.isValid(pX, pY - 1) && board.isEmpty(pX, pY - 1)) {
                        board.moveEntity(pX, pY, pX, pY - 1);
                    }
                }
                break;
            case Input.Keys.A:
                didPlayerAttack = heldWeapon.attack(board,'A');
                if (!didPlayerAttack) {
                    if (board.isValid(pX - 1, pY) && board.isEmpty(pX - 1, pY)) {
                        board.moveEntity(pX, pY, pX - 1, pY);
                    }
                }
                break;
            case Input.Keys.S:
                didPlayerAttack = heldWeapon.attack(board,'S');
                if (!didPlayerAttack) {
                    if (board.isValid(pX, pY + 1) && board.isEmpty(pX, pY + 1)) {
                        board.moveEntity(pX, pY, pX, pY + 1);
                    }
                }
                break;
            case Input.Keys.D:
                didPlayerAttack = heldWeapon.attack(board,'D');
                if (!didPlayerAttack) {
                    if(board.isValid(pX+1, pY) && board.isEmpty(pX+1, pY))
                {
                    board.moveEntity(pX, pY, pX+1, pY);
                }
                    }
                break;
            case Input.Keys.E:
                if(heldItem!=null)
                {
                    if(heldItem.affect(this,board))
                    {
                        heldItem=null;
                    }
                }
                break;
            default:
                // you got gnomed
                break;
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
    }

    @Override
    public void onDeath(Board board) {
        isPlayerDead = true;

    }
}
