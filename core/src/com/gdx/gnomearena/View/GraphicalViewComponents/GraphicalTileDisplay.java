package com.gdx.gnomearena.View.GraphicalViewComponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gdx.gnomearena.Config.Graphics.GraphicalViewConfig;

public class GraphicalTileDisplay extends GraphicalGameBoardDisplay
{
    private Image dirtImage;
    private Image darkDirtImage;

    private Image background;

    private int boardSize;

    private Image[][] tileImages;

    public GraphicalTileDisplay()
    {
        this(15);
    }

    public GraphicalTileDisplay(int size)
    {
        boardSize = size;
        dirtImage = new Image(new Sprite(new Texture(GraphicalViewConfig.LIGHT_DIRT_IMAGE)));
        darkDirtImage = new Image(new Sprite(new Texture(GraphicalViewConfig.DARK_DIRT_IMAGE)));
        dirtImage.setScale(GraphicalViewConfig.TILE_IMAGE_SCALE);
        darkDirtImage.setScale(GraphicalViewConfig.TILE_IMAGE_SCALE);

        background = new Image(new Sprite(new Texture(GraphicalViewConfig.BACKGROUND_IMAGE)));

        tileImages = new Image[boardSize][boardSize];

        for(int i=0; i<boardSize; i++)
        {
            for(int j=0; j<boardSize; j++)
            {
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
                tile.setPosition(upperLeftTileX+tileWidth*i*totalBoardScale, upperLeftTileY-tileWidth*j*totalBoardScale);
                tile.setScale(GraphicalViewConfig.TILE_IMAGE_SCALE*totalBoardScale);
                stage.addActor(tile);
            }
        }
    }
}
