package com.gdx.gnomearena.Model;

public abstract class GnomeMovePattern
{
    public Pair<Integer,Integer>[] moveRange;

    public boolean move(Board board, Gnome mover)
    {
        Pair<Integer,Integer> myPosition = board.getEntitiesPosition(mover);
        Pair<Integer,Integer> playerPos = board.getPlayersPosition();

        for(Pair<Integer,Integer> ranges : moveRange)
        {
            if(board.isValid(myPosition.getKey() + ranges.getKey(), myPosition.getValue() + ranges.getValue()) && board.isEmpty(myPosition.getKey() + ranges.getKey(), myPosition.getValue() + ranges.getValue()) && Math.abs(playerPos.getKey()-myPosition.getKey()) + Math.abs(playerPos.getValue()-myPosition.getValue()) > Math.abs(playerPos.getKey()-myPosition.getKey()-ranges.getKey()) + Math.abs(playerPos.getValue()-myPosition.getValue()-ranges.getValue()))
            {
                board.moveEntity(myPosition.getKey(),myPosition.getValue(), myPosition.getKey() + ranges.getKey(), myPosition.getValue() + ranges.getValue());
                return true;
            }
        }
        return false;
    }
}
