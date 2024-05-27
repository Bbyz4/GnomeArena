package com.gdx.gnomearena.Model;

import java.util.ArrayList;
import java.util.List;

public class LevelManager
{
    private static int currentLevel;
    private static int currentScore;
    private static List<Pair<Integer,Integer>> levels;

    static
    {
        currentLevel = 0;

        levels = new ArrayList<>();
        
        levels.add(new Pair<>(0, 1));
        levels.add(new Pair<>(3, 3));
        levels.add(new Pair<>(10, 5));
        levels.add(new Pair<>(25, 7));
        levels.add(new Pair<>(50, 10));
        levels.add(new Pair<>(100, 14));
    }

    public LevelManager()
    {
        
    }

    public static void resetLevel()
    {
        currentLevel = 0;
        currentScore = 0;
    }

    public static void increaseScore(int newScore)
    {
        currentScore += newScore;
        increaseLevel();
    }

    public static int getScore()
    {
        return currentScore;
    }

    private static void increaseLevel()
    {
        while(levels.size() > currentLevel+1 && levels.get(currentLevel+1).getKey() <= currentScore)
        {
            currentLevel++;
        }
    }

    public static int getCurrentLevel()
    {
        return currentLevel;
    }

    public static int getCurrentGnomeCost()
    {
        return levels.get(currentLevel).getValue();
    }
}
