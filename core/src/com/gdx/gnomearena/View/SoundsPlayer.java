package com.gdx.gnomearena.View;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundsPlayer {
    Music music;
    Float soundEffectsVolume;
    Float musicVolume;
    public SoundsPlayer()
    {
        //music = Gdx.audio.newMusic(Gdx.files.internal("butong.wav"));
        soundEffectsVolume = 1.0f;
        musicVolume = 1.0f;
    }

    public void playMusic()
    {
        music.setVolume(musicVolume);
        music.setLooping(true);
        music.play();
    }
    public void setMusic(Music m)
    {
        music = m;
    }

    public void setSoundEffectsVolume(Float volume)
    {
        soundEffectsVolume = volume;
    }

    public void setMusicVolume(Float volume)
    {
        musicVolume = volume;
    }

    public void playSoundEffect(Sound s)
    {
        long id = s.play(soundEffectsVolume);
        s.setLooping(id,false);
    }

}
