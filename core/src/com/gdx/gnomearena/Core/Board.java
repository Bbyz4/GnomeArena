package com.gdx.gnomearena.Core;

public class Board
{
    private Entity[][] board;
    private int boardSize;

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

    public void spawnEntity(Entity e, int x, int y)
    {
        board[x][y] = e;
    }

    public void moveEntity(int oldX, int oldY, int newX, int newY)
    {
        board[newX][newY] = board[oldX][oldY];
        board[oldX][oldY] = null;
    }

    public void removeEntity(int x, int y)
    {
        board[x][y] = null;
    }
}
