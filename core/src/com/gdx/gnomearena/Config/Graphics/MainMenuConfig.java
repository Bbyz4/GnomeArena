package com.gdx.gnomearena.Config.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MainMenuConfig
{

    public static final Texture GNOME_BACKGROUND_IMAGE = new Texture("otherSprites/GnomeBackground.jpg");
    public static final Texture BUTTON_BEFORE_IMAGE = new Texture("otherSprites/PlayButton.png");
    public static final Texture BUTTON_AFTER_IMAGE = new Texture("otherSprites/AfterPlayButton.png");

    public static final FreeTypeFontGenerator PIXEL_FONT = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PixelFont.ttf"));
    public static final int PARAM_MENU_SIZE = 150;
    public static final Color PARAM_MENU_COLOR = Color.BLACK;

    public static final int PLAYBUTTON_POSITION_X = 810;
    public static final int PLAYBUTTON_POSITION_Y = 50;
    public static final int PLAYBUTTON_WIDTH = 300;
    public static final int PLAYBUTTON_HEIGHT= 300;
    public static final String PLAYBUTTON_BEFORE_NAME = "playButtonTexture1";
    public static final String PLAYBUTTON_AFTER_NAME = "playButtonTexture2";


    public static final int TITLELABEL_POSITION_X = 860;
    public static final int TITLELABEL_POSITION_Y = 800;
    public static final int TITLELABEL_WIDTH = 200;
    public static final String TITLE_TEXT = "GNOME ARENA";

    public static final float MENU_CLEARCOLOR_V = 0f;
    public static final float MENU_CLEARCOLOR_V1 = 1f;
    public static final float MENU_CLEARCOLOR_V2 = 1f;
    public static final float MENU_CLEARCOLOR_V3 = 1f;
    


}
