package com.gdx.gnomearena.View.GraphicalViewComponents;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gdx.gnomearena.Config.Graphics.GameConfig;
import com.gdx.gnomearena.Config.Model.ModelConfig;
import com.gdx.gnomearena.Model.Pair;

public class GraphicalTileDisplay extends GraphicalGameBoardDisplay
{
    private final Image dirtImage;
    private final Image darkDirtImage;


    private final int boardSize;

    private final Image[][] tileImages;

    public GraphicalTileDisplay()
    {
        this(ModelConfig.DEFAULT_BOARD_SIZE);
    }

    public GraphicalTileDisplay(int size)
    {
        boardSize = size;
        dirtImage = new Image(new Sprite(GameConfig.LIGHT_DIRT_TEXTURE));
        darkDirtImage = new Image(new Sprite(GameConfig.DARK_DIRT_TEXTURE));
        dirtImage.setScale(GameConfig.TILE_IMAGE_SCALE);
        darkDirtImage.setScale(GameConfig.TILE_IMAGE_SCALE);


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
        int startX = GameConfig.TILES_STARTX;
        int endX = GameConfig.TILES_ENDX;
        int startY = GameConfig.TILES_STARTY;
        int endY = GameConfig.TILES_ENDY;
        for(int i=startX; i<endX; i++)
        {
            for(int j=startY; j<endY; j++)
            {
                Image tile = new Image(tileImages[i][j].getDrawable());
                tile.setPosition(upperLeftTileX+((tileWidth*(i-startX))*totalBoardScale), upperLeftTileY-((tileWidth*(j-startY))*totalBoardScale));
                tile.setScale(GameConfig.TILE_IMAGE_SCALE*totalBoardScale);
                //System.out.println((upperLeftTileX+tileWidth*(i-startX)*totalBoardScale) + " " + (upperLeftTileY-tileWidth*(j-startY)*totalBoardScale));
                stage.addActor(tile);
            }
        }
    }
}
