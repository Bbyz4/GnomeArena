package com.gdx.gnomearena.View.GraphicalViewComponents;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.gdx.gnomearena.Config.Audio.MusicConfig;
import com.gdx.gnomearena.Config.Audio.SoundEffectsConfig;

public class SoundsPlayer {
    Music music;
    Float soundEffectsVolume;
    Float musicVolume;
    public SoundsPlayer()
    {
        soundEffectsVolume = SoundEffectsConfig.soundsVolume;
        musicVolume = MusicConfig.musicVolume;
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

    public void stopMusic() {
        music.stop();
    }

    public void pauseMusic() {
        music.pause();
    }

    public void disposeMuisc() {
        music.dispose();
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
