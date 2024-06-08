package com.gdx.gnomearena.View;

import com.gdx.gnomearena.ViewModel.MainViewModel;

public abstract class ActiveGameView
{
    private MainViewModel registeredTo;

    private void registerInViewModel(MainViewModel MVM)
    {
        MVM.activeGameViews.add(this);
        registeredTo = MVM;
    }

    private void sendUserAction(int keyPressed)
    {
        registeredTo.processActiveViewRequest(keyPressed);
    }
}
