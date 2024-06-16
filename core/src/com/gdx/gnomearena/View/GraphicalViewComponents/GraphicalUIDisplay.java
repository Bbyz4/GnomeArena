package com.gdx.gnomearena.View.GraphicalViewComponents;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gdx.gnomearena.Config.Graphics.GameConfig;
import com.gdx.gnomearena.Model.Item;
import com.gdx.gnomearena.Model.Weapon;
import com.gdx.gnomearena.Model.Items.EntitySpawningItem;

public class GraphicalUIDisplay
{
    private final float playerInventoryBoxXPosition = GameConfig.PLAYER_INVENTORY_BOX_X_POSITION;
    private final float playerInventoryBoxYPosition = GameConfig.PLAYER_INVENTORY_BOX_Y_POSITION;
    private final float boxSeparatorSize = GameConfig.BOX_SEPARATOR_SIZE;

    private final float UIItemsScale = GameConfig.UI_ITEMS_SCALE;
    private final Image itemFrameImage;
    private final Image weaponFrameImage;
    private final Image background;


    private final List<Image> UIBoxes;

    public GraphicalUIDisplay()
    {
        UIBoxes = new ArrayList<>();
        itemFrameImage = new Image(new Sprite(GameConfig.ITEM_FRAME_TEXTURE));
        itemFrameImage.setPosition(playerInventoryBoxXPosition, playerInventoryBoxYPosition);
        itemFrameImage.setScale(UIItemsScale);
        UIBoxes.add(itemFrameImage);

        weaponFrameImage = new Image(new Sprite(GameConfig.WEAPON_FRAME_TEXTURE));
        weaponFrameImage.setPosition(playerInventoryBoxXPosition+boxSeparatorSize, playerInventoryBoxYPosition);
        weaponFrameImage.setScale(UIItemsScale);
        UIBoxes.add(weaponFrameImage);

        background = new Image(new Sprite(GameConfig.BACKGROUND_IMAGE));

    }

    public void displayUIBoxes(Stage stage)
    {
        for(Image image : UIBoxes)
        {
            stage.addActor(image);
        }
    }

    public void displayBackground(Stage stage)
    {
        stage.addActor(background);
    }


    public void displayPlayersWeapon(Weapon weapon, Stage stage)
    {
        if(weapon!=null)
        {
            Sprite spr = new Sprite(GameConfig.WEAPON_SKINS.get(weapon.getClass().getSimpleName()));
            Image im = new Image(spr);
            im.setPosition(playerInventoryBoxXPosition+boxSeparatorSize, playerInventoryBoxYPosition);
            im.setScale(UIItemsScale);
            stage.addActor(im);
        }
    }

    public void displayPlayersItem(Item item, Stage stage)
    {
        if(item!=null)
        {
            Sprite spr;
            if(item instanceof EntitySpawningItem)
            {
                EntitySpawningItem esit = (EntitySpawningItem)item;
                spr = new Sprite(GameConfig.ITEM_SKINS.get(esit.spawnedEntity.getClass().getSimpleName()));
            }
            else
            {
                spr = new Sprite(GameConfig.ITEM_SKINS.get(item.getClass().getSimpleName()));
            }
            Image im = new Image(spr);
            im.setPosition(playerInventoryBoxXPosition, playerInventoryBoxYPosition);
            im.setScale(UIItemsScale);
            stage.addActor(im);
        }
    }
}   
