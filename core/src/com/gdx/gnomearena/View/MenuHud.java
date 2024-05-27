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

public class MenuHud {

    private Label titleLabel;
    private Label.LabelStyle textStyle;
    private Button playButton;
    public MenuHud() {

        BitmapFont bigFont;
        FreeTypeFontGenerator ftfp;

        ftfp = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Bebas-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 100;
        param.color.set(0, 0, 0, 1);
        bigFont = ftfp.generateFont(param);
        textStyle = new Label.LabelStyle();
        textStyle.font = bigFont;
        textStyle.fontColor = Color.BLACK;
        createTitleLabel();
        createPlayButton();

    }

    public Button getPlayButton() {
        return playButton;
    }

    public void createPlayButton() {
        Skin skin = new Skin();
        skin.add("playButtonTexture1", new Texture("gnomeSprites/testtest.jpg"));
        skin.add("playButtonTexture2", new Texture("badlogic.jpg"));
        Button.ButtonStyle bstyle = new Button.ButtonStyle();
        bstyle.up = skin.getDrawable("playButtonTexture1");
        bstyle.down = skin.getDrawable("playButtonTexture2");
        playButton = new Button(bstyle);
        playButton.setPosition(810, 440);
        playButton.setWidth(300);
        playButton.setHeight(300);
    }

    public void displayPlayButton(Stage stage) {
        stage.addActor(playButton);
    }

    public void createTitleLabel() {
        titleLabel = new Label("GNOME ARENA", textStyle);
        titleLabel.setAlignment(Align.center);
        titleLabel.setPosition(860, 800);
        titleLabel.setWidth(200);
    }

    public void displayTitleLabel(Stage stage) {
        stage.addActor(titleLabel);
    }


}
