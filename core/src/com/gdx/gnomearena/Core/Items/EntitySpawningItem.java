package com.gdx.gnomearena.Core.Items;

import com.gdx.gnomearena.Core.Board;
import com.gdx.gnomearena.Core.Direction;
import com.gdx.gnomearena.Core.Entity;
import com.gdx.gnomearena.Core.Item;
import com.gdx.gnomearena.Core.Pair;
import com.gdx.gnomearena.Core.Player;

public class EntitySpawningItem extends Item
{
    Entity spawnedEntity;

    public EntitySpawningItem(Entity e)
    {
        spawnedEntity = e;
        skin = e.skin;
    }

    //attempts to spawn a TurnEntity on a tile, that is in front of the player
    @Override
    public boolean affect(Player user, Board board)
    {
        Pair<Integer,Integer> newEntityPosition = Direction.getFieldsFrontField(board.getEntitiesPosition(user), user.getPlayerDirection());

        if(board.isValid(newEntityPosition.getKey(), newEntityPosition.getValue()) && board.isEmpty(newEntityPosition.getKey(), newEntityPosition.getValue()))
        {
            board.spawnEntity(spawnedEntity, newEntityPosition.getKey(), newEntityPosition.getValue());
            return true;
        }
        else
        {
            return false;
        }
    }
}
