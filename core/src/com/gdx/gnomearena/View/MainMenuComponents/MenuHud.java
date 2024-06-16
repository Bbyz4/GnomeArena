package com.gdx.gnomearena.View.MainMenuComponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.gdx.gnomearena.Config.Graphics.MainMenuConfig;

public class MenuHud {

    private Label titleLabel;
    private final Label.LabelStyle textStyle;
    private Button playButton;
    private final Image gnome_background;

    public MenuHud() {

        BitmapFont bigFont;
        FreeTypeFontGenerator ftfp = MainMenuConfig.PIXEL_FONT;
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = MainMenuConfig.PARAM_MENU_SIZE;
        param.color.set(MainMenuConfig.PARAM_MENU_COLOR);
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
        skin.add(MainMenuConfig.PLAYBUTTON_BEFORE_NAME, MainMenuConfig.BUTTON_BEFORE_IMAGE);
        skin.add(MainMenuConfig.PLAYBUTTON_AFTER_NAME, MainMenuConfig.BUTTON_AFTER_IMAGE);
        Button.ButtonStyle bstyle = new Button.ButtonStyle();
        bstyle.up = skin.getDrawable(MainMenuConfig.PLAYBUTTON_BEFORE_NAME);
        bstyle.down = skin.getDrawable(MainMenuConfig.PLAYBUTTON_AFTER_NAME);
        playButton = new Button(bstyle);
        playButton.setPosition(MainMenuConfig.PLAYBUTTON_POSITION_X, MainMenuConfig.PLAYBUTTON_POSITION_Y);
        playButton.setWidth(MainMenuConfig.PLAYBUTTON_WIDTH);
        playButton.setHeight(MainMenuConfig.PLAYBUTTON_HEIGHT);
    }

    public void displayPlayButton(Stage stage) {
        stage.addActor(playButton);
    }

    public void createTitleLabel() {
        titleLabel = new Label(MainMenuConfig.TITLE_TEXT, textStyle);
        titleLabel.setAlignment(Align.center);
        titleLabel.setPosition(MainMenuConfig.TITLELABEL_POSITION_X, MainMenuConfig.TITLELABEL_POSITION_Y);
        titleLabel.setWidth(MainMenuConfig.TITLELABEL_WIDTH);
    }

    public void displayTitleLabel(Stage stage) {
        stage.addActor(titleLabel);
    }



    public void displayGnomeBackground(Stage stage)
    {
        stage.addActor(gnome_background);
    }



}
