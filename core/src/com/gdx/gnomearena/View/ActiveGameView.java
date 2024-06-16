package com.gdx.gnomearena.View;

import com.gdx.gnomearena.ViewModel.GameViewModel;

public abstract class ActiveGameView
{
    public GameViewModel registeredTo;

    public abstract void registerInViewModel(GameViewModel viewModel);

    public abstract void sendUserAction(int keyPressed);
}
