package com.gdx.gnomearena.View;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gdx.gnomearena.Model.Item;
import com.gdx.gnomearena.Model.Weapon;

public class UIDisplay
{
    private final int playerInventoryBoxXPosition = 1100;
    private final int playerInventoryBoxYPosition = 350;

    private final float UIItemsScale = 3f;

    private Texture itemFrameTexture;
    private Sprite itemFrameSprite;
    private Image itemFrameImage;
    
    private Texture weaponFrameTexture;
    private Sprite weaponFrameSprite;
    private Image weaponFrameImage;

    private List<Image> UIBoxes;;

    public UIDisplay()
    {
        UIBoxes = new ArrayList<>();

        itemFrameTexture = new Texture(Gdx.files.internal("otherSprites/ItemFrame.png"));
        itemFrameSprite = new Sprite(itemFrameTexture);
        itemFrameImage = new Image(itemFrameSprite);
        itemFrameImage.setPosition(playerInventoryBoxXPosition, playerInventoryBoxYPosition);
        itemFrameImage.setOrigin(itemFrameImage.getWidth()/2, itemFrameImage.getHeight()/2);
        UIBoxes.add(itemFrameImage);

        weaponFrameTexture = new Texture(Gdx.files.internal("otherSprites/ItemFrame.png"));
        weaponFrameSprite = new Sprite(weaponFrameTexture);
        weaponFrameImage = new Image(weaponFrameSprite);
        weaponFrameImage.setPosition(playerInventoryBoxXPosition+100, playerInventoryBoxYPosition);
        weaponFrameImage.setOrigin(weaponFrameImage.getWidth()/2, weaponFrameImage.getHeight()/2);
        UIBoxes.add(weaponFrameImage);
    }

    public void displayUIBoxes(Stage stage)
    {
        for(Image image : UIBoxes)
        {
            stage.addActor(image);
        }
    }

    public void displayPlayersWeapon(Weapon weapon, Stage stage)
    {
        if(weapon!=null)
        {
            Sprite spr = new Sprite(weapon.skin);
            Image im = new Image(spr);
            im.setPosition(playerInventoryBoxXPosition+100, playerInventoryBoxYPosition);
            im.setScale(UIItemsScale);
            stage.addActor(im);
        }
    }

    public void displayPlayersItem(Item item, Stage stage)
    {
        if(item!=null)
        {
            Sprite spr = new Sprite(item.skin);
            Image im = new Image(spr);
            im.setPosition(playerInventoryBoxXPosition, playerInventoryBoxYPosition);
            im.setScale(UIItemsScale);
            stage.addActor(im);
        }
    }
}   
