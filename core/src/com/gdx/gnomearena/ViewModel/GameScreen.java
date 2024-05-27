package com.gdx.gnomearena.ViewModel;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.IntSet;
//import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.gnomearena.MainGame;
import com.gdx.gnomearena.Model.Entity;
import com.gdx.gnomearena.Model.GameManager;
import com.gdx.gnomearena.Model.Item;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.Weapon;
import com.gdx.gnomearena.View.*;


public class GameScreen implements Screen {

    final MainGame game;
    final GameManager gameManager;
    private Stage stage;
    private Stage hudStage;
    private Hud hud;
    private int timer = 0;

    private final TileDisplay tileDisplay;
    private final EntityDisplay entityDisplay;
    private final ItemDisplay itemDisplay;
    private final UIDisplay uiDisplay;
    private final BeatMeterDisplay beatMeterDisplay;
    private final SoundsPlayer soundsPlayer;

    private final float pace = 0.6f;
    private final float clickWindow = 0.3f;
    private boolean keyHandled = false;
    private float elapsedTime = 0;

    private float timeFromPreviousMove = 0;
    //private Viewport viewport;

    private final IntSet gameControls = new IntSet();
    OrthographicCamera camera;

    public GameScreen(MainGame game)
    {
        this.game = game;
        this.gameManager = new GameManager();
        //camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //camera = new OrthographicCamera(gameManager.gameBoard.getPlayersPosition().getKey(),gameManager.gameBoard.getPlayersPosition().getValue());
        //camera = new OrthographicCamera(0,0);
        camera = new OrthographicCamera();
        hud = new Hud();
        // WASD - movement, E - use item
        gameControls.addAll(
            Input.Keys.W,
            Input.Keys.A,
            Input.Keys.S,
            Input.Keys.D,
            Input.Keys.E
        );

        tileDisplay = new TileDisplay();
        entityDisplay = new EntityDisplay();
        itemDisplay = new ItemDisplay();
        uiDisplay = new UIDisplay();
        beatMeterDisplay = new BeatMeterDisplay();
        soundsPlayer = new SoundsPlayer();

    }

    @Override
    public void show()
    {
        hudStage = new Stage(new ScreenViewport());

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())); //random number rn

        Gdx.input.setInputProcessor(new InputAdapter()
        {
            @Override
            public boolean keyDown(int keycode)
            {
                if(gameControls.contains(keycode) && !keyHandled && elapsedTime>=pace*(1-clickWindow))
                {
                    timer++;
                    gameManager.handlePlayerInput(keycode);
                    timeFromPreviousMove = 0;
                    keyHandled = true;
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta)
    {
        stage.clear();
        hudStage.clear();
        elapsedTime += delta;
        timeFromPreviousMove += delta;

        if(elapsedTime >= pace)
        {
            if(!keyHandled)
            {
                timer++;
                gameManager.handlePlayerInput(-1);
                timeFromPreviousMove = 0;
            }
            keyHandled = false;
            elapsedTime = 0;
            //boardDisplay.flick(); too flashy imo
        }
        /** TESTING */
        //camera.position.x=gameManager.gameBoard.getPlayersPosition().getKey()*32;
        //camera.position.y=gameManager.gameBoard.getPlayersPosition().getValue()*32;
        //stage.getCamera().position.set(gameManager.gameBoard.getPlayersPosition().getKey()*32 + Gdx.graphics.getWidth()/2,gameManager.gameBoard.getPlayersPosition().getValue()*32+Gdx.graphics.getHeight()/2,0);


        for (Sound s: gameManager.gameBoard.soundList) {
            soundsPlayer.playSoundEffect(s);
        }
        gameManager.gameBoard.soundList.clear();

        /**END OF TESTING */


        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //TEMPORARY CHANGE
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);



        tileDisplay.displayTiles(stage);

        List<Pair<Item, Pair<Integer,Integer>>> boardItems = gameManager.gameBoard.getAllItemsWithPositions();
        itemDisplay.displayItems(boardItems, stage);

        List<Pair<Entity, Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>> boardEntities = gameManager.gameBoard.getAllEntitiesWithMoves();
        entityDisplay.displayEntities(boardEntities, stage, timeFromPreviousMove);

        if(gameManager.isOver()) {
            game.setScreen(new StatsScreen(game,gameManager.getScore(), timer));
        }

        uiDisplay.displayUIBoxes(stage);

        Item playerItem = gameManager.player.getHeldItem();
        uiDisplay.displayPlayersItem(playerItem, hudStage);
        Weapon playerWeapon = gameManager.player.getHeldWeapon();
        uiDisplay.displayPlayersWeapon(playerWeapon, hudStage);

        beatMeterDisplay.displayBeatMeter(hudStage, pace, clickWindow, elapsedTime, keyHandled);

        hud.displayHud(hudStage, gameManager.getScore(),gameManager.getHP(),timer);
        stage.act(delta);

        stage.getBatch().setProjectionMatrix(camera.combined);
        stage.draw();

        hudStage.act(delta);
        hudStage.draw();
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
