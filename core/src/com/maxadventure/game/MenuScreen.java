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
import com.badlogic.gdx.utils.TimeUtils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class MenuScreen implements Screen {
    private  MyGdxGame myGdxGame;
    private final SpriteBatch batch;
    private OrthographicCamera camera;



    private HashMap<String, BackgroundCircle> parallaxBg1 = new HashMap<>();
    private HashMap<String, BackgroundCircle> parallaxBg2 = new HashMap<>();
    private HashMap<String, BackgroundCircle> parallaxBg3 = new HashMap<>();
    private HashMap<String, BackgroundCircle> parallaxBg8 = new HashMap<>();

    private Texture B8L1, B8L2, B8L3, B8L4;
    private Texture B7L1, B7L2, B7L3, B7L4;
    private Texture B6L1, B6L2, B6L3, B6L4;
    private Texture B1L1, B1L2, B1L3, B1L4, B1l5, B1L6;

    public MenuScreen(MyGdxGame myGdxGame, SpriteBatch batch, OrthographicCamera camera) {
        this.batch = batch;
        this.camera = camera;
        this.myGdxGame = myGdxGame;

        initializateBackground1();
        initializateBackground2();
        initializateBackground3();
        initializateBackground8();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        long time = TimeUtils.millis();
        long seconds = (long) (time / 1000);
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long now = hours % 24 + 3;
        camera.position.add(3, 0, 0);
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
        batch.begin();
        if (now > 19) {
            renderBackground8(delta);
        } else if (now < 18) {
            renderBackground2(delta);
        }
        batch.setProjectionMatrix(camera.combined);
        //if (Gdx.input.justTouched()) myGdxGame.setScreen(myGdxGame.testMapScreen);
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


    public void initializateBackground1(){
        B8L4 = new Texture("MenuBackground1/1.png");
        B8L3 = new Texture("MenuBackground1/2.png");
        B8L2 = new Texture("MenuBackground1/3.png");
        B8L1 = new Texture("MenuBackground1/4.png");


        parallaxBg1.put("B8L1", new BackgroundCircle(B8L1, batch, camera, 0.1f));
        parallaxBg1.put("B8L2", new BackgroundCircle(B8L2, batch, camera, 0.4f));
        parallaxBg1.put("B8L3", new BackgroundCircle(B8L3, batch, camera, -0.13f));
        parallaxBg1.put("B8L4", new BackgroundCircle(B8L4, batch, camera, 0.07f));
    }

    public void initializateBackground2(){
        B7L4 = new Texture("MenuBackground2/1.png");
        B7L3 = new Texture("MenuBackground2/2.png");
        B7L2 = new Texture("MenuBackground2/3.png");
        B7L1 = new Texture("MenuBackground2/4.png");


        parallaxBg2.put("B7L1", new BackgroundCircle(B7L1, batch, camera, 0.1f));
        parallaxBg2.put("B7L2", new BackgroundCircle(B7L2, batch, camera, 0.4f));
        parallaxBg2.put("B7L3", new BackgroundCircle(B7L3, batch, camera, -0.13f));
        parallaxBg2.put("B7L4", new BackgroundCircle(B7L4, batch, camera, 0.07f));
    }

    public void initializateBackground3(){
        B6L4 = new Texture("MenuBackground2/1.png");
        B6L3 = new Texture("MenuBackground2/2.png");
        B6L2 = new Texture("MenuBackground2/3.png");
        B6L1 = new Texture("MenuBackground2/4.png");


        parallaxBg3.put("B6L1", new BackgroundCircle(B6L1, batch, camera, 0.1f));
        parallaxBg3.put("B6L2", new BackgroundCircle(B6L2, batch, camera, 0.4f));
        parallaxBg3.put("B6L3", new BackgroundCircle(B6L3, batch, camera, -0.13f));
        parallaxBg3.put("B6L4", new BackgroundCircle(B6L4, batch, camera, 0.07f));
    }

    public void initializateBackground8(){
        B1L6 = new Texture("MenuBackground8/1.png");
        B1l5 = new Texture("MenuBackground8/2.png");
        B1L4 = new Texture("MenuBackground8/3.png");
        B1L3 = new Texture("MenuBackground8/4.png");
        B1L2 = new Texture("MenuBackground8/5.png");
        B1L1 = new Texture("MenuBackground8/6.png");

        parallaxBg8.put("B1L1", new BackgroundCircle(B1L1, batch, camera, 0.1f));
        parallaxBg8.put("B1L2", new BackgroundCircle(B1L2, batch, camera, 0.4f));
        parallaxBg8.put("B1L3", new BackgroundCircle(B1L3, batch, camera, -0.13f));
        parallaxBg8.put("B1L4", new BackgroundCircle(B1L4, batch, camera, 0.07f));
        parallaxBg8.put("B1L5", new BackgroundCircle(B1l5, batch, camera, 0.1f));
        parallaxBg8.put("B1L6", new BackgroundCircle(B1L6, batch, camera, 0.1f));
    }

    public void renderBackground1(float delta){
        for (BackgroundCircle bgCircle : parallaxBg1.values()){
            bgCircle.render(delta);
        }
    }
    public void renderBackground2(float delta){
        for (BackgroundCircle bgCircle : parallaxBg2.values()){
            bgCircle.render(delta);
        }
    }

    public void renderBackground3(float delta){
        for (BackgroundCircle bgCircle : parallaxBg3.values()){
            bgCircle.render(delta);
        }
    }

    public void renderBackground8(float delta){
        for (BackgroundCircle bgCircle : parallaxBg8.values()){
            bgCircle.render(delta);
        }
    }
}