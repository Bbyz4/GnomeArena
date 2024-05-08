package com.gdx.gnomearena.Core;

import java.util.List;

public class GameManager
{
    private final Board gameBoard;
    private final GnomeSpawner gnomeSpawner;
    private final Player player;

    public GameManager()
    {
        gameBoard = new Board();
        gnomeSpawner = new GnomeSpawner();
        player = new Player();
        gameBoard.spawnEntity(player, gameBoard.middle(), gameBoard.middle());
    }


    public void handlePlayerInput(int keycode)
    {
        player.makeMove(gameBoard, keycode);
        moveAllGnomes();
        //TESTING
        System.out.println(gameBoard.getPlayerX() + " " + gameBoard.getPlayerY());
    }

    private void moveAllGnomes()
    {
        List<Gnome> list = gameBoard.getGnomeMoveOrders();
        if(list!=null)
        {
            for(int i=0; i<list.size(); i++)
            {
                list.get(i).makeMove(gameBoard);
            }
        }
    }
}
