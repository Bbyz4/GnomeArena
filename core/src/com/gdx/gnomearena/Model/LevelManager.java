package com.gdx.gnomearena.Model;

import java.util.ArrayList;
import java.util.List;
public class LevelManager
{
    private static int currentLevel;
    private static int currentScore;
    private static List<LevelInfo> levels;

    static
    {
        currentLevel = 0;

        levels = new ArrayList<>();
        
        levels.add(new LevelInfo(0, 1, 10));
        levels.add(new LevelInfo(3, 3, 10));
        levels.add(new LevelInfo(10, 5, 10));
        levels.add(new LevelInfo(25, 7, 10));
        levels.add(new LevelInfo(50, 10, 12));
        levels.add(new LevelInfo(100, 15, 14));
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
        while(levels.size() > currentLevel+1 && levels.get(currentLevel+1).requiredXP <= currentScore)
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
        return levels.get(currentLevel).totalGnomeCost;
    }

    public static int getCurrentLevelCooldown()
    {
        return levels.get(currentLevel).spawnCooldown;
    }
}
