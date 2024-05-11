package com.gdx.gnomearena.Core;

import java.util.ArrayList;
import java.util.List;

import com.gdx.gnomearena.Core.Gnomes.TestingGnome;

import javafx.util.Pair;

public class Board
{
    private Entity[][] board; // (0,0) is the top-left square
    private int boardSize;
    
    private int playerX;
    private int playerY;

    //TESTING
    private TestingGnome test;

    Board(int x)
    {
        board = new Entity[x][x];
        boardSize=x;
        //TESTING
        test = new TestingGnome();
        spawnEntity(test, 0, 0);
    }

    Board()
    {
        this(15);
    }

    public boolean isValid(int x, int y)
    {
        return (x>=0 && x<boardSize && y>=0 && y<boardSize);
    }

    public boolean isEmpty(int x, int y)
    {
        return (board[x][y]==null);
    }

    public Entity get(int x, int y)
    {
        return board[x][y];
    }

    public int middle()
    {
        return (boardSize/2)+1;
    }

    public Pair<Integer, Integer> getPlayersPosition()
    {
        return new Pair<Integer,Integer>(playerX, playerY);
    }

    public Pair<Integer, Integer> getEntitiesPosition(Entity e)
    {
        for(int i=0; i<boardSize; i++)
        {
            for(int j=0; j<boardSize; j++)
            {
                if(board[i][j]!=null && board[i][j].equals(e))
                {
                    return new Pair<Integer,Integer>(i, j);
                }
            }
        }

        return null;
    }

    public void spawnEntity(Entity e, int x, int y)
    {
        board[x][y] = e;
        if(e instanceof Player)
        {
            playerX = x;
            playerY = y;
        }
    }

    public void moveEntity(int oldX, int oldY, int newX, int newY)
    {
        board[newX][newY] = board[oldX][oldY];
        board[oldX][oldY] = null;
        if(board[newX][newY] instanceof Player)
        {
            playerX = newX;
            playerY = newY;
        }
    }

    public void removeEntity(int x, int y)
    {
        board[x][y] = null;
    }

    public void removeEntity(Entity e)
    {
        Pair<Integer,Integer> p = getEntitiesPosition(e);
        if(p!=null)
        {
            removeEntity(p.getKey(), p.getValue());
        }
    }

    public List<Gnome> getGnomeMoveOrders()
    {
        //TESTING
        List<Gnome> l = new ArrayList<>();
        l.add(test);
        return l;
    }
}
