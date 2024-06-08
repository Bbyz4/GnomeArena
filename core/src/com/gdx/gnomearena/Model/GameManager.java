package com.gdx.gnomearena.Model;
import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.IntSet;

public class GameManager
{
    public final Board gameBoard;
    public final GnomeSpawner gnomeSpawner;
    public final Player player;

    public final float pace = 0.6f; //MOVE THOSE TO CONFIG FILES LATER
    public final float clickWindow = 0.4f;
    public boolean keyHandled = false;
    public float elapsedTime = 0;

    public float timeFromPreviousMove = 0;
    public int timer = 0;

    private final IntSet gameControls = new IntSet();

    public GameManager()
    {
        gameBoard = new Board();
        gnomeSpawner = new GnomeSpawner();
        player = new Player();
        gameBoard.spawnEntity(player, gameBoard.middle(), gameBoard.middle());

        LevelManager.resetLevel();

        gameControls.addAll(
            Input.Keys.W,
            Input.Keys.A,
            Input.Keys.S,
            Input.Keys.D,
            Input.Keys.E
        );
    }

    public void framePass(float delta)
    {
        elapsedTime += delta;
        timeFromPreviousMove += delta;

        if(elapsedTime >= pace)
        {
            if(!keyHandled)
            {
                timer++;
                this.handlePlayerInput(-1);
                timeFromPreviousMove = 0;
            }
            keyHandled = false;
            elapsedTime = 0;
        }
    }

    public void handleKeyPress(int keycode)
    {
        if(gameControls.contains(keycode) && !keyHandled && elapsedTime>=pace*(1-clickWindow))
        {
            timer++;
            this.handlePlayerInput(keycode);
            timeFromPreviousMove = 0;
            keyHandled = true;
        }
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
