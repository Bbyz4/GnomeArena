package com.gdx.gnomearena.View.GraphicalViewComponents;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gdx.gnomearena.Config.Graphics.GraphicalViewConfig;
import com.gdx.gnomearena.Model.Item;
import com.gdx.gnomearena.Model.Pair;
import com.gdx.gnomearena.Model.Items.EntitySpawningItem;
import com.gdx.gnomearena.Model.Items.WeaponPickup;

public class GraphcalItemDisplay extends GraphicalGameBoardDisplay
{
    public GraphcalItemDisplay()
    {

    }

    private float getItemsXPos(float xPos, float imageWidth)
    {
        return upperLeftTileX + tileWidth*xPos*totalBoardScale + imageWidth*totalBoardScale/2;
    }

    private float getItemsYPos(float yPos, float imageHeight)
    {
        return upperLeftTileY - (tileWidth*yPos*totalBoardScale - imageHeight*totalBoardScale/2);
    }

    public void displayItems(List<Pair<Item, Pair<Integer,Integer>>> items, Stage stage)
    {
        for(int i=0; i<items.size(); i++)
        {
            Item it = items.get(i).getKey();
            Sprite spr = new Sprite();
            if(it instanceof EntitySpawningItem)
            {
                EntitySpawningItem esit = (EntitySpawningItem)it;
                spr = new Sprite(GraphicalViewConfig.ITEM_SKINS.get(esit.spawnedEntity.getClass().getSimpleName()));
            }
            else if(it instanceof WeaponPickup)
            {
                WeaponPickup wpit = (WeaponPickup)it;
                spr = new Sprite(GraphicalViewConfig.WEAPON_SKINS.get(wpit.spawnedWeapon.getClass().getSimpleName()));
            }
            else
            {
                spr = new Sprite(GraphicalViewConfig.ITEM_SKINS.get(it.getClass().getSimpleName()));
            }

            Image im = new Image(spr);
            im.setOrigin(im.getWidth()/2, im.getHeight()/2);
            Pair<Integer,Integer> imagePos = new Pair<Integer,Integer>(items.get(i).getValue().getKey(), items.get(i).getValue().getValue());
            im.setPosition(getItemsXPos(imagePos.getKey(), im.getWidth()), getItemsYPos(imagePos.getValue(), im.getHeight()));
            im.setScale(GraphicalViewConfig.BOARD_IMAGES_SCALE*totalBoardScale);
            stage.addActor(im);
        }
    }
}
