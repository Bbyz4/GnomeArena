package com.gdx.gnomearena.Config.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class StatsConfig {

    public static final Texture GNOME_BACKGROUND_IMAGE = new Texture("otherSprites/GnomeBackground.jpg");
    public static final Texture BUTTON_BEFORE_IMAGE = new Texture("otherSprites/Menu.png");
    public static final Texture BUTTON_AFTER_IMAGE = new Texture("otherSprites/Menu.png");

    public static final FreeTypeFontGenerator PIXEL_FONT = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PixelFont.ttf"));
}
