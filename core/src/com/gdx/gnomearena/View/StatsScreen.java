package com.gdx.gnomearena.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.gnomearena.Config.Graphics.StatsConfig;
import com.gdx.gnomearena.MainGame;
import com.gdx.gnomearena.View.StatsComponents.StatsHud;

public class StatsScreen implements Screen
{
    final MainGame game;

    OrthographicCamera camera;
    private Stage stage;
    private final StatsHud statsHud;
    private final int score;
    private final int timer;

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


        statsHud.getPlayAgainButton().addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        statsHud.displayPlayAgainButton((stage));
        statsHud.displayGameOverLabel(stage);
        statsHud.displayScoreTimeTable(stage,score,timer);


        ; //DONT REMOVE
    }

    @Override
    public void render(float delta)
    {
        camera.update();

        Gdx.gl.glClearColor(StatsConfig.STATS_CLEARCOLOR_V,StatsConfig.STATS_CLEARCOLOR_V1,StatsConfig.STATS_CLEARCOLOR_V2,StatsConfig.STATS_CLEARCOLOR_V3);
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
        statsHud.getPlayAgainButton().clearListeners();
        stage.clear();
    }

    @Override
    public void dispose()
    {
        //throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }


}
