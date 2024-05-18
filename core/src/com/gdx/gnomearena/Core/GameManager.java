package com.gdx.gnomearena.Core;

import java.util.List;

public class GameManager
{
    public final Board gameBoard;
    public final GnomeSpawner gnomeSpawner;
    public final Player player;

    public GameManager()
    {
        gameBoard = new Board();
        gnomeSpawner = new GnomeSpawner();
        player = new Player();
        gameBoard.spawnEntity(player, gameBoard.middle(), gameBoard.middle());
    }


    public void handlePlayerInput(int keycode)
    {
        gameBoard.copyBoard();
        player.makeMove(gameBoard, keycode);
        moveAllGnomes();
        moveAllItems();
        gameBoard.spawnNewWave(gnomeSpawner.newGnomesList(3)); // hardcoded gnome amount
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

    private void moveAllItems()
    {
        List<Item> list = gameBoard.getAllItems();
        if(list!=null)
        {
            for(int i=0; i<list.size(); i++)
            {
                list.get(i).passRound(gameBoard);
            }
        }
    }

    public boolean isOver() {
        return player.isPlayerDead;
    }

    public int getScore() {
        return gameBoard.getScore();
    }

    public int getHP() {
        return player.health;
    }

}
