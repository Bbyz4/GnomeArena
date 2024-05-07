package com.gdx.gnomearena.Core;

public class GameManager
{
    private final Board gameBoard;


    public GameManager()
    {
        gameBoard = new Board();
    }


    public void handlePlayerInput(int keycode)
    {
        System.out.println(keycode);
    }

    private void moveAllGnomes()
    {

    }
}
