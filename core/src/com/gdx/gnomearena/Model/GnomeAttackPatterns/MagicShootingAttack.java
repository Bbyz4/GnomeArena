package com.gdx.gnomearena.Model.GnomeAttackPatterns;

import com.gdx.gnomearena.Model.Board;
import com.gdx.gnomearena.Model.Direction;
import com.gdx.gnomearena.Model.Gnome;
import com.gdx.gnomearena.Model.GnomeAttackPattern;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.TurnEntities.MagicBullet;

public class MagicShootingAttack extends GnomeAttackPattern
{
    private int maxRange;
    private int meleeDamage;

    public MagicShootingAttack()
    {
        this(5);
    }

    @SuppressWarnings("unchecked")
    public MagicShootingAttack(int range)
    {
        damagePoints = 2;
        meleeDamage = 1;
        maxRange = range;

        attackRange = new Pair[]
        {
                new Pair<>(1, 0),
                new Pair<>(-1,0),
                new Pair<>(0,1),
                new Pair<>(0,-1)
        };
    }

    @Override
    public boolean attack(Board board, Gnome attacker)
    {
        Pair<Integer, Integer> attackerPosition = board.getEntitiesPosition(attacker);
        Pair<Integer,Integer> playerPos = board.getPlayersPosition();
        for (Pair<Integer,Integer> ranges: attackRange)
        {
            for(int i=1; i<=maxRange; i++)
            {
                if(attackerPosition.getKey() + ranges.getKey()*i  == playerPos.getKey() && attackerPosition.getValue() + ranges.getValue()*i == playerPos.getValue())
                {
                    if(i==1)
                    {
                        board.get(playerPos.getKey(), playerPos.getValue()).takeDamage(board, meleeDamage);
                    }
                    else
                    {
                        Direction bulletDir = Direction.getDirectionFromPair(ranges);
                        Pair<Integer,Integer> bulletPos = Direction.getFieldsFrontField(attackerPosition, bulletDir);
                        board.spawnEntity(new MagicBullet(bulletDir, damagePoints), bulletPos.getKey(), bulletPos.getValue());
                    }

                    return true;
                }

                if(!board.isEmpty(attackerPosition.getKey() + ranges.getKey()*i, attackerPosition.getValue() + ranges.getValue()*i))
                {
                    break;   
                }
            }
        }
        return false;
    }
}
