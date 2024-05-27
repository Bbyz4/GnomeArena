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

    private Image background;

    private int boardSize;
    private Random rand;

    private Image[][] tileImages;

    public TileDisplay(int size)
    {
        boardSize = size;
        grassImage = new Image(new Sprite(new Texture("terrainSprites/Grass.png")));
        dirtImage = new Image(new Sprite(new Texture("terrainSprites/Dirt.png")));
        stoneImage = new Image(new Sprite(new Texture("terrainSprites/Stone.png")));
        darkGrassImage = new Image(new Sprite(new Texture("terrainSprites/DarkGrass.png")));
        darkDirtImage = new Image(new Sprite(new Texture("terrainSprites/DarkDirt.png")));
        grassImage.setScale(2f);
        dirtImage.setScale(2f);
        stoneImage.setScale(2f);
        darkGrassImage.setScale(2f);
        darkDirtImage.setScale(2f);

        background = new Image(new Sprite(new Texture("otherSprites/Background.jpg")));

        flicker = 0;

        rand = new Random();

        tileImages = new Image[boardSize][boardSize];

        for(int i=0; i<boardSize; i++)
        {
            for(int j=0; j<boardSize; j++)
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
        stage.addActor(background);
        for(int i=0; i<boardSize; i++)
        {
            for(int j=0; j<boardSize; j++)
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
