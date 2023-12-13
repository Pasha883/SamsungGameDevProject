package com.maxadventure.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.HashMap;

public class SettingsScreen implements Screen {

    private  MyGdxGame myGdxGame;
    private final SpriteBatch batch;
    private OrthographicCamera camera;

    private int lang = 2;

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

    private Texture ENG01, ENG02, RUS01, RUS02;
    private Texture stat01ENG, stat02ENG, stat01RUS, stat02RUS;
    private Texture dynam01ENG, dynam02ENG, dynam01RUS, dynam02RUS, BLM;
    private Texture back01ENG, back02ENG, back01RUS, back02RUS;
    private Button ENG, RUS, staticRUS, staticENG, dynamRUS, dynamENG, backRUS, backENG;

    private Texture JMTENG, LTENG, NSTENG, STENG, SeTENG;
    private Texture JMTRUS, LTRUS, NSTRUS, STRUS, SeTRUS;
    private Sound click;
    private Sprite BLMM;

    public SettingsScreen(MyGdxGame myGdxGame, SpriteBatch batch, OrthographicCamera camera) {
        click = Gdx.audio.newSound(Gdx.files.internal("Sounds/Click.mp3"));

        ENG01 = new Texture("SettingsScreenAssets/Buttons/Language/ENG/Eng01.png");
        ENG02 = new Texture("SettingsScreenAssets/Buttons/Language/ENG/Eng02.png");
        RUS01 = new Texture("SettingsScreenAssets/Buttons/Language/RUS/Rus01.png");
        RUS02 = new Texture("SettingsScreenAssets/Buttons/Language/RUS/Rus02.png");

        stat01ENG = new Texture("SettingsScreenAssets/Buttons/Static/ENG/Stat01.png");
        stat02ENG = new Texture("SettingsScreenAssets/Buttons/Static/ENG/Stat02.png");
        stat01RUS = new Texture("SettingsScreenAssets/Buttons/Static/RUS/Stat01.png");
        stat02RUS = new Texture("SettingsScreenAssets/Buttons/Static/RUS/Stat02.png");

        dynam01ENG = new Texture("SettingsScreenAssets/Buttons/Dynamic/ENG/Dynam01.png");
        dynam02ENG = new Texture("SettingsScreenAssets/Buttons/Dynamic/ENG/Dynam02.png");
        dynam01RUS = new Texture("SettingsScreenAssets/Buttons/Dynamic/RUS/Dynam01.png");
        dynam02RUS = new Texture("SettingsScreenAssets/Buttons/Dynamic/RUS/Dynam02.png");

        back01ENG = new Texture("SettingsScreenAssets/Buttons/Back/ENG/back01.png");
        back02ENG = new Texture("SettingsScreenAssets/Buttons/Back/ENG/back03.png");
        back01RUS = new Texture("SettingsScreenAssets/Buttons/Back/RUS/back01.png");
        back02RUS = new Texture("SettingsScreenAssets/Buttons/Back/RUS/back03.png");

        JMTENG = new Texture("SettingsScreenAssets/Assets/ENG/JoyModeText.png");
        LTENG = new Texture("SettingsScreenAssets/Assets/ENG/LangText.png");
        NSTENG = new Texture("SettingsScreenAssets/Assets/ENG/NonStatText.png");
        STENG = new Texture("SettingsScreenAssets/Assets/ENG/StatText.png");
        SeTENG = new Texture("SettingsScreenAssets/Assets/ENG/SettText.png");

        JMTENG = new Texture("SettingsScreenAssets/Assets/ENG/JoyModeText.png");
        LTENG = new Texture("SettingsScreenAssets/Assets/ENG/LangText.png");
        NSTENG = new Texture("SettingsScreenAssets/Assets/ENG/NonStatText.png");
        STENG = new Texture("SettingsScreenAssets/Assets/ENG/StatText.png");
        SeTENG = new Texture("SettingsScreenAssets/Assets/ENG/SettText.png");

        JMTRUS = new Texture("SettingsScreenAssets/Assets/RUS/JoyModeText.png");
        LTRUS = new Texture("SettingsScreenAssets/Assets/RUS/LangText.png");
        NSTRUS = new Texture("SettingsScreenAssets/Assets/RUS/NonStatText.png");
        STRUS = new Texture("SettingsScreenAssets/Assets/RUS/StatText.png");
        SeTRUS = new Texture("SettingsScreenAssets/Assets/RUS/SettText.png");

        BLM = new Texture("SettingsScreenAssets/BLM.jpg");
        BLMM = new Sprite(BLM);

        this.batch = batch;
        this.camera = camera;
        this.myGdxGame = myGdxGame;

        ENG = new Button(ENG01, batch,
                500, 160, new Vector2(Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 + 100));
        RUS = new Button(RUS01, batch,
                500, 160, new Vector2(Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 + 100));

        staticENG = new Button(stat01ENG, batch,
                500, 160, new Vector2(Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 - 230));
        staticRUS = new Button(stat01RUS, batch,
                500, 160, new Vector2(Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 - 230));

        dynamENG = new Button(dynam01ENG, batch,
                500, 160, new Vector2(Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 - 230));
        dynamRUS = new Button(dynam01RUS, batch,
                500, 160, new Vector2(Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 - 230));

        backENG = new Button(back01ENG, batch,
                500, 160, new Vector2(Gdx.graphics.getWidth() - 500, Gdx.graphics.getHeight() / 2 - 500));
        backRUS = new Button(back01RUS, batch,
                500, 160, new Vector2(Gdx.graphics.getWidth() - 500, Gdx.graphics.getHeight() / 2 - 500));

        initializateBackground1();
        initializateBackground2();
        initializateBackground3();
        initializateBackground4();
        initializateBackground5();
        initializateBackground6();
        initializateBackground7();
        initializateBackground8();

        initializeMenuElementsRUS();
        initializeMenuElementsENG();
    }

    @Override
    public void show() {
        BLMM.setAlpha(0.5f);
    }

    @Override
    public void render(float delta) {
        boolean isStat = MyGdxGame.isJoysticStatic;
        lang = MyGdxGame.language;
        BLMM.setPosition((int)(camera.position.x) - MyGdxGame.WIDTH/2,
                (int)(camera.position.y) - MyGdxGame.HEIGHT/2);
        MyGdxGame.leftBottomPointCamera.set(
                (int)(camera.position.x) - MyGdxGame.WIDTH/2,
                (int)(camera.position.y) - MyGdxGame.HEIGHT/2
        );
        camera.position.add(3, 0, 0);
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
        batch.begin();
        chooseBackground(delta);
        BLMM.draw(batch);
        if (lang == 1) {
            batch.draw(SeTENG,
                    (int) (camera.position.x - SeTENG.getWidth() * 1.5),
                    Gdx.graphics.getHeight() - 100,
                    SeTENG.getWidth() * 3,
                    SeTENG.getHeight() * 3);

            batch.draw(LTENG,
                    (int) (camera.position.x - LTENG.getWidth() * 1.5),
                    Gdx.graphics.getHeight() - 260,
                    LTENG.getWidth() * 3,
                    LTENG.getHeight() * 3);

            batch.draw(JMTENG,
                    (int) (camera.position.x - JMTENG.getWidth() * 1.5),
                    Gdx.graphics.getHeight() - 600,
                    JMTENG.getWidth() * 3,
                    JMTENG.getHeight() * 3);
            ENG.draw();
            if (isStat == true) {
                staticENG.draw();
                batch.draw(STENG,
                        (int) (camera.position.x - STENG.getWidth() * 1),
                        Gdx.graphics.getHeight() - 960,
                        STENG.getWidth() * 2,
                        STENG.getHeight() * 2);
            } else if (isStat == false) {
                dynamENG.draw();
                batch.draw(NSTENG,
                        (int) (camera.position.x - NSTENG.getWidth() * 1),
                        Gdx.graphics.getHeight() - 880,
                        NSTENG.getWidth() * 2,
                        NSTENG.getHeight() * 2);
            }

            backENG.draw();
        } else if (lang == 2) {
            batch.draw(SeTRUS,
                    (int) (camera.position.x - SeTRUS.getWidth() * 1.5),
                    Gdx.graphics.getHeight() - 100,
                    SeTRUS.getWidth() * 3,
                    SeTRUS.getHeight() * 3);

            batch.draw(LTRUS,
                    (int) (camera.position.x - LTRUS.getWidth() * 1.5),
                    Gdx.graphics.getHeight() - 260,
                    LTRUS.getWidth() * 3,
                    LTENG.getHeight() * 3);

            batch.draw(JMTRUS,
                    (int) (camera.position.x - JMTRUS.getWidth() * 1.5),
                    Gdx.graphics.getHeight() - 600,
                    JMTRUS.getWidth() * 3,
                    JMTRUS.getHeight() * 3);
            RUS.draw();
            if (isStat == true) {
                staticRUS.draw();
                batch.draw(STRUS,
                        (int) (camera.position.x - STRUS.getWidth() * 1),
                        Gdx.graphics.getHeight() - 960,
                        STRUS.getWidth() * 2,
                        STRUS.getHeight() * 2);
            } else if (isStat == false) {
                dynamRUS.draw();
                batch.draw(NSTRUS,
                        (int) (camera.position.x - NSTRUS.getWidth() * 1),
                        Gdx.graphics.getHeight() - 880,
                        NSTRUS.getWidth() * 2,
                        NSTRUS.getHeight() * 2);
            }

            backRUS.draw();
        }

        if (staticENG.isHit() || staticRUS.isHit() || dynamENG.isHit() || dynamRUS.isHit()) {
            if (isStat == true) {
                MyGdxGame.isJoysticStatic = false;
                click.play();
                if (lang == 1) {
                    batch.draw(stat02ENG,
                            camera.position.x - 250,
                            Gdx.graphics.getHeight() / 2 - 230,
                            500,
                            160);
                }
            } else if (isStat == false) {
                MyGdxGame.isJoysticStatic = true;
                click.play();
                if (lang == 1) {
                    batch.draw(dynam02ENG,
                            camera.position.x - 250,
                            Gdx.graphics.getHeight() / 2 - 230,
                            500,
                            160);
                }
            }
        }

        if(backENG.isHit() || backRUS.isHit()){
            myGdxGame.setScreen(myGdxGame.menuScreen);
            click.play();
            if (lang == 1) {
                batch.draw(back02ENG,
                        camera.position.x - 250,
                        Gdx.graphics.getHeight() / 2 - 500,
                        500,
                        160);
            } else if (lang == 2) {
                batch.draw(back02RUS,
                        camera.position.x - 250,
                        Gdx.graphics.getHeight() / 2 - 500,
                        500,
                        160);
            }
        }

        if (RUS.isHit() || ENG.isHit()){
            click.play();
            if (lang == 1){
                MyGdxGame.language = 2;
                batch.draw(ENG02,
                        camera.position.x - 250,
                        Gdx.graphics.getHeight() / 2 + 100,
                        500,
                        160);
            } else if(lang == 2){
                MyGdxGame.language = 1;
                batch.draw(RUS02,
                        camera.position.x - 250,
                        Gdx.graphics.getHeight() / 2 + 100,
                        500,
                        160);
            }
        }

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

    public void initializeMenuElementsRUS(){
       // menuTitleRUS = new Texture("MenuAssets/RUS/TitleMenu.png");
       // menuTitleRUSShad = new Texture("MenuAssets/RUS/TitleMenuShad.png");
    }

    public void initializeMenuElementsENG(){
        //menuTitleENG = new Texture("MenuAssets/ENG/TitleENG.png");
        //menuTitleENGShad = new Texture("MenuAssets/ENG/TitleENGShad.png");
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
