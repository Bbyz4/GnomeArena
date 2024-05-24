package com.gdx.gnomearena.Core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GnomeMover
{
    public static List<TurnEntity> getGnomeMoveOrders(Board board)
    {
        List<TurnEntity> gnomes = new ArrayList<>();
        List<TurnEntity> others = new ArrayList<>();
        List<Pair<Integer,Integer>> positions = new ArrayList<>();

        for (int i = 0; i < board.size(); i++)
        {
            for (int j = 0; j < board.size(); j++)
            {
                if (board.get(i,j) instanceof TurnEntity)
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
                Pair<Integer,Integer> playerPos = board.getPlayersPosition();
                int dist1 = Math.abs(playerPos.getKey()-p1.getKey()) + Math.abs(playerPos.getValue()-p1.getValue());
                int dist2 = Math.abs(playerPos.getKey()-p2.getKey()) + Math.abs(playerPos.getValue()-p2.getValue());
                return Integer.compare(dist1, dist2);
            }
        });
        
        for(int i=0; i<positions.size(); i++)
        {
            if(board.get(positions.get(i).getKey(), positions.get(i).getValue()) instanceof Gnome)
            {
                gnomes.add((TurnEntity)board.get(positions.get(i).getKey(), positions.get(i).getValue()));
            }
            else
            {
                others.add((TurnEntity)board.get(positions.get(i).getKey(), positions.get(i).getValue()));
            }
        }
        gnomes.addAll(others);
        return gnomes;
    }
}
