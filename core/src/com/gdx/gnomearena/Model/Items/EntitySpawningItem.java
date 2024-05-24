package com.gdx.gnomearena.Model.Items;

import com.gdx.gnomearena.Model.Board;
import com.gdx.gnomearena.Model.Direction;
import com.gdx.gnomearena.Model.Entity;
import com.gdx.gnomearena.Model.Item;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.Player;

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
