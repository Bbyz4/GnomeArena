package com.gdx.gnomearena.Model;

import java.util.ArrayList;

public class GameLogs
{

    //COLLECTS BASIC INFO ABOUT WHAT HAPPENED IN THE GAME, THIS WILL BE USED BY VIEW'S
    private static ArrayList<Item> usedItems = new ArrayList<>();
    private static ArrayList<Weapon> damagingWeapons = new ArrayList<>();
    private static ArrayList<Entity> attackingEntities = new ArrayList<>();
    private static ArrayList<Entity> damagedEntites = new ArrayList<>();

    public static void addItemUsage(Item it)
    {
        usedItems.add(it);
    }

    public static void addWeaponDamage(Weapon w)
    {
        damagingWeapons.add(w);
    }

    public static void addEntityAttack(Entity e)
    {
        attackingEntities.add(e);
    }

    public static void addDamagedEntity(Entity e)
    {
        damagedEntites.add(e);
    }

    public static ArrayList<Item> getUsedItems()
    {
        return usedItems;
    }

    public static ArrayList<Weapon> getDamagingWeapons()
    {
        return damagingWeapons;
    }

    public static ArrayList<Entity> getAttackingEntities()
    {
        return attackingEntities;
    }

    public static ArrayList<Entity> getDamagedEntites()
    {
        return damagedEntites;
    }

    public static void clearLogs()
    {
        usedItems.clear();
        damagingWeapons.clear();
        attackingEntities.clear();
        damagedEntites.clear();
    }
}
