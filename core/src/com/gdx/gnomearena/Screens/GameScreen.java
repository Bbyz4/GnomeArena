package com.gdx.gnomearena.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gdx.gnomearena.MainGame;

public class GameScreen implements Screen {

    final MainGame game;

    OrthographicCamera camera;

    public GameScreen(MainGame game)
    {
        this.game = game;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        camera.update();

        Gdx.gl.glClearColor(1,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.stage.act(delta);
        game.stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        
    }

    @Override
    public void pause()
    {
       
    }

    @Override
    public void resume()
    {
        
    }

    @Override
    public void hide()
    {
        
    }

    @Override
    public void dispose()
    {
        
    }

}
