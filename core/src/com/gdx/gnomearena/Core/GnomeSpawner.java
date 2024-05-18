package com.gdx.gnomearena.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import com.gdx.gnomearena.Core.Gnomes.BasicGnome;

public class GnomeSpawner
{
    Supplier<Gnome>[] spawnedGnomes;
    int cooldown;
    int currentCooldown;
    Random rand;

    @SuppressWarnings("unchecked")
    public GnomeSpawner()
    {
        spawnedGnomes = new Supplier[]
        {
            BasicGnome::new
        };
        cooldown = 10;
        currentCooldown = 10;
        rand = new Random();
    }

    List<Gnome> newGnomesList(int x)
    {
        currentCooldown--;
        if(currentCooldown==0)
        {
            currentCooldown = cooldown;
            List<Gnome> l = new ArrayList<>();
            for(int i=0; i<x; i++)
            {
                Supplier<Gnome> gc = spawnedGnomes[rand.nextInt(spawnedGnomes.length)];
                Gnome gnome = gc.get();
                l.add(gnome);
            }
            
            return l;
        }
        return null;
    }
}
