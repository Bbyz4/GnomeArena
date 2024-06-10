package com.gdx.gnomearena.Config.Audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


public class MusicConfig {

    public static final Music HUNDRED_BPM_MUSIC = Gdx.audio.newMusic(Gdx.files.internal("music/100bpm.ogg"));
    public static final float musicVolume = 0.5f;

}
