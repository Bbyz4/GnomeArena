package com.gdx.gnomearena.ViewModel;


public class CurrentViewModel
{
    private static MainViewModel curr;

    public static MainViewModel getCurrentScreen()
    {
        return curr;
    }

    public static void changeCurrentScreen(MainViewModel newCurr)
    {
        curr = newCurr;
    }
}
