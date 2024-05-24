package com.gdx.gnomearena.ViewModel;

import java.util.List;

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
import com.gdx.gnomearena.Model.Entity;
import com.gdx.gnomearena.Model.GameManager;
import com.gdx.gnomearena.Model.Item;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.Weapon;
import com.gdx.gnomearena.View.BeatMeterDisplay;
import com.gdx.gnomearena.View.TileDisplay;
import com.gdx.gnomearena.View.EntityDisplay;
import com.gdx.gnomearena.View.Hud;
import com.gdx.gnomearena.View.ItemDisplay;
import com.gdx.gnomearena.View.UIDisplay;


public class GameScreen implements Screen {

    final MainGame game;
    final GameManager gameManager;
    private Stage stage;
    private Hud hud;
    private int timer = 0;

    private final TileDisplay tileDisplay;
    private final EntityDisplay entityDisplay;
    private final ItemDisplay itemDisplay;
    private final UIDisplay uiDisplay;
    private final BeatMeterDisplay beatMeterDisplay;

    private final float pace = 0.6f;
    private final float clickWindow = 0.3f;
    private boolean keyHandled = false;
    private float elapsedTime = 0;

    private float timeFromPreviousMove = 0;

    private final IntSet gameControls = new IntSet();
    OrthographicCamera camera;

    public GameScreen(MainGame game)
    {
        this.game = game;
        this.gameManager = new GameManager();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //TEMPORARY CHANGE
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);

        stage.clear();

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
        uiDisplay.displayPlayersItem(playerItem, stage);

        Weapon playerWeapon = gameManager.player.getHeldWeapon();
        uiDisplay.displayPlayersWeapon(playerWeapon, stage);

        beatMeterDisplay.displayBeatMeter(stage, pace, clickWindow, elapsedTime, keyHandled); 

        hud.scoreLabel.setText(String.format("%03d",gameManager.getScore()));
        hud.hpLabel.setText(String.format("%01d",gameManager.getHP()));
        hud.timeLabel.setText(String.format("%03d",timer));
        stage.addActor(hud.table);

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
