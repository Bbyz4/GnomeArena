package com.gdx.gnomearena.Config.Graphics;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GameConfig
{
    //BOARD AND BACKGROUND
    public static final float TOTAL_BOARD_SCALE = 1.1f;
    public static final int UPPER_LEFT_TILE_X = 420;
    public static final int UPPER_LEFT_TILE_Y = 995;
    public static final float TILE_WIDTH = 64f;
    public static final float TILE_IMAGE_SCALE = 2f;

    //FONTS
    public static final FreeTypeFontGenerator PIXEL_FONT = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PixelFont.ttf"));

    public static final Texture LIGHT_DIRT_TEXTURE = new Texture("terrainSprites/Dirt.png");
    public static final Texture DARK_DIRT_TEXTURE  = new Texture("terrainSprites/DarkDirt.png");

    public static final Texture BACKGROUND_IMAGE = new Texture("otherSprites/Background.jpg");

    //BEAT METER
    public static final Texture BEAT_METER_BASE_TEXTURE = new Texture("otherSprites/BeatMeter.png");
    public static final Texture BEAT_METER_RING_TEXTURE = new Texture("otherSprites/BeatMeter2.png");

    public static final float BEAT_METER_X_POSITION = 1200f;
    public static final float BEAT_METER_Y_POSITION = -300f;
    public static final float BEAT_METER_SCALE = 0.36f;

    public static final Color BEAT_METER_READY_COLOR = Color.GREEN;
    public static final Color BEAT_METER_CHARGING_COLOR = Color.RED;
    public static final Color BEAT_METER_USED_COLOR = Color.GRAY;

    public static final Color BEAT_METER_RING_COLOR = Color.YELLOW;

    //UI
    public static final float PLAYER_INVENTORY_BOX_X_POSITION = 60f;
    public static final float PLAYER_INVENTORY_BOX_Y_POSITION = 900f;
    public static final float BOX_SEPARATOR_SIZE = 150f;
    public static final float UI_ITEMS_SCALE = 4f;

    public static final Texture ITEM_FRAME_TEXTURE = new Texture("otherSprites/ItemFrame2.png");
    public static final Texture WEAPON_FRAME_TEXTURE = new Texture("otherSprites/WeaponFrame.png");

    public static final Map<String, Texture> ITEM_SKINS = new HashMap<>();
    public static final Map<String, Texture> TURN_ENTITY_SKINS = new HashMap<>();
    public static final Map<String, Texture> WEAPON_SKINS = new HashMap<>();

    //ENTITIES AND ITEMS
    public static final float BOARD_IMAGES_SCALE = 1.5f;
    public static final float ENTITY_HEALTH_PADDING = 50.0f;
    public static final float ENTITY_HEALTH_SCALE = 1.0f;
    public static final int ENTITY_HEART_WIDTH = 32;
    public static final int ENTITY_HEART_PADDING = 1;
    public static final float GNOME_SCALE = 1.4f;
    public static final float GNOME_MOVEMENT_TIME = 0.2f;


    //HUD

    public static final int HUD_TABLE_POSITION_X = 1600;
    public static final int HUD_TABLE_POSITION_Y = 900;
    public static final int HUD_TABLE_WIDTH = 200;
    public static final int PARAM_SIZE = 100;
    public static final Color PARAM_COLOR = Color.BLACK;
    public static final int HUD_RIGHT_PADDING = 30;

    //TILES
    public static final int TILES_STARTX = 0;
    public static final int TILES_STARTY = 0;
    public static final int TILES_ENDX = 15;
    public static final int TILES_ENDY = 15;



    static
    {
        ITEM_SKINS.put("Medkit", new Texture("itemSprites/Medkit.png"));
        ITEM_SKINS.put("Bomb", new Texture("itemSprites/BombPickup.png"));

        TURN_ENTITY_SKINS.put("Player", new Texture("playerSprites/Player.png"));

        TURN_ENTITY_SKINS.put("BasicGnome", new Texture("gnomeSprites/BasicGnome.png"));
        TURN_ENTITY_SKINS.put("ScoutGnome", new Texture("gnomeSprites/ScoutGnome.png"));
        TURN_ENTITY_SKINS.put("WizardGnome", new Texture("gnomeSprites/WizardGnome.png"));
        TURN_ENTITY_SKINS.put("TrapGnome", new Texture("gnomeSprites/TrapGnome.png"));

        TURN_ENTITY_SKINS.put("MagicBullet", new Texture("gnomeSprites/MagicBullet.png"));
        TURN_ENTITY_SKINS.put("Trap", new Texture("itemSprites/Trap.png"));
        TURN_ENTITY_SKINS.put("Bomb", new Texture("itemSprites/BombExploding.png"));

        WEAPON_SKINS.put("Dagger", new Texture("weaponSprites/Dagger.png"));
        WEAPON_SKINS.put("Spear", new Texture("weaponSprites/Spear.png"));
        WEAPON_SKINS.put("Hammer", new Texture("weaponSprites/Hammer.png"));
    }
}

