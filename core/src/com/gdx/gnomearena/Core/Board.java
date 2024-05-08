package com.gdx.gnomearena.Core;

import java.util.List;

public class Board
{
    private Entity[][] board; // (0,0) is the top-left square
    private int boardSize;
    
    private int playerX;
    private int playerY;

    Board(int x)
    {
        board = new Entity[x][x];
        boardSize=x;
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

    public int getPlayerX()
    {
        return playerX;
    }

    public int getPlayerY()
    {
        return playerY;
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

    public List<Gnome> getGnomeMoveOrders()
    {
        return null;
    }
}
