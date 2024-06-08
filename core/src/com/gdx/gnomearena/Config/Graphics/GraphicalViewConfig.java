package com.gdx.gnomearena.Config.Graphics;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;

public class GraphicalViewConfig
{
    //BOARD AND BACKGROUND
    public static final float TOTAL_BOARD_SCALE = 1.1f;
    public static final int UPPER_LEFT_TILE_X = 420;
    public static final int UPPER_LEFT_TILE_Y = 995;
    public static final float TILE_WIDTH = 64f;
    public static final float TILE_IMAGE_SCALE = 2f;

    public static final String LIGHT_DIRT_IMAGE = "terrainSprites/Dirt.png";
    public static final String DARK_DIRT_IMAGE = "terrainSprites/DarkDirt.png";

    public static final String BACKGROUND_IMAGE = "otherSprites/Background.jpg";

    //BEAT METER
    public static final String BEAT_METER_BASE_TEXTURE = "otherSprites/BeatMeter.png";
    public static final String BEAT_METER_RING_TEXTURE = "otherSprites/BeatMeter2.png";

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

    public static final String ITEM_FRAME_TEXTURE = "otherSprites/ItemFrame2.png";
    public static final String WEAPON_FRAME_TEXTURE = "otherSprites/WeaponFrame.png";

    public static final Map<String, String> ITEM_SKINS = new HashMap<>();
    public static final Map<String, String> TURN_ENTITY_SKINS = new HashMap<>();
    public static final Map<String, String> WEAPON_SKINS = new HashMap<>();

    //ENTITIES AND ITEMS
    public static final float BOARD_IMAGES_SCALE = 1.5f;
    public static final float ENTITY_HEALTH_PADDING = 50.0f;
    public static final float ENTITY_HEALTH_SCALE = 1.0f;
    public static final int ENTITY_HEART_WIDTH = 32;
    public static final int ENTITY_HEART_PADDING = 1;
    public static final float GNOME_SCALE = 1.4f;
    public static final float GNOME_MOVEMENT_TIME = 0.2f;

    static
    {
        ITEM_SKINS.put("Medkit", "itemSprites/Medkit.png");
        ITEM_SKINS.put("Bomb", "itemSprites/BombPickup.png");

        TURN_ENTITY_SKINS.put("Player", "playerSprites/Player.png");

        TURN_ENTITY_SKINS.put("BasicGnome", "gnomeSprites/BasicGnome.png");
        TURN_ENTITY_SKINS.put("ScoutGnome", "gnomeSprites/ScoutGnome.png");
        TURN_ENTITY_SKINS.put("WizardGnome", "gnomeSprites/WizardGnome.png");
        TURN_ENTITY_SKINS.put("TrapGnome", "gnomeSprites/TrapGnome.png");

        TURN_ENTITY_SKINS.put("MagicBullet", "gnomeSprites/MagicBullet.png");
        TURN_ENTITY_SKINS.put("Trap", "itemSprites/Trap.png");
        TURN_ENTITY_SKINS.put("Bomb", "itemSprites/BombExploding.png");

        WEAPON_SKINS.put("Dagger", "weaponSprites/Dagger.png");
        WEAPON_SKINS.put("Spear", "weaponSprites/Spear.png");
        WEAPON_SKINS.put("Hammer", "weaponSprites/Hammer.png");
    }
}