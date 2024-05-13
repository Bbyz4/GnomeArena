package com.gdx.gnomearena.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class StatsHud {
    public Label scoreLabel;
    public Label timeLabel;
    public Table table;
    private BitmapFont bigFont;
    private FreeTypeFontGenerator ftfp;
    public StatsHud() {
        BitmapFont bigFont;
        FreeTypeFontGenerator ftfp;
        ftfp = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Bebas-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 100;
        param.color.set(0, 0, 0, 1);
        bigFont = ftfp.generateFont(param);
        Label.LabelStyle hudStyle = new Label.LabelStyle();
        hudStyle.font = bigFont;
        hudStyle.fontColor = Color.WHITE;
        table = new Table();
        scoreLabel = new Label(String.format("%03d", 0), hudStyle);
        timeLabel = new Label(String.format("%03d", 0), hudStyle);
        table.add(new Label("SCORE:", hudStyle)).expandX().padRight(30);
        table.add(scoreLabel);
        table.row();
        table.add(new Label("TIME:", hudStyle)).expandX().padRight(30);
        table.add(timeLabel);
        table.setPosition(620, 150);
        table.setWidth(200);
    }
}
