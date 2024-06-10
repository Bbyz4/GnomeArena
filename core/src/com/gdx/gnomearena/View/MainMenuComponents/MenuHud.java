package com.gdx.gnomearena.View.MainMenuComponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.gdx.gnomearena.Config.Graphics.MainMenuConfig;

public class MenuHud {

    private Label titleLabel;
    private Label.LabelStyle textStyle;
    private Button playButton;
    private Image gnome_background;

    public MenuHud() {

        BitmapFont bigFont;
        FreeTypeFontGenerator ftfp = MainMenuConfig.PIXEL_FONT;
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 150;
        param.color.set(0, 0, 0, 1);
        bigFont = ftfp.generateFont(param);
        textStyle = new Label.LabelStyle();
        textStyle.font = bigFont;
        textStyle.fontColor = Color.BLACK;
        createTitleLabel();
        createPlayButton();
        gnome_background = new Image(new Sprite(MainMenuConfig.GNOME_BACKGROUND_IMAGE));

    }

    public Button getPlayButton() {
        return playButton;
    }

    public void createPlayButton() {
        Skin skin = new Skin();
        skin.add("playButtonTexture1", MainMenuConfig.BUTTON_BEFORE_IMAGE);
        skin.add("playButtonTexture2", MainMenuConfig.BUTTON_AFTER_IMAGE);
        Button.ButtonStyle bstyle = new Button.ButtonStyle();
        bstyle.up = skin.getDrawable("playButtonTexture1");
        bstyle.down = skin.getDrawable("playButtonTexture2");
        playButton = new Button(bstyle);
        playButton.setPosition(810, 50);
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



    public void displayGnomeBackground(Stage stage)
    {
        stage.addActor(gnome_background);
    }



}
