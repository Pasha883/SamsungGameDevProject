package com.maxadventure.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.HashMap;

public class MenuScreen implements Screen {
    private  MyGdxGame myGdxGame;
    private final SpriteBatch batch;
    private OrthographicCamera camera;

    private HashMap<String, BackgroundCircle> parallaxBg = new HashMap<>();

    private Texture B8L1, B8L2, B8L3, BL4;
    private Texture B1L1, B1L2, B1L3, B1L4, B1l5, B1L6;

    public MenuScreen(MyGdxGame myGdxGame, SpriteBatch batch, OrthographicCamera camera) {
        this.batch = batch;
        this.camera = camera;
        this.myGdxGame = myGdxGame;

        initializateBackground8();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.position.add(3, 0, 0);
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
        batch.begin();
        renderBackground(delta);
        batch.setProjectionMatrix(camera.combined);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void initializateBackground8(){
        B1L6 = new Texture("MenuBackground8/1.png");
        B1l5 = new Texture("MenuBackground8/2.png");
        B1L4 = new Texture("MenuBackground8/3.png");
        B1L3 = new Texture("MenuBackground8/4.png");
        B1L2 = new Texture("MenuBackground8/5.png");
        B1L1 = new Texture("MenuBackground8/6.png");

        parallaxBg.put("B1L1", new BackgroundCircle(B1L1, batch, camera, 0.1f));
        parallaxBg.put("B1L2", new BackgroundCircle(B1L2, batch, camera, 0.4f));
        parallaxBg.put("B1L3", new BackgroundCircle(B1L3, batch, camera, -0.13f));
        parallaxBg.put("B1L4", new BackgroundCircle(B1L4, batch, camera, 0.07f));
        parallaxBg.put("B1L5", new BackgroundCircle(B1l5, batch, camera, 0.1f));
        parallaxBg.put("B1L6", new BackgroundCircle(B1L6, batch, camera, 0.1f));
    }

    public void renderBackground(float delta){
        for (BackgroundCircle bgCircle : parallaxBg.values()){
            bgCircle.render(delta);
        }
    }
}