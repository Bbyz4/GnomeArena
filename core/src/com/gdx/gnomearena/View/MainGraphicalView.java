package com.gdx.gnomearena.View;

import java.util.List;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.gnomearena.Model.*;
import com.gdx.gnomearena.ViewModel.BoardViewInfo;

public class MainGraphicalView extends PassiveGameView
{
    private final TileDisplay tileDisplay;
    private final EntityDisplay entityDisplay;
    private final ItemDisplay itemDisplay;
    private final UIDisplay uiDisplay;
    private final BeatMeterDisplay beatMeterDisplay;
    private final SoundsPlayer soundsPlayer;

    public Stage stage;
    public Stage hudStage;

    public MainGraphicalView()
    {
        tileDisplay = new TileDisplay();
        entityDisplay = new EntityDisplay();
        itemDisplay = new ItemDisplay();
        uiDisplay = new UIDisplay();
        beatMeterDisplay = new BeatMeterDisplay();
        soundsPlayer = new SoundsPlayer();
    }

    @Override
    public void initialize()
    {
        stage = new Stage(new ScreenViewport());
        hudStage = new Stage(new ScreenViewport());
    }

    @Override
    public void displayGame(GameManager gameManager, float delta)
    {
        stage.clear();
        hudStage.clear();

        for (Sound s: SoundsManager.getSoundList())
        {
            soundsPlayer.playSoundEffect(s);
        }
        SoundsManager.clearList();

        tileDisplay.displayTiles(stage);

        List<Pair<Item, Pair<Integer,Integer>>> boardItems = BoardViewInfo.getAllItemsWithPositions(gameManager.gameBoard);
        itemDisplay.displayItems(boardItems, stage);

        List<Pair<Entity, Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>> boardEntities = BoardViewInfo.getAllEntitiesWithMoves(gameManager.gameBoard);
        entityDisplay.displayEntities(boardEntities, stage, gameManager.timeFromPreviousMove);

        uiDisplay.displayUIBoxes(stage);

        Item playerItem = gameManager.player.getHeldItem();
        uiDisplay.displayPlayersItem(playerItem, hudStage);
        Weapon playerWeapon = gameManager.player.getHeldWeapon();
        uiDisplay.displayPlayersWeapon(playerWeapon, hudStage);

        beatMeterDisplay.displayBeatMeter(hudStage, gameManager.pace, gameManager.clickWindow, gameManager.elapsedTime, gameManager.keyHandled);

        registeredTo.hud.displayHud(hudStage, gameManager.getScore(),gameManager.getHP(),gameManager.timer);

        stage.act(delta);
        stage.draw();

        hudStage.act(delta);
        hudStage.draw();
    }

    @Override
    public void finalize()
    {
        stage.clear();
    }
}
