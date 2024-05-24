package com.gdx.gnomearena.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BeatMeterDisplay
{
    private final int beatMeterXPosition = 1200;
    private final int beatMeterYPosition = -300;
    private final float beatMeterScale = 1.2f;

    private Texture bmtexture;
    private Sprite bmsprite;
    private Image bmimage;
    private Texture bm2texture;
    private Sprite bm2sprite;
    private Image bm2image;

    public BeatMeterDisplay()
    {
        bmtexture = new Texture(Gdx.files.internal("otherSprites/BeatMeter.png"));
        bmsprite = new Sprite(bmtexture);
        bmimage = new Image(bmsprite);
        bmimage.setPosition(beatMeterXPosition, beatMeterYPosition);
        bmimage.setOrigin(bmimage.getWidth()/2, bmimage.getHeight()/2);
        bmimage.setColor(Color.YELLOW);
        bm2texture = new Texture(Gdx.files.internal("otherSprites/BeatMeter2.png"));
        bm2sprite = new Sprite(bm2texture);
        bm2image = new Image(bm2sprite);
        bm2image.setPosition(beatMeterXPosition, beatMeterYPosition);
        bm2image.setOrigin(bm2image.getWidth()/2, bm2image.getHeight()/2);
    }

    public void displayBeatMeter(Stage stage, float pace, float clickWindow, float elapsedTime, boolean keyHandled)
    {
        bmimage.setScale(0.3f*(pace-elapsedTime)*beatMeterScale);
        bm2image.setScale(0.3f*(pace*clickWindow)*beatMeterScale);
        if(elapsedTime>=pace*(1-clickWindow))
        {
            if(!keyHandled)
            {
                bm2image.setColor(Color.GREEN);
            }
            else
            {
                bm2image.setColor(Color.GRAY);
            }
        }
        else
        {
            bm2image.setColor(Color.RED);
        }
        stage.addActor(bmimage);
        stage.addActor(bm2image);
    }
}
