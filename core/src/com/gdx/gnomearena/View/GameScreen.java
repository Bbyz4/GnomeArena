package com.gdx.gnomearena.View;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gdx.gnomearena.MainGame;
import com.gdx.gnomearena.Model.*;
import com.gdx.gnomearena.View.GraphicalViewComponents.Hud;
import com.gdx.gnomearena.ViewModel.GameViewModel;


public class GameScreen extends ActiveGameView implements Screen
{

    final MainGame game;
    final GameManager gameManager;


    public OrthographicCamera camera;
    public GameViewModel viewModel;
    public GameScreen(MainGame game, GameManager gameManager, GameViewModel viewModel)
    {
        this.game = game;
        this.gameManager = gameManager;
        camera = new OrthographicCamera();
        this.viewModel = viewModel;
        viewModel.registerActiveListener(this);



        //TESTING
        //camera.setToOrtho(false, 800, 600);
    }

    /* //WILL BE USED FOR PLAYER INPUT LATER
    public void processActiveViewRequest(int keyPressed)
    {

    }

     */

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(new InputAdapter()
        {
            @Override
            public boolean keyDown(int keycode)
            {
                viewModel.passInputToModel(keycode);

                return true;
            }
        });

        viewModel.initializeViews();
    }

    @Override
    public void render(float delta)
    {
        viewModel.inform(gameManager, delta);

        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //TEMPORARY CHANGE
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
            viewModel.updateAllViews(gameManager,delta);

        if(gameManager.isOver()) {
            viewModel.endGame();
            game.setScreen(new StatsScreen(game,gameManager.getScore(), gameManager.timer));
        }



        GameLogs.clearLogs();
    }


    public void update()
    {

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
