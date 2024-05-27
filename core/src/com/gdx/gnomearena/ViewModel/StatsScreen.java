package com.gdx.gnomearena.ViewModel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.gnomearena.MainGame;
import com.gdx.gnomearena.View.StatsHud;
public class StatsScreen implements Screen
{
    final MainGame game;

    OrthographicCamera camera;
    private BitmapFont bigFont;
    private FreeTypeFontGenerator ftfp;
    private Stage stage;
    private StatsHud statsHud;
    private int score;
    private int timer;
    Button playButton;
    Label gameOverLabel;

    public StatsScreen(final MainGame game, int score, int timer)
    {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        statsHud = new StatsHud();
        this.score = score;
        this.timer = timer;
    }

    @Override
    public void show()
    {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        ftfp = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Bebas-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 100;
        param.color.set(0,0,0,1);
        bigFont = ftfp.generateFont(param);



        statsHud.getPlayButton().addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new GameScreen(game));
            }
        });

        statsHud.displayPlayButton(stage);
        statsHud.displayGameOverLabel(stage);
        statsHud.displayScoreTimeTable(stage,score,timer);


        ;
    }

    @Override
    public void render(float delta)
    {
        camera.update();

        Gdx.gl.glClearColor(0,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
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
        statsHud.getPlayButton().clearListeners();
        stage.clear();
    }

    @Override
    public void dispose()
    {
        //throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }
}
