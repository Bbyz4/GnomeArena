package com.gdx.gnomearena.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.gdx.gnomearena.MainGame;

public class MainMenuScreen implements Screen
{
    final MainGame game;

    OrthographicCamera camera;

    Label.LabelStyle textStyle = new Label.LabelStyle();

    Button playButton;
    Label titleLabel;

    public MainMenuScreen(final MainGame game)
    {
        this.game = game;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show()
    {
        Skin skin = new Skin();
        skin.add("playButtonTexture1", new Texture("gnomeSprites/testtest.jpg"));
        skin.add("playButtonTexture2", new Texture("badlogic.jpg"));
        Button.ButtonStyle bstyle = new Button.ButtonStyle();
        bstyle.up = skin.getDrawable("playButtonTexture1");
        bstyle.down = skin.getDrawable("playButtonTexture2");
        playButton = new Button(bstyle);
        playButton.setPosition(620, 305);
        playButton.setWidth(200);
        playButton.setHeight(200);
        playButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new GameScreen(game));
            }
        });
        game.stage.addActor(playButton);

        textStyle.font = game.font;
        textStyle.fontColor = Color.BLACK;

        titleLabel = new Label("GNOME ARENA", textStyle);
        titleLabel.setAlignment(Align.center);
        titleLabel.setPosition(620, 700);
        titleLabel.setWidth(200);
        game.stage.addActor(titleLabel);
    }

    @Override
    public void render(float delta)
    {
        camera.update();

        Gdx.gl.glClearColor(0,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.stage.act(delta);
        game.stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        //throw new UnsupportedOperationException("Unimplemented method 'resize'");
    }

    @Override
    public void pause()
    {
        //throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume()
    {
        //throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public void hide()
    {
        game.stage.clear();
    }

    @Override
    public void dispose()
    {
        //throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }
}
