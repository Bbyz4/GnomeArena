package com.gdx.gnomearena;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.gnomearena.View.MainMenuScreen;
import com.gdx.gnomearena.ViewModel.GameViewModel;


public class MainGame extends Game
{
	public SpriteBatch batch;
	public GameViewModel viewModel;

	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render ()
	{
		super.render();
	}
	
	@Override
	public void dispose ()
	{
		batch.dispose();
	}
	
}
