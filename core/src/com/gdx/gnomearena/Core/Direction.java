package com.gdx.gnomearena.Core;

import com.badlogic.gdx.Input;

import java.util.HashMap;
import java.util.Map;

public enum Direction
{
    UP(Input.Keys.W),
    DOWN(Input.Keys.S),
    LEFT(Input.Keys.A),
    RIGHT(Input.Keys.D);

    private int value;
    private static final Map<Integer, Direction> directionMap = new HashMap<>();
    private static final Map<Direction, Pair<Integer,Integer>> nextFieldMap = new HashMap<>();

    static
    {
        for (Direction dir : Direction.values())
        {
            directionMap.put(dir.value, dir);
        }
        nextFieldMap.put(Direction.UP, new Pair<>(0, -1));
        nextFieldMap.put(Direction.DOWN, new Pair<>(0, 1));
        nextFieldMap.put(Direction.LEFT, new Pair<>(-1, 0));
        nextFieldMap.put(Direction.RIGHT, new Pair<>(1, 0));
    }
    Direction(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public static Direction getDirection(Integer keyCode)
    {
        return directionMap.get(keyCode);
    }

    //gives a field's position that is in front of another field including a direction
    public static Pair<Integer,Integer> getFieldsFrontField(Pair<Integer, Integer> field, Direction dir)
    {
        return new Pair<>(field.getKey() + nextFieldMap.get(dir).getKey(), field.getValue() + nextFieldMap.get(dir).getValue());
    }
}
