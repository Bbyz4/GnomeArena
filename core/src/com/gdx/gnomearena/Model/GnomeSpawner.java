package com.gdx.gnomearena.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import com.gdx.gnomearena.Model.Gnomes.BasicGnome;
import com.gdx.gnomearena.Model.Gnomes.ScoutGnome;
import com.gdx.gnomearena.Model.Gnomes.WizardGnome;

public class GnomeSpawner
{
    Supplier<Gnome>[] spawnedGnomes;
    private int cooldown;
    private int currentCooldown;
    Random rand;

    private int currentLevel;

    //key = xp required to enter that level. value = total cost of spawned gnomes
    private List<Pair<Integer,Integer>> levels;

    private List<Integer> order;

    @SuppressWarnings("unchecked")
    public GnomeSpawner()
    {
        spawnedGnomes = new Supplier[]
        {
            BasicGnome::new,
            ScoutGnome::new,
            WizardGnome::new

        };
        cooldown = 10;
        currentCooldown = 10;
        rand = new Random();

        currentLevel = 0;
        levels = new ArrayList<>();
        
        levels.add(new Pair<>(0, 1));
        levels.add(new Pair<>(3, 3));
        levels.add(new Pair<>(10, 5));
        levels.add(new Pair<>(25, 7));
        levels.add(new Pair<>(50, 10));
        levels.add(new Pair<>(100, 14));

        order = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            order.add(i);
        }
    }

    List<Gnome> newGnomesList(int playerXP)
    {
        currentCooldown--;
        if(currentCooldown==0)
        {
            currentCooldown = cooldown;
            List<Gnome> l = new ArrayList<>();

            while(levels.size() > currentLevel && levels.get(currentLevel+1).getKey() <= playerXP)
            {
                currentLevel++;
            }

            int totalGnomeCost = levels.get(currentLevel).getValue();

            //this might cause an infinite loop in the future, remember to modify this
            while(totalGnomeCost>0)
            {
                Supplier<Gnome> gc = spawnedGnomes[rand.nextInt(spawnedGnomes.length)];
                Gnome gnome = gc.get();
                if(totalGnomeCost-gnome.cost >= 0)
                {
                    l.add(gnome);
                    totalGnomeCost -= gnome.cost;
                }
            }
            return l;
        }
        return null;
    }

    public void spawnNewWave(Board board, List<Gnome> wave)
    {
        if(wave==null){return;}
        
        Collections.shuffle(order);

        int currentDist = 0;
        int currentSpawned = 0;
        while(currentSpawned<wave.size() && currentDist<=board.size()/2)
        {
            for(int i=currentDist; i<board.size()-currentDist; i++)
            {
                for (int j : order)
                {
                    switch(j)
                    {
                        case 0:
                            if(board.isEmpty(currentDist, currentDist)) 
                            {
                                board.spawnEntity(wave.get(currentSpawned), currentDist, currentDist);
                                currentSpawned++;
                            }
                            break;
                        case 1:
                            if(board.isEmpty(currentDist, board.size() - 1 - currentDist))
                            {
                                board.spawnEntity(wave.get(currentSpawned), currentDist, board.size() - 1 - currentDist);
                                currentSpawned++;
                            }
                            break;
                        case 2:
                            if(board.isEmpty(board.size() - 1 - currentDist, currentDist))
                            {
                                board.spawnEntity(wave.get(currentSpawned), board.size() - 1 - currentDist, currentDist);
                                currentSpawned++;
                            }
                            break;
                        case 3:
                            if(board.isEmpty(board.size() - 1 - currentDist, board.size() - 1 - currentDist))
                            {
                                board.spawnEntity(wave.get(currentSpawned), board.size() - 1 - currentDist, board.size() - 1 - currentDist);
                                currentSpawned++;
                            }
                            break;
                    }
                    
                    if (currentSpawned >= wave.size())
                    {
                        return;
                    }
                }
            }

            currentDist++;
        }
    }
}
