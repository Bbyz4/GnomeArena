package com.gdx.gnomearena.ViewModel;

import com.gdx.gnomearena.Model.GameManager;
import com.gdx.gnomearena.View.ActiveGameView;
import com.gdx.gnomearena.View.PassiveGameView;

import java.util.ArrayList;
import java.util.List;

public class GameViewModel {
    private final GameManager gameManager;
    private final List<PassiveGameView> passiveListeners = new ArrayList<>();
    private final List<ActiveGameView> activeListeners = new ArrayList<>();

    public GameViewModel(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void passInputToModel(int keycode) {
        gameManager.handleKeyPress(keycode);
    }
    public void endGame() {
        //IN CASE MODEL NEEDS TO UPDATE SOMETHING
    }

    public void passFrameToModel(float delta) {
        gameManager.framePass(delta);
    }
    public void registerPassiveListener(PassiveGameView listener) {
        passiveListeners.add(listener);
    }

    public void registerActiveListener(ActiveGameView listener) {
        activeListeners.add(listener);
    }

    public void initializeViews() {
            for (PassiveGameView PGV: passiveListeners) {
                PGV.initialize();
            }
    }

    public void inform(GameManager gameManager1, float delta) {
        updateAllViews(gameManager1, delta);
    }

    public void updateAllViews(GameManager gameManager1, float delta) {
        for (PassiveGameView PGV: passiveListeners) {
            PGV.displayGame(gameManager1,delta);
        }
    }

    public void disposeAll() {
        for (PassiveGameView PGV: passiveListeners) {
             PGV.finish();
        }
    }




}
