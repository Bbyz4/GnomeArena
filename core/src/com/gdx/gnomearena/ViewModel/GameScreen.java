package com.gdx.gnomearena.ViewModel;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.gnomearena.MainGame;
import com.gdx.gnomearena.Model.Entity;
import com.gdx.gnomearena.Model.GameManager;
import com.gdx.gnomearena.Model.Item;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.Weapon;
import com.gdx.gnomearena.View.Hud;


public class GameScreen implements Screen {

    final MainGame game;
    final GameManager gameManager;
    private Stage stage;
    private Hud hud;
    private int timer = 0;
    private Image grassBlocks[][];


    private final float pace = 0.6f;
    private final float clickWindow = 0.3f;
    private boolean keyHandled = false;
    private float elapsedTime = 0;

    private float timeFromPreviousMove = 0;
    private final float animationTime = 0.2f;
    private final float gnomeScale = 1.4f;

    private final IntSet gameControls = new IntSet();

    private Texture bmtexture;
    private Sprite bmsprite;
    private Image bmimage;
    private Texture bm2texture;
    private Sprite bm2sprite;
    private Image bm2image;

    private Texture itemFrameTexture;
    private Sprite itemFrameSprite;
    private Image itemFrameImage;
    
    private Texture weaponFrameTexture;
    private Sprite weaponFrameSprite;
    private Image weaponFrameImage;

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
                    timer++;
                    gameManager.handlePlayerInput(keycode);
                    timeFromPreviousMove = 0;
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

        bmtexture = new Texture(Gdx.files.internal("otherSprites/BeatMeter.png"));
        bmsprite = new Sprite(bmtexture);
        bmimage = new Image(bmsprite);
        bmimage.setPosition(700, -300);
        bmimage.setOrigin(bmimage.getWidth()/2, bmimage.getHeight()/2);
        bmimage.setColor(Color.YELLOW);
        bm2texture = new Texture(Gdx.files.internal("otherSprites/BeatMeter2.png"));
        bm2sprite = new Sprite(bm2texture);
        bm2image = new Image(bm2sprite);
        bm2image.setPosition(700, -300);
        bm2image.setOrigin(bm2image.getWidth()/2, bm2image.getHeight()/2);

        itemFrameTexture = new Texture(Gdx.files.internal("otherSprites/ItemFrame.png"));
        itemFrameSprite = new Sprite(itemFrameTexture);
        itemFrameImage = new Image(itemFrameSprite);
        itemFrameImage.setPosition(1100, 350);
        itemFrameImage.setOrigin(itemFrameImage.getWidth()/2, itemFrameImage.getHeight()/2);

        weaponFrameTexture = new Texture(Gdx.files.internal("otherSprites/ItemFrame.png"));
        weaponFrameSprite = new Sprite(weaponFrameTexture);
        weaponFrameImage = new Image(weaponFrameSprite);
        weaponFrameImage.setPosition(1200, 350);
        weaponFrameImage.setOrigin(weaponFrameImage.getWidth()/2, weaponFrameImage.getHeight()/2);
    }
    
    private Pair<Float,Float> calculateImagePosition(Pair<Integer,Integer> oldPos, Pair<Integer,Integer> newPos)
    {
        float t = Math.min(timeFromPreviousMove/animationTime, 1f);
        return new Pair<Float,Float>(oldPos.getKey() + t * (newPos.getKey() - oldPos.getKey()), oldPos.getValue() + t * (newPos.getValue() - oldPos.getValue()));
    }

    private float getImagesXPos(float xPos, float imageWidth)
    {
        return 64*xPos + imageWidth/2;
    }

    private float getImagesYPos(float yPos, float imageHeight)
    {
        return 896 - (64*yPos - imageHeight/2);
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
        }

        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //TEMPORARY CHANGE
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);

        stage.clear();

        for(int i=0; i<15; i++)
        {
            for(int j=0; j<15; j++)
            {
                stage.addActor(grassBlocks[i][j]);
            }
        }

        List<Pair<Item, Pair<Integer,Integer>>> itemDisplay = gameManager.gameBoard.getAllItemsWithPositions();
        for(int i=0; i<itemDisplay.size(); i++)
        {
            Sprite spr = new Sprite(itemDisplay.get(i).getKey().skin);
            Image im = new Image(spr);
            im.setOrigin(im.getWidth()/2, im.getHeight()/2);
            Pair<Integer,Integer> imagePos = new Pair<Integer,Integer>(itemDisplay.get(i).getValue().getKey(), itemDisplay.get(i).getValue().getValue());
            im.setPosition(getImagesXPos(imagePos.getKey(), im.getWidth()), getImagesYPos(imagePos.getValue(), im.getHeight()));
            im.setScale(1.5f);
            stage.addActor(im);
        }

        List<Pair<Entity, Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>> gnomeDisplay = gameManager.gameBoard.getAllEntitiesWithMoves();
        for(int i=0; i<gnomeDisplay.size(); i++)
        {
            Sprite spr = new Sprite(gnomeDisplay.get(i).getKey().skin);
            Image im = new Image(spr);
            im.setOrigin(im.getWidth()/2, im.getHeight()/2);
            Pair<Float,Float> imagePos = calculateImagePosition(gnomeDisplay.get(i).getValue().getValue(), gnomeDisplay.get(i).getValue().getKey());
            im.setPosition(getImagesXPos(imagePos.getKey(), im.getWidth()), getImagesYPos(imagePos.getValue(), im.getHeight()));
            im.setScale(1.5f*gnomeScale);
            stage.addActor(im);
        }

        if(gameManager.isOver()) {
            game.setScreen(new StatsScreen(game,gameManager.getScore(), timer));
        }

        stage.addActor(itemFrameImage);
        Item playerItem = gameManager.player.getHeldItem();

        if(playerItem!=null)
        {
            Sprite spr = new Sprite(playerItem.skin);
            Image im = new Image(spr);
            im.setPosition(1100, 350);
            im.setScale(3f);
            stage.addActor(im);
        }

        stage.addActor(weaponFrameImage);
        Weapon playerWeapon = gameManager.player.getHeldWeapon();

        if(playerWeapon!=null)
        {
            Sprite spr = new Sprite(playerWeapon.skin);
            Image im = new Image(spr);
            im.setPosition(1200, 350);
            im.setScale(3f);
            stage.addActor(im);
        }

        bmimage.setScale(0.3f*(pace-elapsedTime));
        bm2image.setScale(0.3f*(pace*clickWindow));
        if(elapsedTime>=pace*(1-clickWindow))
        {
            if(!keyHandled)
            {
                bm2image.setColor(Color.GREEN);
            }
            else
            {
                bm2image.setColor(Color.GRAY);
            }
        }
        else
        {
            bm2image.setColor(Color.RED);
        }
        stage.addActor(bmimage);
        stage.addActor(bm2image); 

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
