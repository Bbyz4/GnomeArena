package com.gdx.gnomearena.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.gnomearena.MainGame;
import com.gdx.gnomearena.Core.GameManager;

public class GameScreen implements Screen {

    final MainGame game;
    final GameManager gameManager;
    private Stage stage;
    
    private final float pace = 1f;
    private final float clickWindow = 0.25f;
    private boolean keyHandled = false;
    private float elapsedTime = 0;

    private final IntSet gameControls = new IntSet();

    OrthographicCamera camera;

    public GameScreen(MainGame game)
    {
        this.game = game;
        this.gameManager = new GameManager();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // WASD - movement, E - use item
        gameControls.addAll(
            Input.Keys.W,
            Input.Keys.A,
            Input.Keys.S,
            Input.Keys.D,
            Input.Keys.E
        );
    }

    @Override
    public void show()
    {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(new InputAdapter()
        {
            @Override
            public boolean keyDown(int keycode)
            {
                if(gameControls.contains(keycode) && !keyHandled && elapsedTime>=pace*(1-clickWindow))
                {
                    gameManager.handlePlayerInput(keycode);
                    keyHandled = true;
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta)
    {
        elapsedTime += delta;

        if(elapsedTime >= pace)
        {
            if(!keyHandled)
            {
                gameManager.handlePlayerInput(-1);
            }
            keyHandled = false;
            elapsedTime = 0;
        }

        camera.update();

        if(elapsedTime>=pace*(1-clickWindow))
        {
            if(!keyHandled)
            {
                Gdx.gl.glClearColor(1,0,0,1);
            }
            else
            {
                Gdx.gl.glClearColor(0,1,0,1);
            }
        }
        else
        {
            Gdx.gl.glClearColor(0,0,0,1);
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
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
        stage.clear();
    }

    @Override
    public void dispose()
    {
        
    }

}
