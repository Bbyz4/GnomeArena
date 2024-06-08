package com.gdx.gnomearena.View;

import com.gdx.gnomearena.Model.GameManager;
import com.gdx.gnomearena.ViewModel.MainViewModel;

public abstract class PassiveGameView
{
    public MainViewModel registeredTo;

    public void registerInViewModel(MainViewModel MVM)
    {
        MVM.passiveGameViews.add(this);
        registeredTo = MVM;
    }

    public void initialize()
    {
        
    }

    public void displayGame(GameManager gameManager, float delta)
    {
        return;
    }

    public void finalize()
    {

    }
}
