package com.gdx.gnomearena.View;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gdx.gnomearena.Model.Item;
import com.gdx.gnomearena.Model.Pair;

public class ItemDisplay
{
    private final int upperLeftTileX = 0;
    private final int upperLeftTileY = 896;

    public ItemDisplay()
    {

    }

    private float getItemsXPos(float xPos, float imageWidth)
    {
        return upperLeftTileX + 64*xPos + imageWidth/2;
    }

    private float getItemsYPos(float yPos, float imageHeight)
    {
        return upperLeftTileY - (64*yPos - imageHeight/2);
    }

    public void displayItems(List<Pair<Item, Pair<Integer,Integer>>> items, Stage stage)
    {
        for(int i=0; i<items.size(); i++)
        {
            Sprite spr = new Sprite(items.get(i).getKey().skin);
            Image im = new Image(spr);
            im.setOrigin(im.getWidth()/2, im.getHeight()/2);
            Pair<Integer,Integer> imagePos = new Pair<Integer,Integer>(items.get(i).getValue().getKey(), items.get(i).getValue().getValue());
            im.setPosition(getItemsXPos(imagePos.getKey(), im.getWidth()), getItemsYPos(imagePos.getValue(), im.getHeight()));
            im.setScale(1.5f);
            stage.addActor(im);
        }
    }
}
