package com.gdx.gnomearena.Model;

import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;
import java.util.HashSet;

public class SoundsManager {
    private static ArrayList<Sound> soundList = new ArrayList<>();

    public SoundsManager() {
        soundList = new ArrayList<>();
    }
    public static void addSound(Sound sound) {
        soundList.add(sound);
    }
    public static ArrayList<Sound> getSoundList() {
        return soundList;
    }
    public static void clearList() {
        soundList.clear();
    }


}
