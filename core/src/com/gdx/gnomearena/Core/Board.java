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
    private int score;

    private Entity[][] previousBoard;
    private Item[][] itemBoard;

    Board(int x)
    {
        board = new Entity[x][x];
        previousBoard = new Entity[x][x];
        itemBoard = new Item[x][x];
        boardSize=x;
        score = 0;
    }

    Board()
    {
        this(15);
    }

    public int getScore()
    {
        return score;
    }

    public void updateScore(int xp)
    {
        score += xp;
    }

    public boolean isValid(int x, int y)
    {
        return (x>=0 && x<boardSize && y>=0 && y<boardSize);
    }

    public boolean isEmpty(int x, int y)
    {
        return (board[x][y]==null);
    }

    private boolean isPreviousEmpty(int x, int y)
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

    private Entity getPrevious(int x, int y)
    {
        return previousBoard[x][y];
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

    public Pair<Integer,Integer> getItemsPosition(Item item)
    {
        for(int i=0; i<boardSize; i++)
        {
            for(int j=0; j<boardSize; j++)
            {
                if(itemBoard[i][j]!=null && itemBoard[i][j].equals(item))
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

        if(e instanceof Gnome)
        {
            ((Gnome)e).onSpawn(this);
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
            for(int j=0; j<boardSize; j++)
            {
                previousBoard[i][j] = board[i][j];
            }
        }
    }

    public List<TurnEntity> getGnomeMoveOrders()
    {
        List<TurnEntity> gnomes = new ArrayList<>();
        List<TurnEntity> others = new ArrayList<>();
        List<Pair<Integer,Integer>> positions = new ArrayList<>();

        for (int i = 0; i < boardSize; i++)
        {
            for (int j = 0; j < boardSize; j++)
            {
                if (board[i][j] instanceof TurnEntity)
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
            if(get(positions.get(i).getKey(), positions.get(i).getValue()) instanceof Gnome)
            {
                gnomes.add((TurnEntity)get(positions.get(i).getKey(), positions.get(i).getValue()));
            }
            else
            {
                others.add((TurnEntity)get(positions.get(i).getKey(), positions.get(i).getValue()));
            }
        }
        gnomes.addAll(others);
        return gnomes;
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
                    l.add(new Pair<>(board[i][j],new Pair<>(i,j)));
                }
            }
        }
        return l;
    }

    // searches for the previous position of an entity, starting from the position it is currently at
    private Pair<Integer,Integer> searchForEntityInPreviousBoard(Entity e, int x, int y)
    {
        int layer = 0;
        while(layer<=boardSize)
        {
            for(int i=(-1)*layer; i<=layer; i++)
            {
                if(isValid(x+i, y+layer) && !isPreviousEmpty(x+i, y+layer) && e.equals(getPrevious(x+i, y+layer)))
                {
                    return new Pair<>(x+i, y+layer);
                }

                if(isValid(x+i, y-layer) && !isPreviousEmpty(x+i, y-layer) && e.equals(getPrevious(x+i, y-layer)))
                {
                    return new Pair<>(x+i, y-layer);
                }

                if(isValid(x+layer, y+i) && !isPreviousEmpty(x+layer, y+i) && e.equals(getPrevious(x+layer, y+i)))
                {
                    return new Pair<>(x+layer, y+i);
                }

                if(isValid(x-layer, y+i) && !isPreviousEmpty(x-layer, y+i) && e.equals(getPrevious(x-layer, y+i)))
                {
                    return new Pair<>(x-layer, y+i);
                }
            }
            layer++;
        }
        return null;
    }

    public List<Pair<Entity, Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>> getAllEntitiesWithMoves()
    {
        List<Pair<Entity, Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>> l = new ArrayList<>();
        for(int i=0; i<boardSize; i++)
        {
            for(int j=0; j<boardSize; j++)
            {
                if(board[i][j]!=null)
                {
                    Entity ent = get(i,j);
                    Pair<Integer,Integer> prevPos = searchForEntityInPreviousBoard(ent, i, j);
                    if(prevPos==null)
                    {
                        prevPos = new Pair<>(i, j);
                    }
                    l.add(new Pair<>(ent, new Pair<>(new Pair<>(i,j),prevPos)));
                }
            }
        }
        return l;
    }

    public List<Pair<Item, Pair<Integer,Integer>>> getAllItemsWithPositions()
    {
        List<Pair<Item, Pair<Integer,Integer>>> l = new ArrayList<>();
        for(int i=0; i<boardSize; i++)
        {
            for(int j=0; j<boardSize; j++)
            {
                if(itemBoard[i][j]!=null)
                {
                    l.add(new Pair<>(itemBoard[i][j],new Pair<>(i,j)));
                }
            }
        }
        return l;
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
