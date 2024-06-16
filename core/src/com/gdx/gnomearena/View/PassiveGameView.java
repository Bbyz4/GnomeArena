package com.gdx.gnomearena.View;

import com.gdx.gnomearena.Model.GameManager;

public abstract class PassiveGameView
{
    public GameScreen registeredTo;

    

    public abstract void initialize();

    public abstract void displayGame(GameManager gameManager, float delta);

    public abstract void finish();
}
