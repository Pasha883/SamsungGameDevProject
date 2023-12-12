package com.maxadventure.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends Game {
    SpriteBatch batch;
    public static int HEIGHT, WIDTH;
    public OrthographicCamera camera;

    public IntroScreen introScreen;
    public TestMapScreen testMapScreen;
    public MenuScreen menuScreen;
    public static Vector2 leftBottomPointCamera = new Vector2();

    Texture img;


    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        introScreen = new IntroScreen(this, batch, camera);
        testMapScreen = new TestMapScreen(this, batch, camera);
        leftBottomPointCamera.set(
                (int)(camera.position.x) - MyGdxGame.WIDTH/2,
                (int)(camera.position.y) - MyGdxGame.HEIGHT/2
        );

        menuScreen = new MenuScreen(this, batch, camera);
        setScreen(introScreen);
    }


    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
