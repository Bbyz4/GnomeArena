package com.gdx.gnomearena.View;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.gnomearena.Config.Audio.MusicConfig;
import com.gdx.gnomearena.Config.Audio.SoundEffectsConfig;
import com.gdx.gnomearena.Model.*;
import com.gdx.gnomearena.View.GraphicalViewComponents.*;
import com.gdx.gnomearena.ViewModel.BoardViewInfo;
import com.gdx.gnomearena.ViewModel.GameViewModel;

public class MainGraphicalView extends PassiveGameView
{
    private final GraphicalTileDisplay tileDisplay;
    private final GraphicalEntityDisplay entityDisplay;
    private final GraphcalItemDisplay itemDisplay;
    private final GraphicalUIDisplay uiDisplay;
    private final GraphicalBeatMeterDisplay beatMeterDisplay;
    private final SoundsPlayer soundsPlayer;
    private final Hud hud;

    private  CameraManager cameraManager;
    public Stage stage;
    public Stage hudStage;
    public Stage backgroundStage;


    public MainGraphicalView(GameViewModel viewModel)
    {
        tileDisplay = new GraphicalTileDisplay();
        entityDisplay = new GraphicalEntityDisplay();
        itemDisplay = new GraphcalItemDisplay();
        uiDisplay = new GraphicalUIDisplay();
        beatMeterDisplay = new GraphicalBeatMeterDisplay();
        soundsPlayer = new SoundsPlayer();
        hud = new Hud();
        viewModel.registerPassiveListener(this);

    }

    @Override
    public void initialize()
    {
        stage = new Stage(new ScreenViewport());
        hudStage = new Stage(new ScreenViewport());
        backgroundStage = new Stage(new ScreenViewport());
        cameraManager = new CameraManager(stage);
        soundsPlayer.setMusic(MusicConfig.HUNDRED_BPM_MUSIC);
        soundsPlayer.playMusic();
    }

    @Override
    public void displayGame(GameManager gameManager, float delta)
    {
        stage.clear();
        hudStage.clear();
        backgroundStage.clear();
        playSounds();


        uiDisplay.displayBackground(backgroundStage);

        cameraManager.update(stage,gameManager.gameBoard.getPlayersPosition(), gameManager.gameBoard.size());

        tileDisplay.displayTiles(stage,cameraManager.getCamera());

        List<Pair<Item, Pair<Integer,Integer>>> boardItems = BoardViewInfo.getAllItemsWithPositions(gameManager.gameBoard);
        itemDisplay.displayItems(boardItems, stage);

        List<Pair<Entity, Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>> boardEntities = BoardViewInfo.getAllEntitiesWithMoves(gameManager.gameBoard);
        entityDisplay.displayEntities(boardEntities, stage, gameManager.timeFromPreviousMove);

        uiDisplay.displayUIBoxes(hudStage);

        Item playerItem = gameManager.player.getHeldItem();
        uiDisplay.displayPlayersItem(playerItem, hudStage);
        Weapon playerWeapon = gameManager.player.getHeldWeapon();
        uiDisplay.displayPlayersWeapon(playerWeapon, hudStage);

        beatMeterDisplay.displayBeatMeter(hudStage, gameManager.pace, gameManager.clickWindow, gameManager.elapsedTime, gameManager.keyHandled);

        hud.displayHud(hudStage, gameManager.getScore(),gameManager.getHP(),gameManager.timer);


        backgroundStage.act(delta);
        backgroundStage.draw();


        stage.act(delta);
        stage.draw();

        hudStage.act(delta);
        hudStage.draw();
    }

    private void playSounds()
    {
        ArrayList<Item> usedItems = GameLogs.getUsedItems();
        for(Item it : usedItems)
        {
            if(SoundEffectsConfig.ITEM_USAGE_SOUNDS.get(it.getClass().getSimpleName())!=null)
            {
                soundsPlayer.playSoundEffect(SoundEffectsConfig.ITEM_USAGE_SOUNDS.get(it.getClass().getSimpleName()));
            }
        }

        ArrayList<Weapon> usedWeapons = GameLogs.getDamagingWeapons();
        for(Weapon w : usedWeapons)
        {
            if(SoundEffectsConfig.WEAPON_USAGE_SOUNDS.get(w.getClass().getSimpleName())!=null)
            {
                soundsPlayer.playSoundEffect(SoundEffectsConfig.WEAPON_USAGE_SOUNDS.get(w.getClass().getSimpleName()));
            }
        }

        ArrayList<Entity> damagedEntities = GameLogs.getDamagedEntites();
        for(Entity e : damagedEntities)
        {
            if(SoundEffectsConfig.ENTITY_DAMAGE_SOUNDS.get(e.getClass().getSimpleName())!=null)
            {
                soundsPlayer.playSoundEffect(SoundEffectsConfig.ENTITY_DAMAGE_SOUNDS.get(e.getClass().getSimpleName()));
            }
        }
    }

    @Override
    public void finish()
    {
        stage.clear();
        hudStage.clear();
        backgroundStage.clear();
        soundsPlayer.disposeMusic();
    }
}
