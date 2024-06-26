package com.gdx.gnomearena.Model;


import java.util.ArrayList;
import java.util.List;

import com.gdx.gnomearena.Config.Model.ModelConfig;


public class Board
{
    private final Entity[][] board; // (0,0) is the top-left square
    private final int boardSize;
    private int playerX;
    private int playerY;

    private final Entity[][] previousBoard;
    private final Item[][] itemBoard;


    Board(int x)
    {
        board = new Entity[x][x];
        previousBoard = new Entity[x][x];
        itemBoard = new Item[x][x];
        boardSize=x;
    }

    Board()
    {
        this(ModelConfig.DEFAULT_BOARD_SIZE);
    }

    public boolean isValid(int x, int y)
    {
        return (x>=0 && x<boardSize && y>=0 && y<boardSize);
    }

    public boolean isEmpty(int x, int y)
    {
        if(!isValid(x, y))
        {
            return false;
        }

        if(board[x][y]==null)
        {
            return true;
        }

        if(board[x][y] instanceof NonBlockingEntity)
        {
            return true;
        }

        return false;
    }

    public boolean isPreviousEmpty(int x, int y)
    {
        return (previousBoard[x][y]==null);
    }

    public Entity get(int x, int y)
    {
        return board[x][y];
    }

    public Item getItem(int x, int y)
    {
        return itemBoard[x][y];
    }

    public Entity getPrevious(int x, int y)
    {
        return previousBoard[x][y];
    }

    public int middle()
    {
        return (boardSize/2);
    }

    public int size()
    {
        return boardSize;
    }

    public Pair<Integer, Integer> getPlayersPosition()
    {
        return new Pair<>(playerX, playerY);
    }

    public Pair<Integer, Integer> getEntitiesPosition(Entity e)
    {
        for(int i=0; i<boardSize; i++)
        {
            for(int j=0; j<boardSize; j++)
            {
                if(board[i][j]!=null && board[i][j].equals(e))
                {
                    return new Pair<>(i, j);
                }
            }
        }

        return null;
    }

    public Pair<Integer,Integer> getItemsPosition(Item item)
    {
        for(int i=0; i<boardSize; i++)
        {
            for(int j=0; j<boardSize; j++)
            {
                if(itemBoard[i][j]!=null && itemBoard[i][j].equals(item))
                {
                    return new Pair<>(i, j);
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

        if(e instanceof Gnome)
        {
            ((Gnome)e).onSpawn(this);
        }
    }

    public void moveEntity(int oldX, int oldY, int newX, int newY)
    {
        if(board[newX][newY]!=null && board[newX][newY] instanceof NonBlockingEntity)
        {
            NonBlockingEntity nbe = (NonBlockingEntity) board[newX][newY];
            nbe.onReplace(this, get(oldX, oldY));
        }

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

    public void spawnItem(Item it, int x, int y)
    {
        itemBoard[x][y]=it;
    }

    public void removeItem(int x, int y)
    {
        itemBoard[x][y] = null;
    }

    public void removeEntity(Entity e)
    {
        Pair<Integer,Integer> p = getEntitiesPosition(e);
        if(p!=null)
        {
            removeEntity(p.getKey(), p.getValue());
        }
    }

    public void copyBoard()
    {
        for(int i=0; i<boardSize; i++)
        {
            System.arraycopy(board[i], 0, previousBoard[i], 0, boardSize);
        }
    }

    public List<Item> getAllItems()
    {
        List<Item> l = new ArrayList<>();
        for(int i=0; i<boardSize; i++)
        {
            for(int j=0; j<boardSize; j++)
            {
                if(itemBoard[i][j]!=null)
                {
                    l.add(itemBoard[i][j]);
                }
            }
        }
        return l;
    } 

}
