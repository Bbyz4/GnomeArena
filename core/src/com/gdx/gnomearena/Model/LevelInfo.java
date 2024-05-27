package com.gdx.gnomearena.Model;

import java.util.function.Supplier;

public class LevelInfo
{
    public int requiredXP;
    public int totalGnomeCost;
    public Supplier<Gnome>[] spawnedGnomes; //For future updates: each level will have it's own set of gnomes
    public int spawnCooldown;

    public LevelInfo(int requiredXP, int totalGnomeCost, int spawnCooldown)
    {
        this.requiredXP = requiredXP;
        this.totalGnomeCost = totalGnomeCost;
        this.spawnCooldown = spawnCooldown;
    }
}
