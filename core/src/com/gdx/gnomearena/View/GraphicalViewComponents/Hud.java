package com.gdx.gnomearena.View.GraphicalViewComponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.gdx.gnomearena.Config.Graphics.GameConfig;


public class Hud {
    public Label scoreLabel;
    public Label hpLabel;
    public Label timeLabel;
    public Table table;
    Label.LabelStyle hudStyle;
    public Hud() {

        BitmapFont bigFont;
        FreeTypeFontGenerator ftfp = GameConfig.PIXEL_FONT;
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = GameConfig.PARAM_SIZE;
        param.color.set(GameConfig.PARAM_COLOR);
        bigFont = ftfp.generateFont(param);
        hudStyle = new Label.LabelStyle();
        hudStyle.font = bigFont;
        hudStyle.fontColor = Color.WHITE;
        table = new Table();
        scoreLabel = new Label(String.format("%03d", 0), hudStyle);
        hpLabel = new Label(String.format("%03d", 0), hudStyle);
        timeLabel = new Label(String.format("%03d", 0), hudStyle);
        createTable();
    }
    public void createTable() {
        table.add(new Label(GameConfig.SCORETEXT, hudStyle)).expandX().padRight(GameConfig.HUD_RIGHT_PADDING);
        table.add(scoreLabel);
        table.row();
        table.add(new Label(GameConfig.HPTEXT, hudStyle)).expandX().padRight(GameConfig.HUD_RIGHT_PADDING);
        table.add(hpLabel);
        table.row();
        table.add(new Label(GameConfig.TIMETEXT, hudStyle)).expandX().padRight(GameConfig.HUD_RIGHT_PADDING);
        table.add(timeLabel);
        table.setPosition(GameConfig.HUD_TABLE_POSITION_X, GameConfig.HUD_TABLE_POSITION_Y);
        table.setWidth(GameConfig.HUD_TABLE_WIDTH);

    }

    public void displayHud(Stage stage, int score, int hp, int timer) {
        scoreLabel.setText(String.format("%03d",score));
        hpLabel.setText(String.format("%01d",hp));
        timeLabel.setText(String.format("%03d",timer));

        stage.addActor(table);
    }

}
