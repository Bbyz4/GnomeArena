package com.gdx.gnomearena;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.gnomearena.Screens.MainMenuScreen;


public class MainGame extends Game
{
	public Stage stage;
	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(3);
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
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
		font.dispose();
		stage.dispose();
	}
	
}
