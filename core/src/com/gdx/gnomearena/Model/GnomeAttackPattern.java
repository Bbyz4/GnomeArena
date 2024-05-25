package com.gdx.gnomearena.Model;


public abstract class GnomeAttackPattern
{
    public Pair<Integer,Integer>[] attackRange;
    public int damagePoints;

    public boolean attack(Board board, Gnome attacker)
    {
        Pair<Integer, Integer> attackerPosition = board.getEntitiesPosition(attacker);
        Pair<Integer,Integer> playerPos = board.getPlayersPosition();


        for (Pair<Integer,Integer> ranges: attackRange) {
            if (attackerPosition.getKey() + ranges.getKey()  == playerPos.getKey() && attackerPosition.getValue() + ranges.getValue() == playerPos.getValue()) {
                board.get(playerPos.getKey(), playerPos.getValue()).takeDamage(board, damagePoints);
                return true;
            }

        }

        return false;
    }
}