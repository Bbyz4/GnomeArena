package com.gdx.gnomearena.Core;

import com.badlogic.gdx.Input;

public class Player extends Entity
{
    Weapon heldWeapon;
    Item heldItem;

    Player()
    {
        health = 3;
        //heldWeapon = starting weapon
        heldItem = null;
    }

    void makeMove(Board b, int keycode)
    {
        int pX = b.getPlayerX();
        int pY = b.getPlayerY();
        switch(keycode) {
            case Input.Keys.W:
                if(b.isValid(pX, pY-1) && b.isEmpty(pX, pY-1))
                {
                    b.moveEntity(pX, pY, pX, pY-1);
                }
                break;
            case Input.Keys.A:
                if(b.isValid(pX-1, pY) && b.isEmpty(pX-1, pY))
                {
                    b.moveEntity(pX, pY, pX-1, pY);
                }
                break;
            case Input.Keys.S:
                if(b.isValid(pX, pY+1) && b.isEmpty(pX, pY+1))
                {
                    b.moveEntity(pX, pY, pX, pY+1);
                }
                break;
            case Input.Keys.D:
                if(b.isValid(pX+1, pY) && b.isEmpty(pX+1, pY))
                {
                    b.moveEntity(pX, pY, pX+1, pY);
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
}
