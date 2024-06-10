package com.gdx.gnomearena.View.GraphicalViewComponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gdx.gnomearena.Config.Graphics.GraphicalViewConfig;
import com.gdx.gnomearena.Model.Pair;

public class CameraManager {
    private Camera camera;
    private float cameraX;
    private float cameraY;
    public CameraManager(Stage stage) {
        camera = stage.getCamera();
    }

    public void update(Stage stage, Pair<Integer,Integer> playersPosition, int boardSize) {
        //TESTING
        float tileWidth = GraphicalViewConfig.TILE_WIDTH*GraphicalViewConfig.TOTAL_BOARD_SCALE;
        //camera.position.set(playersPosition.getKey()* tileWidth, playersPosition.getValue()*tileWidth,0);
        //camera.position.set(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),0);
        camera.update();
       // System.out.println(camera.position);
        float halfViewportWidth = camera.viewportWidth / 2;
        float halfViewportHeight = camera.viewportHeight / 2;
        float mapWidth = boardSize * tileWidth; // Szerokość planszy w pikselach
        float mapHeight = boardSize * tileWidth; // Wysokość planszy w pikselach

        cameraX = MathUtils.clamp(playersPosition.getKey()+0.5f,7.5f,boardSize-7.5f);
        cameraY = MathUtils.clamp(playersPosition.getValue()+0.5f,6.5f,boardSize-8.5f); //will be in config

        camera.position.set(GraphicalViewConfig.UPPER_LEFT_TILE_X + (cameraX*tileWidth),GraphicalViewConfig.UPPER_LEFT_TILE_Y - (cameraY*tileWidth),0);

        //camera.position.x = MathUtils.clamp(camera.position.x, halfViewportWidth, Gdx.graphics.getWidth() - halfViewportWidth);
       // camera.position.y = MathUtils.clamp(camera.position.y, halfViewportHeight, Gdx.graphics.getHeight() - halfViewportHeight);

        stage.getBatch().setProjectionMatrix(camera.combined);
    }
    public Pair<Integer,Integer> getCamera() {
        return new Pair<>((int) (cameraX-0.5),(int)(cameraY-0.5));
    }


}
