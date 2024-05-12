package com.gdx.gnomearena.Core;

import java.util.ArrayList;
import java.util.Comparator;
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
        List<Gnome> l = new ArrayList<>();
        List<Pair<Integer,Integer>> positions = new ArrayList<>();

        for (int i = 0; i < boardSize; i++)
        {
            for (int j = 0; j < boardSize; j++)
            {
                if (board[i][j] instanceof Gnome)
                {
                    positions.add(new Pair<Integer,Integer>(i,j));
                }
            }
        }
        
        positions.sort(new Comparator<Pair<Integer,Integer>>()
        {
            @Override
            public int compare(Pair<Integer,Integer> p1, Pair<Integer,Integer> p2)
            {
                int dist1 = Math.abs(playerX-p1.getKey()) + Math.abs(playerY-p1.getValue());
                int dist2 = Math.abs(playerX-p2.getKey()) + Math.abs(playerY-p2.getValue());
                return Integer.compare(dist1, dist2);
            }
        });
        
        for(int i=0; i<positions.size(); i++)
        {
            l.add((Gnome)get(positions.get(i).getKey(), positions.get(i).getValue()));
        }
        return l;
    }

    public List<Pair<Entity, Pair<Integer,Integer>>> getAllEntitiesWithPositions()
    {
        List<Pair<Entity, Pair<Integer,Integer>>> l = new ArrayList<>();
        for(int i=0; i<boardSize; i++)
        {
            for(int j=0; j<boardSize; j++)
            {
                if(board[i][j]!=null)
                {
                    l.add(new Pair<Entity, Pair<Integer,Integer>>(board[i][j],new Pair<Integer,Integer>(i,j)));
                }
            }
        }
        return l;
    }

    public void spawnNewWave(List<Gnome> wave)
    {
        if(wave==null){return;}
        //Tries to spawn every gnome on the egde of the map
        int currentDist = 0;
        int currentSpawned = 0;
        while(currentSpawned<wave.size() && currentDist<=boardSize/2)
        {
            for(int i=currentDist; i<boardSize-currentDist; i++)
            {
                if(isEmpty(i, currentDist))
                {
                    spawnEntity(wave.get(currentSpawned), i, currentDist);
                    currentSpawned++;
                    if(currentSpawned>=wave.size())
                    {
                        return;
                    }
                }

                if(isEmpty(i, boardSize-1-currentDist))
                {
                    spawnEntity(wave.get(currentSpawned), i, boardSize-1-currentDist);
                    currentSpawned++;
                    if(currentSpawned>=wave.size())
                    {
                        return;
                    }
                }

                if(isEmpty(currentDist, i))
                {
                    spawnEntity(wave.get(currentSpawned), currentDist, i);
                    currentSpawned++;
                    if(currentSpawned>=wave.size())
                    {
                        return;
                    }
                }

                if(isEmpty(boardSize-1-currentDist, i))
                {
                    spawnEntity(wave.get(currentSpawned), boardSize-1-currentDist, i);
                    currentSpawned++;
                    if(currentSpawned>=wave.size())
                    {
                        return;
                    }
                }
            }

            currentDist++;
        }
    }
}
