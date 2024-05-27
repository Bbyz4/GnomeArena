package com.gdx.gnomearena.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.gdx.gnomearena.ViewModel.GameScreen;

public class StatsHud {
    private Label scoreLabel;
    private Label timeLabel;
    private Table scoreTimeTable;
    private Label.LabelStyle hudStyle;
    private Label gameOverLabel;
    private Label.LabelStyle textStyle;
    private Button playAgainButton;
    public StatsHud() {

        BitmapFont bigFont;
        FreeTypeFontGenerator ftfp;

        ftfp = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Bebas-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 100;
        param.color.set(0, 0, 0, 1);
        bigFont = ftfp.generateFont(param);
        hudStyle = new Label.LabelStyle();
        hudStyle.font = bigFont;
        hudStyle.fontColor = Color.WHITE;
        textStyle = new Label.LabelStyle();
        textStyle.font = bigFont;
        textStyle.fontColor = Color.BLACK;
        scoreTimeTable = new Table();
        scoreLabel = new Label(String.format("%03d", 0), hudStyle);
        timeLabel = new Label(String.format("%03d", 0), hudStyle);
        createScoreTimeTable();
        createGameOverLabel();
        createPlayAgainButton();

    }
    public void createScoreTimeTable() {
        scoreTimeTable.add(new Label("SCORE:", hudStyle)).expandX().padRight(30);
        scoreTimeTable.add(scoreLabel);
        scoreTimeTable.row();
        scoreTimeTable.add(new Label("TIME:", hudStyle)).expandX().padRight(30);
        scoreTimeTable.add(timeLabel);
        scoreTimeTable.setPosition(860, 150);
        scoreTimeTable.setWidth(200);
    }
    public Button getPlayAgainButton() {
        return playAgainButton;
    }

    public void createPlayAgainButton() {
        Skin skin = new Skin();
        skin.add("playAgainButtonTexture1", new Texture("gnomeSprites/testtest.jpg"));
        skin.add("playAgainButtonTexture2", new Texture("badlogic.jpg"));
        Button.ButtonStyle bstyle = new Button.ButtonStyle();
        bstyle.up = skin.getDrawable("playAgainButtonTexture1");
        bstyle.down = skin.getDrawable("playAgainButtonTexture2");
        playAgainButton = new Button(bstyle);
        playAgainButton.setPosition(810, 440);
        playAgainButton.setWidth(300);
        playAgainButton.setHeight(300);
    }

    public void displayPlayAgainButton(Stage stage) {
        stage.addActor(playAgainButton);
    }

    public void createGameOverLabel() {
        gameOverLabel = new Label("GAME OVER", textStyle);
        gameOverLabel.setAlignment(Align.center);
        gameOverLabel.setPosition(860, 800);
        gameOverLabel.setWidth(200);
    }


    public void displayGameOverLabel(Stage stage) {
        stage.addActor(gameOverLabel);
    }

    public void displayScoreTimeTable(Stage stage, int score, int timer) {
        scoreLabel.setText(String.format("%03d",score));
        timeLabel.setText(String.format("%03d",timer));
        stage.addActor(scoreTimeTable);
    }


}
