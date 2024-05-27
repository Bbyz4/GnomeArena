package com.gdx.gnomearena.ViewModel;

import java.util.ArrayList;
import java.util.List;

import com.gdx.gnomearena.Model.*;

public class BoardViewInfo
{
    public static List<Pair<Entity, Pair<Integer,Integer>>> getAllEntitiesWithPositions(Board board)
    {
        List<Pair<Entity, Pair<Integer,Integer>>> l = new ArrayList<>();
        for(int i=0; i<board.size(); i++)
        {
            for(int j=0; j<board.size(); j++)
            {
                if(board.get(i,j)!=null)
                {
                    l.add(new Pair<>(board.get(i,j),new Pair<>(i,j)));
                }
            }
        }
        return l;
    }

    private static Pair<Integer,Integer> searchForEntityInPreviousBoard(Board board, Entity e, int x, int y)
    {
        int layer = 0;
        while(layer<=board.size())
        {
            for(int i=(-1)*layer; i<=layer; i++)
            {
                if(board.isValid(x+i, y+layer) && !board.isPreviousEmpty(x+i, y+layer) && e.equals(board.getPrevious(x+i, y+layer)))
                {
                    return new Pair<>(x+i, y+layer);
                }

                if(board.isValid(x+i, y-layer) && !board.isPreviousEmpty(x+i, y-layer) && e.equals(board.getPrevious(x+i, y-layer)))
                {
                    return new Pair<>(x+i, y-layer);
                }

                if(board.isValid(x+layer, y+i) && !board.isPreviousEmpty(x+layer, y+i) && e.equals(board.getPrevious(x+layer, y+i)))
                {
                    return new Pair<>(x+layer, y+i);
                }

                if(board.isValid(x-layer, y+i) && !board.isPreviousEmpty(x-layer, y+i) && e.equals(board.getPrevious(x-layer, y+i)))
                {
                    return new Pair<>(x-layer, y+i);
                }
            }
            layer++;
        }
        return null;
    }

    public static List<Pair<Entity, Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>> getAllEntitiesWithMoves(Board board)
    {
        List<Pair<Entity, Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>> l = new ArrayList<>();
        for(int i=0; i<board.size(); i++)
        {
            for(int j=0; j<board.size(); j++)
            {
                if(board.get(i,j)!=null)
                {
                    Entity ent = board.get(i,j);
                    Pair<Integer,Integer> prevPos = searchForEntityInPreviousBoard(board, ent, i, j);
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

    public static List<Pair<Item, Pair<Integer,Integer>>> getAllItemsWithPositions(Board board)
    {
        List<Pair<Item, Pair<Integer,Integer>>> l = new ArrayList<>();
        for(int i=0; i<board.size(); i++)
        {
            for(int j=0; j<board.size(); j++)
            {
                if(board.getItem(i,j)!=null)
                {
                    l.add(new Pair<>(board.getItem(i,j),new Pair<>(i,j)));
                }
            }
        }
        return l;
    }
}
