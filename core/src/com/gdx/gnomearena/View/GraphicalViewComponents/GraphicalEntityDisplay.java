package com.gdx.gnomearena.View.GraphicalViewComponents;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gdx.gnomearena.Config.Graphics.GameConfig;
import com.gdx.gnomearena.Model.Entity;
import com.gdx.gnomearena.Model.Pair;

public class GraphicalEntityDisplay extends GraphicalGameBoardDisplay
{
    private float timeFromPreviousMove = 0;
    private final float entityHealthPadding = GameConfig.ENTITY_HEALTH_PADDING;
    private final float entityHealthScale = GameConfig.ENTITY_HEALTH_SCALE;

    public GraphicalEntityDisplay()
    {

    }

    private void showEntitiesHealth(Entity entity, float xPos, float yPos, Stage stage) {
        int maxHealth = entity.maxHealth;
        int currentHealth = entity.health;

        if(maxHealth==currentHealth)
        {
            return;
        }

        float heartWidth = GameConfig.ENTITY_HEART_WIDTH;
        float heartHeight = GameConfig.ENTITY_HEART_WIDTH;
        float padding = GameConfig.ENTITY_HEART_PADDING;

        Table table = new Table();

        for (int i = 0; i < currentHealth; i++) {
            Image heartFull = new Image(new Texture("otherSprites/HeartFull.png"));
            heartFull.setScale(entityHealthScale);
            table.add(heartFull).width(heartWidth).height(heartHeight).padRight(padding);
        }


        for (int i = currentHealth; i < maxHealth; i++) {
            Image heartEmpty = new Image(new Texture("otherSprites/HeartEmpty.png"));
            heartEmpty.setScale(entityHealthScale);
            table.add(heartEmpty).width(heartWidth).height(heartHeight).padRight(padding);
        }

        table.setPosition(xPos, yPos + entityHealthPadding);
        table.setScale(GameConfig.BOARD_IMAGES_SCALE*totalBoardScale);
        stage.addActor(table);
    }

    private Pair<Float,Float> calculateEntitiesPosition(Pair<Integer,Integer> oldPos, Pair<Integer,Integer> newPos)
    {
        float t = Math.min(timeFromPreviousMove/gnomeMovementTime, 1f);
        return new Pair<>(oldPos.getKey() + t * (newPos.getKey() - oldPos.getKey()), oldPos.getValue() + t * (newPos.getValue() - oldPos.getValue()));
    }

    private float getEntitiesXPosition(float xPos, float imageWidth)
    {
        return upperLeftTileX + tileWidth*xPos*totalBoardScale + imageWidth*totalBoardScale/2;
    }

    private float getEntitiesYPosition(float yPos, float imageHeight)
    {
        return upperLeftTileY - (tileWidth*yPos*totalBoardScale - imageHeight*totalBoardScale/2);
    }

    public void displayEntities(List<Pair<Entity, Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>> entities, Stage stage, float time)
    {
        timeFromPreviousMove = time;
        for(int i=0; i<entities.size(); i++)
        {
            Sprite spr = new Sprite(GameConfig.TURN_ENTITY_SKINS.get(entities.get(i).getKey().getClass().getSimpleName()));
            Image im = new Image(spr);
            im.setOrigin(im.getWidth()/2, im.getHeight()/2);
            Pair<Float,Float> imagePos = calculateEntitiesPosition(entities.get(i).getValue().getValue(), entities.get(i).getValue().getKey());
            float imX = getEntitiesXPosition(imagePos.getKey(), im.getWidth());
            float imY = getEntitiesYPosition(imagePos.getValue(), im.getHeight());
            im.setPosition(imX, imY);
            im.setScale(GameConfig.BOARD_IMAGES_SCALE*gnomeScale*totalBoardScale);
            stage.addActor(im);
            showEntitiesHealth(entities.get(i).getKey(), imX + im.getWidth()*totalBoardScale/2, imY, stage);
        }
    }
}
