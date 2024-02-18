package com.maxadventure.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends Game {
    SpriteBatch batch;
    //public static int HEIGHT, WIDTH;
    public static float WIDTH = 1920/(2f * 10);
    public static float HEIGHT = 1080/(2f * 10);
    public OrthographicCamera camera;

    public static float SCREEN_WIDTH;
    public static float SCREEN_HEIGHT;

    public OrthographicCamera hudCamera = new OrthographicCamera();

    public static boolean isJoysticStatic = false;
    public static boolean isJoysticMode = false;

    public IntroScreen introScreen;
    public GameScreen gameScreen;
    public MenuScreen menuScreen;
    public SettingsScreen settingsScreen;
    public MemeMenuScreen memeMenuScreen;
    public StartVideoScreen startVideoScreen;

    public static Vector2 leftBottomPointCamera = new Vector2();

    public static int language = 1;

    Texture img;

    public static MyGdxGame instance;
    public static InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public MyGdxGame(){
        super();
        instance = this;
    }


    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        hudCamera.setToOrtho(false, WIDTH, HEIGHT);
        camera.setToOrtho(false, WIDTH, HEIGHT);
        introScreen = new IntroScreen(this, batch, camera);
        startVideoScreen = new StartVideoScreen(this, batch, camera);
        gameScreen = new GameScreen(batch, camera, hudCamera, this);
        settingsScreen = new SettingsScreen(this, batch, camera);
        memeMenuScreen = new MemeMenuScreen(this, batch, camera);
        leftBottomPointCamera.set(
                (int)(camera.position.x) - MyGdxGame.WIDTH/2,
                (int)(camera.position.y) - MyGdxGame.HEIGHT/2
        );

        menuScreen = new MenuScreen(this, batch, camera);
        setScreen(menuScreen);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }


    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
