package com.gdx.gnomearena.View.GraphicalViewComponents;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gdx.gnomearena.Config.Graphics.GraphicalViewConfig;

public class GraphicalBeatMeterDisplay
{
    private final float beatMeterXPosition = GraphicalViewConfig.BEAT_METER_X_POSITION;
    private final float beatMeterYPosition = GraphicalViewConfig.BEAT_METER_Y_POSITION;
    private final float beatMeterScale = GraphicalViewConfig.BEAT_METER_SCALE;

    private Texture bmtexture;
    private Sprite bmsprite;
    private Image bmimage;
    private Texture bm2texture;
    private Sprite bm2sprite;
    private Image bm2image;

    public GraphicalBeatMeterDisplay()
    {
        bmtexture = new Texture(GraphicalViewConfig.BEAT_METER_BASE_TEXTURE);
        bmsprite = new Sprite(bmtexture);
        bmimage = new Image(bmsprite);
        bmimage.setPosition(beatMeterXPosition, beatMeterYPosition);
        bmimage.setOrigin(bmimage.getWidth()/2, bmimage.getHeight()/2);
        bmimage.setColor(GraphicalViewConfig.BEAT_METER_RING_COLOR);
        bm2texture = new Texture(GraphicalViewConfig.BEAT_METER_RING_TEXTURE);
        bm2sprite = new Sprite(bm2texture);
        bm2image = new Image(bm2sprite);
        bm2image.setPosition(beatMeterXPosition, beatMeterYPosition);
        bm2image.setOrigin(bm2image.getWidth()/2, bm2image.getHeight()/2);
    }

    public void displayBeatMeter(Stage stage, float pace, float clickWindow, float elapsedTime, boolean keyHandled)
    {
        bmimage.setScale((pace-elapsedTime)*beatMeterScale);
        bm2image.setScale((pace*clickWindow)*beatMeterScale);
        if(elapsedTime>=pace*(1-clickWindow))
        {
            if(!keyHandled)
            {
                bm2image.setColor(GraphicalViewConfig.BEAT_METER_READY_COLOR);
            }
            else
            {
                bm2image.setColor(GraphicalViewConfig.BEAT_METER_USED_COLOR);
            }
        }
        else
        {
            bm2image.setColor(GraphicalViewConfig.BEAT_METER_CHARGING_COLOR);
        }
        stage.addActor(bmimage);
        stage.addActor(bm2image);
    }
}
