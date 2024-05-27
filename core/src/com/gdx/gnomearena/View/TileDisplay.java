package com.gdx.gnomearena.View;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TileDisplay extends GameBoardDisplay
{
    private Image grassImage;
    private Image dirtImage;
    private Image stoneImage;
    private Image darkGrassImage;
    private Image darkDirtImage;
    private int flicker;

    private Random rand;

    private Image[][] tileImages;

    public TileDisplay()
    {
        Texture grass = new Texture("terrainSprites/Grass.png");
        Texture dirt = new Texture("terrainSprites/Dirt.png");
        Texture stone = new Texture("terrainSprites/Stone.png");
        Texture darkGrass = new Texture("terrainSprites/DarkGrass.png");
        Texture darkDirt = new Texture("terrainSprites/DarkDirt.png");

        grassImage = new Image(new Sprite(grass));
        dirtImage = new Image(new Sprite(dirt));
        stoneImage = new Image(new Sprite(stone));
        darkGrassImage = new Image(new Sprite(darkGrass));
        darkDirtImage = new Image(new Sprite(darkDirt));

        grassImage.setScale(2f);
        dirtImage.setScale(2f);
        stoneImage.setScale(2f);
        darkGrassImage.setScale(2f);
        darkDirtImage.setScale(2f);

        flicker = 0;

        rand = new Random();

        tileImages = new Image[15][15];

        for(int i=0; i<15; i++)
        {
            for(int j=0; j<15; j++)
            {
                /* float r = rand.nextFloat();

                if(r<=0.5f)
                {
                    tileImages[i][j] = grassImage;
                }
                else if(r<=0.8f)
                {
                    tileImages[i][j] = darkGrassImage;
                }
                else if(r<=0.9f)
                {
                    tileImages[i][j] = dirtImage;
                }
                else
                {
                    tileImages[i][j] = stoneImage;
                } */

                tileImages[i][j] = (i+j)%2==0 ? dirtImage : darkDirtImage;
            }
        }
    }

    public void displayTiles(Stage stage)
    {
        for(int i=0; i<15; i++)
        {
            for(int j=0; j<15; j++)
            {
                Image tile = new Image(tileImages[i][j].getDrawable());
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
