package com.gdx.gnomearena.View;

import com.gdx.gnomearena.ViewModel.GameViewModel;

public abstract class ActiveGameView
{
    private GameViewModel registeredTo;

    private void registerInViewModel(GameViewModel viewModel)
    {
        registeredTo = viewModel;
        viewModel.registerActiveListener(this);
    }

    private void sendUserAction(int keyPressed)
    {
        registeredTo.passInputToModel(keyPressed);
    }
}
