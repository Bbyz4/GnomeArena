package com.gdx.gnomearena.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TileDisplay extends GameBoardDisplay
{
    private Image grass1Image;
    private Image grass2Image;
    private int flicker;

    public TileDisplay()
    {
        Texture grass1 = new Texture("terrainSprites/Grass1.png");
        Texture grass2 = new Texture("terrainSprites/Grass2.png");

        grass1Image = new Image(new Sprite(grass1));
        grass2Image = new Image(new Sprite(grass2));

        grass1Image.setScale(2f);
        grass2Image.setScale(2f);

        flicker = 0;
    }

    public void displayTiles(Stage stage)
    {
        for(int i=0; i<15; i++)
        {
            for(int j=0; j<15; j++)
            {
                Image img = (i+j+flicker)%2==0 ? grass2Image : grass1Image;
                Image tile = new Image(img.getDrawable());
                tile.setPosition(upperLeftTileX+64*i*totalBoardScale, upperLeftTileY-64*j*totalBoardScale);
                tile.setScale(2f*totalBoardScale);
                stage.addActor(tile);
            }
        }
    }

    public void flick()
    {
        flicker = (flicker+1)%2;
    }
}
