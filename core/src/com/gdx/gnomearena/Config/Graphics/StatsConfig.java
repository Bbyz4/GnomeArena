package com.gdx.gnomearena.Config.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class StatsConfig {

    public static final Texture GNOME_BACKGROUND_IMAGE = new Texture("otherSprites/GnomeBackground.jpg");
    public static final Texture BUTTON_BEFORE_IMAGE = new Texture("otherSprites/Menu.png");
    public static final Texture BUTTON_AFTER_IMAGE = new Texture("otherSprites/Menu.png");

    public static final FreeTypeFontGenerator PIXEL_FONT = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PixelFont.ttf"));

    public static final int STATS_PARAM_SIZE = 100;
    public static final Color STATS_PARAM_COLOR = Color.BLACK;


    public static final int GAMEOVERLABEL_POSITION_X = 860;
    public static final int GAMEOVERLABEL_POSITION_Y = 800;
    public static final int GAMEOVERLABEL_WIDTH = 200;
    public static final String GAMEOVER_TEXT = "GAME OVER";

    public static final int PLAYAGAINBUTTON_POSITION_X = 810;
    public static final int PLAYAGAINBUTTON_POSITION_Y = 440;
    public static final int PLAYAGAINBUTTON_WIDTH = 300;
    public static final int PLAYAGAINBUTTON_HEIGHT= 300;
    public static final String PLAYAGAINBUTTON_BEFORE_NAME = "playButtonTexture1";
    public static final String PLAYAGAINBUTTON_AFTER_NAME = "playButtonTexture2";


    public static final int SCORE_TABLE_POSITION_X = 860;
    public static final int SCORE_TABLE_POSITION_Y = 150;
    public static final int SCORE_TABLE_WIDTH = 200;
    public static final int SCORE_TABLE_RIGHT_PADDING = 30;
    public static final String SCORE_TEXT = "SCORE:";
    public static final String TIME_TEXT = "TIME:";


    public static final float STATS_CLEARCOLOR_V = 0f;
    public static final float STATS_CLEARCOLOR_V1 = 1f;
    public static final float STATS_CLEARCOLOR_V2 = 1f;
    public static final float STATS_CLEARCOLOR_V3 = 1f;


}
