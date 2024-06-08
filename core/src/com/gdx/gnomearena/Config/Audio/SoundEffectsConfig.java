package com.gdx.gnomearena.Config.Audio;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffectsConfig
{
    public static final Map<String, Sound> ITEM_USAGE_SOUNDS = new HashMap<>();
    public static final Map<String, Sound> WEAPON_USAGE_SOUNDS = new HashMap<>();
    public static final Map<String, Sound> ENTITY_ATTACK_SOUNDS = new HashMap<>(); //NOT USED RIGHT NOW
    public static final Map<String, Sound> ENTITY_DAMAGE_SOUNDS = new HashMap<>();

    static
    {
        ITEM_USAGE_SOUNDS.put("Medkit", Gdx.audio.newSound(Gdx.files.internal("sounds/MedKitUseSound.mp3")));

        WEAPON_USAGE_SOUNDS.put("Dagger", Gdx.audio.newSound(Gdx.files.internal("sounds/DaggerHitSound.mp3")));
        WEAPON_USAGE_SOUNDS.put("Spear", Gdx.audio.newSound(Gdx.files.internal("sounds/SpearHitSound.mp3")));  
        WEAPON_USAGE_SOUNDS.put("Hammer", Gdx.audio.newSound(Gdx.files.internal("sounds/HammerHitSound.mp3")));

        ENTITY_DAMAGE_SOUNDS.put("Player", Gdx.audio.newSound(Gdx.files.internal("sounds/PlayerTakeDamageSound.mp3")));
    }
}
