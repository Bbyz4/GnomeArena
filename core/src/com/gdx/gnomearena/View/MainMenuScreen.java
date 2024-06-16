package com.gdx.gnomearena.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gdx.gnomearena.MainGame;
import com.gdx.gnomearena.Model.GameManager;
import com.gdx.gnomearena.View.MainMenuComponents.MenuHud;
//import com.gdx.gnomearena.ViewModel.CurrentViewModel;
import com.gdx.gnomearena.ViewModel.GameViewModel;

public class MainMenuScreen implements Screen
{
    final MainGame game;
    OrthographicCamera camera;

    Label.LabelStyle textStyle = new Label.LabelStyle();
    private Stage stage;

    MenuHud menuHud;

    public MainMenuScreen(final MainGame game)
    {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        menuHud = new MenuHud();
    }



    @Override
    public void show()
    {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        menuHud.displayGnomeBackground(stage);
        menuHud.getPlayButton().addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                //WHEN WE FIND BETTER SOLUTION IT WILL BE CHANGED
                GameManager gameManager = new GameManager();
                GameViewModel viewModel = new GameViewModel(gameManager);
                GameScreen gameScreen = new GameScreen(game,gameManager,viewModel);
                MainGraphicalView mainGraphicalView = new MainGraphicalView(viewModel);
                game.setScreen(gameScreen);

            }
        });
        menuHud.displayPlayButton(stage);
        menuHud.displayTitleLabel(stage);
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
        menuHud.getPlayButton().clearListeners();
        stage.clear();
    }

    @Override
    public void dispose()
    {
        //throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }

}
