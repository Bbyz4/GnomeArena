package com.gdx.gnomearena.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import com.gdx.gnomearena.Model.Gnomes.BasicGnome;
import com.gdx.gnomearena.Model.Gnomes.ScoutGnome;
import com.gdx.gnomearena.Model.Gnomes.WizardGnome;

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

    public void spawnNewWave(Board board, List<Gnome> wave)
    {
        if(wave==null){return;}
        board.spawnEntity(new WizardGnome(), 13, 13);
        board.spawnEntity(new ScoutGnome(), 12, 12);
        //Tries to spawn every gnome on the egde of the map
        int currentDist = 0;
        int currentSpawned = 0;
        while(currentSpawned<wave.size() && currentDist<=board.size()/2)
        {
            for(int i=currentDist; i<board.size()-currentDist; i++)
            {
                if(board.isEmpty(i, currentDist))
                {
                    board.spawnEntity(wave.get(currentSpawned), i, currentDist);
                    currentSpawned++;
                    if(currentSpawned>=wave.size())
                    {
                        return;
                    }
                }

                if(board.isEmpty(i, board.size()-1-currentDist))
                {
                    board.spawnEntity(wave.get(currentSpawned), i, board.size()-1-currentDist);
                    currentSpawned++;
                    if(currentSpawned>=wave.size())
                    {
                        return;
                    }
                }

                if(board.isEmpty(currentDist, i))
                {
                    board.spawnEntity(wave.get(currentSpawned), currentDist, i);
                    currentSpawned++;
                    if(currentSpawned>=wave.size())
                    {
                        return;
                    }
                }

                if(board.isEmpty(board.size()-1-currentDist, i))
                {
                    board.spawnEntity(wave.get(currentSpawned), board.size()-1-currentDist, i);
                    currentSpawned++;
                    if(currentSpawned>=wave.size())
                    {
                        return;
                    }
                }
            }

            currentDist++;
        }
    }
}
