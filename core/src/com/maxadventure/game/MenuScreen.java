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
    private HashMap<String, BackgroundCircle> parallaxBg4 = new HashMap<>();
    private HashMap<String, BackgroundCircle> parallaxBg5 = new HashMap<>();
    private HashMap<String, BackgroundCircle> parallaxBg6 = new HashMap<>();
    private HashMap<String, BackgroundCircle> parallaxBg7 = new HashMap<>();
    private HashMap<String, BackgroundCircle> parallaxBg8 = new HashMap<>();

    private Texture B8L1, B8L2, B8L3, B8L4;
    private Texture B7L1, B7L2, B7L3, B7L4;
    private Texture B6L1, B6L2, B6L3, B6L4;
    private Texture B5L1, B5L2, B5L3, B5L4;
    private Texture B4L1, B4L2, B4L3, B4L4, B4L5;
    private Texture B3L1, B3L2, B3L3, B3L4, B3L5, B3L6;
    private Texture B2L1, B2L2, B2L3, B2L4;
    private Texture B1L1, B1L2, B1L3, B1L4, B1l5, B1L6;

    public MenuScreen(MyGdxGame myGdxGame, SpriteBatch batch, OrthographicCamera camera) {
        this.batch = batch;
        this.camera = camera;
        this.myGdxGame = myGdxGame;

        initializateBackground1();
        initializateBackground2();
        initializateBackground3();
        initializateBackground4();
        initializateBackground5();
        initializateBackground6();
        initializateBackground7();
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
        chooseBackground(delta);
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

    public long findTime(){
        long time = TimeUtils.millis();
        long seconds = (long) (time / 1000);
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long now = hours % 24 + 3;
        if (now >= 24){
            now = now % 24;
        }
        return now;
    }

    public void chooseBackground(float delta){
        long now = findTime();
        if (now >= 0 && now < 7) {
            renderBackground3(delta);
        } else if (now > 6 && now < 9) {
            renderBackground2(delta);
        } else if (now > 8 && now < 14) {
            renderBackground1(delta);
        } else if (now > 13 && now < 18) {
            renderBackground5(delta);
        } else if (now > 17 && now < 20) {
            renderBackground6(delta);
        } else if (now > 19 && now < 21) {
            renderBackground7(delta);
        } else if (now > 20 && now < 22) {
            renderBackground4(delta);
        } else if (now > 21 && now < 24) {
            renderBackground8(delta);
        }
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
        B6L4 = new Texture("MenuBackground3/1.png");
        B6L3 = new Texture("MenuBackground3/2.png");
        B6L2 = new Texture("MenuBackground3/3.png");
        B6L1 = new Texture("MenuBackground3/4.png");


        parallaxBg3.put("B6L1", new BackgroundCircle(B6L1, batch, camera, 0.1f));
        parallaxBg3.put("B6L2", new BackgroundCircle(B6L2, batch, camera, 0.4f));
        parallaxBg3.put("B6L3", new BackgroundCircle(B6L3, batch, camera, -0.13f));
        parallaxBg3.put("B6L4", new BackgroundCircle(B6L4, batch, camera, 0.07f));
    }

    public void initializateBackground4(){
        B5L4 = new Texture("MenuBackground4/1.png");
        B5L3 = new Texture("MenuBackground4/2.png");
        B5L2 = new Texture("MenuBackground4/3.png");
        B5L1 = new Texture("MenuBackground4/4.png");


        parallaxBg4.put("B5L1", new BackgroundCircle(B5L1, batch, camera, 0.1f));
        parallaxBg4.put("B5L2", new BackgroundCircle(B5L2, batch, camera, 0.4f));
        parallaxBg4.put("B5L3", new BackgroundCircle(B5L3, batch, camera, -0.13f));
        parallaxBg4.put("B5L4", new BackgroundCircle(B5L4, batch, camera, 0.07f));
    }

    public void initializateBackground5(){
        B4L5 = new Texture("MenuBackground5/1.png");
        B4L4 = new Texture("MenuBackground5/2.png");
        B4L3 = new Texture("MenuBackground5/3.png");
        B4L2 = new Texture("MenuBackground5/4.png");
        B4L1 = new Texture("MenuBackground5/5.png");


        parallaxBg5.put("B4L1", new BackgroundCircle(B4L1, batch, camera, 0.1f));
        parallaxBg5.put("B4L2", new BackgroundCircle(B4L2, batch, camera, 0.4f));
        parallaxBg5.put("B4L3", new BackgroundCircle(B4L3, batch, camera, -0.13f));
        parallaxBg5.put("B4L4", new BackgroundCircle(B4L4, batch, camera, 0.07f));
        parallaxBg5.put("B4L5", new BackgroundCircle(B4L5, batch, camera, 0.07f));
    }

    public void initializateBackground6(){
        B3L6 = new Texture("MenuBackground6/1.png");
        B3L5 = new Texture("MenuBackground6/2.png");
        B3L4 = new Texture("MenuBackground6/3.png");
        B3L3 = new Texture("MenuBackground6/4.png");
        B3L2 = new Texture("MenuBackground6/5.png");
        B3L1 = new Texture("MenuBackground6/6.png");


        parallaxBg6.put("B3L1", new BackgroundCircle(B3L1, batch, camera, 0.1f));
        parallaxBg6.put("B3L2", new BackgroundCircle(B3L2, batch, camera, 0.4f));
        parallaxBg6.put("B3L3", new BackgroundCircle(B3L3, batch, camera, -0.13f));
        parallaxBg6.put("B3L4", new BackgroundCircle(B3L4, batch, camera, 0.07f));
        parallaxBg6.put("B3L5", new BackgroundCircle(B3L5, batch, camera, 0.07f));
        parallaxBg6.put("B3L6", new BackgroundCircle(B3L6, batch, camera, 0.07f));
    }

    public void initializateBackground7(){
        B2L4 = new Texture("MenuBackground7/1.png");
        B2L3 = new Texture("MenuBackground7/2.png");
        B2L2 = new Texture("MenuBackground7/3.png");
        B2L1 = new Texture("MenuBackground7/4.png");


        parallaxBg7.put("B2L1", new BackgroundCircle(B2L1, batch, camera, 0.1f));
        parallaxBg7.put("B2L2", new BackgroundCircle(B2L2, batch, camera, 0.4f));
        parallaxBg7.put("B2L3", new BackgroundCircle(B2L3, batch, camera, -0.13f));
        parallaxBg7.put("B2L4", new BackgroundCircle(B2L4, batch, camera, 0.07f));
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

    public void renderBackground4(float delta){
        for (BackgroundCircle bgCircle : parallaxBg4.values()){
            bgCircle.render(delta);
        }
    }

    public void renderBackground5(float delta){
        for (BackgroundCircle bgCircle : parallaxBg5.values()){
            bgCircle.render(delta);
        }
    }

    public void renderBackground6(float delta){
        for (BackgroundCircle bgCircle : parallaxBg6.values()){
            bgCircle.render(delta);
        }
    }

    public void renderBackground7(float delta){
        for (BackgroundCircle bgCircle : parallaxBg7.values()){
            bgCircle.render(delta);
        }
    }

    public void renderBackground8(float delta){
        for (BackgroundCircle bgCircle : parallaxBg8.values()){
            bgCircle.render(delta);
        }
    }
}