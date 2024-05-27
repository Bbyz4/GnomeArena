package com.gdx.gnomearena.View;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public class Hud {
    public Label scoreLabel;
    public Label hpLabel;
    public Label timeLabel;
    public Table table;
    Label.LabelStyle hudStyle;
    public Hud() {

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
        table = new Table();
        scoreLabel = new Label(String.format("%03d", 0), hudStyle);
        hpLabel = new Label(String.format("%03d", 0), hudStyle);
        timeLabel = new Label(String.format("%03d", 0), hudStyle);
        table.add(new Label("SCORE:", hudStyle)).expandX().padRight(30);
        table.add(scoreLabel);
        table.row();
        table.add(new Label("HP:", hudStyle)).expandX().padRight(30);
        table.add(hpLabel);
        table.row();
        table.add(new Label("TIME:", hudStyle)).expandX().padRight(30);
        table.add(timeLabel);
        table.setPosition(1600, 900);
        table.setWidth(200);
    }

    public void displayHud(Stage stage, int score, int hp, int timer) {
        scoreLabel.setText(String.format("%03d",score));
        hpLabel.setText(String.format("%01d",hp));
        timeLabel.setText(String.format("%03d",timer));

        stage.addActor(table);
    }

}
