package com.gdx.gnomearena.View.StatsComponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.gdx.gnomearena.Config.Graphics.GameConfig;
import com.gdx.gnomearena.Config.Graphics.StatsConfig;

public class StatsHud {
    private final Label scoreLabel;
    private final Label timeLabel;
    private final Table scoreTimeTable;
    private final Label.LabelStyle hudStyle;
    private Label gameOverLabel;
    private final Label.LabelStyle textStyle;
    private Button playAgainButton;
    public StatsHud() {

        BitmapFont bigFont;
        FreeTypeFontGenerator ftfp = GameConfig.PIXEL_FONT;
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = StatsConfig.STATS_PARAM_SIZE;
        param.color.set(StatsConfig.STATS_PARAM_COLOR);
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
        scoreTimeTable.add(new Label(StatsConfig.SCORE_TEXT, hudStyle)).expandX().padRight(StatsConfig.SCORE_TABLE_RIGHT_PADDING);
        scoreTimeTable.add(scoreLabel);
        scoreTimeTable.row();
        scoreTimeTable.add(new Label(StatsConfig.TIME_TEXT, hudStyle)).expandX().padRight(StatsConfig.SCORE_TABLE_RIGHT_PADDING);
        scoreTimeTable.add(timeLabel);
        scoreTimeTable.setPosition(StatsConfig.SCORE_TABLE_POSITION_X, StatsConfig.SCORE_TABLE_POSITION_Y);
        scoreTimeTable.setWidth(StatsConfig.SCORE_TABLE_WIDTH);
    }
    public Button getPlayAgainButton() {
        return playAgainButton;
    }

    public void createPlayAgainButton() {
        Skin skin = new Skin();
        skin.add(StatsConfig.PLAYAGAINBUTTON_BEFORE_NAME, StatsConfig.BUTTON_BEFORE_IMAGE);
        skin.add(StatsConfig.PLAYAGAINBUTTON_AFTER_NAME, StatsConfig.BUTTON_AFTER_IMAGE);
        Button.ButtonStyle bstyle = new Button.ButtonStyle();
        bstyle.up = skin.getDrawable(StatsConfig.PLAYAGAINBUTTON_BEFORE_NAME);
        bstyle.down = skin.getDrawable(StatsConfig.PLAYAGAINBUTTON_AFTER_NAME);
        playAgainButton = new Button(bstyle);
        playAgainButton.setPosition(StatsConfig.PLAYAGAINBUTTON_POSITION_X, StatsConfig.PLAYAGAINBUTTON_POSITION_Y);
        playAgainButton.setWidth(StatsConfig.PLAYAGAINBUTTON_WIDTH);
        playAgainButton.setHeight(StatsConfig.PLAYAGAINBUTTON_HEIGHT);
    }

    public void displayPlayAgainButton(Stage stage) {
        stage.addActor(playAgainButton);
    }

    public void createGameOverLabel() {
        gameOverLabel = new Label(StatsConfig.GAMEOVER_TEXT, textStyle);
        gameOverLabel.setAlignment(Align.center);
        gameOverLabel.setPosition(StatsConfig.GAMEOVERLABEL_POSITION_X, StatsConfig.GAMEOVERLABEL_POSITION_Y);
        gameOverLabel.setWidth(StatsConfig.GAMEOVERLABEL_WIDTH);
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
