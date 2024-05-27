package com.gdx.gnomearena.Model;
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

        LevelManager.resetLevel();
    }


    public void handlePlayerInput(int keycode)
    {
        gameBoard.copyBoard();
        player.makeMove(gameBoard, keycode);
        moveAllGnomes();
        moveAllItems();
        gnomeSpawner.spawnNewWave(gameBoard, gnomeSpawner.newGnomesList(LevelManager.getScore()));
    }

    private void moveAllGnomes()
    {
        List<TurnEntity> list = GnomeMover.getGnomeMoveOrders(gameBoard);
        if(list!=null)
        {
            for(int i=0; i<list.size(); i++)
            {
                if(gameBoard.getEntitiesPosition(list.get(i))!=null)
                {
                    list.get(i).makeMove(gameBoard);
                }
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

    public int getScore(){
        return LevelManager.getScore();
    }

    public int getHP() {
        return player.health;
    }

}
