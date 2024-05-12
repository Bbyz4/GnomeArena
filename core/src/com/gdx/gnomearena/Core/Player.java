package com.gdx.gnomearena.Core;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.lang.constant.ConstantDesc;

public class Player extends Entity
{
    Weapon heldWeapon;
    Item heldItem;

    Player()
    {
        health = 3;
        //heldWeapon = starting weapon
        heldItem = null;
        skin = new Texture("otherSprites/Player.png");
    }

    void makeMove(Board board, int keycode)
    {
        int pX = board.getPlayersPosition().getKey();
        int pY = board.getPlayersPosition().getValue();
        switch(keycode) {
            case Input.Keys.W:
                if(board.isValid(pX, pY-1) && board.isEmpty(pX, pY-1))
                {

                    board.moveEntity(pX, pY, pX, pY-1);
                }
                break;
            case Input.Keys.A:
                if(board.isValid(pX-1, pY) && board.isEmpty(pX-1, pY))
                {
                    board.moveEntity(pX, pY, pX-1, pY);
                }
                break;
            case Input.Keys.S:
                if(board.isValid(pX, pY+1) && board.isEmpty(pX, pY+1))
                {
                    board.moveEntity(pX, pY, pX, pY+1);
                }
                break;
            case Input.Keys.D:
                if(board.isValid(pX+1, pY) && board.isEmpty(pX+1, pY))
                {
                    board.moveEntity(pX, pY, pX+1, pY);
                }
                break;
            case Input.Keys.E:
                if(heldItem!=null)
                {
                    heldItem.affect(this);
                }
                break;
            default:
                // you got gnomed
                break;
        }
    }

    @Override
    public void takeDamage(int damagepoints) {
        health = Math.max(0, health - damagepoints);
        if (health == 0) {
            onDeath();
        }
    }

    @Override
    public void onDeath() {

    }
}
