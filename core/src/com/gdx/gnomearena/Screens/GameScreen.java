package com.gdx.gnomearena.Screens;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.gnomearena.MainGame;
import com.gdx.gnomearena.Core.Entity;
import com.gdx.gnomearena.Core.GameManager;
import com.gdx.gnomearena.Core.Pair;

public class GameScreen implements Screen {

    final MainGame game;
    final GameManager gameManager;
    private Stage stage;


    private Image grassBlocks[][];


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

        grassBlocks = new Image[15][15];
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

        Texture grass1 = new Texture("terrainSprites/Grass1.png");
        Texture grass2 = new Texture("terrainSprites/Grass2.png");



        for(int i=0; i<15; i++)
        {
            for(int j=0; j<15; j++)
            {
                Texture tex = (i+j)%2==0 ? grass2 : grass1;
                Sprite sprite = new Sprite(tex);
                grassBlocks[i][j] = new Image(sprite);
                grassBlocks[i][j].setPosition(0+64*i, 896-64*j);
                grassBlocks[i][j].setScale(2f);
            }
        }
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
                //Gdx.gl.glClearColor(1,0,0,1);
            }
            else
            {
                //Gdx.gl.glClearColor(0,1,0,1);
            }
        }
        else
        {
            //Gdx.gl.glClearColor(0,0,0,1);
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        stage.clear();

        for(int i=0; i<15; i++)
        {
            for(int j=0; j<15; j++)
            {
                stage.addActor(grassBlocks[i][j]);
            }
        }

        List<Pair<Entity, Pair<Integer,Integer>>> gnomeDisplay = gameManager.gameBoard.getAllEntitiesWithPositions();
        for(int i=0; i<gnomeDisplay.size(); i++)
        {
            Sprite spr = new Sprite(gnomeDisplay.get(i).getKey().skin);
            Image im = new Image(spr);
            im.setOrigin(im.getWidth()/2, im.getHeight()/2);
            im.setPosition(64*gnomeDisplay.get(i).getValue().getKey() + im.getWidth()/2, 896 - (64*gnomeDisplay.get(i).getValue().getValue() - im.getHeight()/2));
            im.setScale(1.5f);
            stage.addActor(im);
        }




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
