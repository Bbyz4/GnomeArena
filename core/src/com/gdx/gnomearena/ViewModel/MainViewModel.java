package com.gdx.gnomearena.ViewModel;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gdx.gnomearena.MainGame;
import com.gdx.gnomearena.Model.*;
import com.gdx.gnomearena.View.*;


public class MainViewModel implements Screen
{

    final MainGame game;
    final GameManager gameManager;
    public Hud hud;
    
    public List<ActiveGameView> activeGameViews;
    public List<PassiveGameView> passiveGameViews;

    OrthographicCamera camera;

    public MainViewModel(MainGame game)
    {
        this.game = game;
        this.gameManager = new GameManager();
        activeGameViews = new ArrayList<>();
        passiveGameViews = new ArrayList<>();
        camera = new OrthographicCamera();
        hud = new Hud();

        //TESTING
        //camera.setToOrtho(false, 800, 600);
    }

    //WILL BE USED FOR PLAYER INPUT LATER
    public void processActiveViewRequest(int keyPressed)
    {

    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(new InputAdapter()
        {
            @Override
            public boolean keyDown(int keycode)
            {
                gameManager.handleKeyPress(keycode);
                return true;
            }
        });

        for(PassiveGameView PGV : passiveGameViews)
        {
            PGV.initialize();
        }
    }

    @Override
    public void render(float delta)
    {
        gameManager.framePass(delta);



        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //TEMPORARY CHANGE
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);


        if(gameManager.isOver()) {
            game.setScreen(new StatsScreen(game,gameManager.getScore(), gameManager.timer));
        }


        for(PassiveGameView PGV : passiveGameViews)
        {
            PGV.displayGame(gameManager, delta);
        }

        GameLogs.clearLogs();
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
        for(PassiveGameView PGV : passiveGameViews)
        {
            PGV.finalize();
        }
    }

    @Override
    public void dispose()
    {
        
    }

}
