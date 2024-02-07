package com.maxadventure.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.HashMap;

public class MenuScreen implements Screen {
    private long startMillis = 0;
    private int counter = 0;
    private boolean isStarted = false;

    private MyGdxGame myGdxGame;
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

    private Music musicM = Gdx.audio.newMusic(Gdx.files.internal("Menu.wav"));

    private Texture play01ENG, play02ENG, play01RUS, play02RUS;
    private Texture settings01ENG, settings02ENG, settings01RUS, settings02RUS;
    private Texture about01ENG, about02ENG, about01RUS, about02RUS, secBUT;
    private Button playENG, playRUS, settingsENG, settingsRUS, aboutENG, aboutRUS, secBut;
    private Sound click, huh, boom;

    private Texture menuTitleENG, menuTitleENGShad, menuTitleRUS, menuTitleRUSShad;

    public MenuScreen(MyGdxGame myGdxGame, SpriteBatch batch, OrthographicCamera camera) {
        click = Gdx.audio.newSound(Gdx.files.internal("Sounds/Click.mp3"));
        huh = Gdx.audio.newSound(Gdx.files.internal("MenuAssets/huh.mp3"));
        boom = Gdx.audio.newSound(Gdx.files.internal("MenuAssets/boom.mp3"));

        play01ENG = new Texture("MenuAssets/Buttons/Play/ENG/play01.png");
        play02ENG = new Texture("MenuAssets/Buttons/Play/ENG/play03.png");
        play01RUS = new Texture("MenuAssets/Buttons/Play/RUS/play01.png");
        play02RUS = new Texture("MenuAssets/Buttons/Play/RUS/play03.png");

        settings01ENG = new Texture("MenuAssets/Buttons/Settings/ENG/option01.png");
        settings02ENG = new Texture("MenuAssets/Buttons/Settings/ENG/option03.png");
        settings01RUS = new Texture("MenuAssets/Buttons/Settings/RUS/option01.png");
        settings02RUS = new Texture("MenuAssets/Buttons/Settings/RUS/option03.png");

        about01ENG = new Texture("MenuAssets/Buttons/About/ENG/about01.png");
        about02ENG = new Texture("MenuAssets/Buttons/About/ENG/about03.png");
        about01RUS = new Texture("MenuAssets/Buttons/About/RUS/about01.png");
        about02RUS = new Texture("MenuAssets/Buttons/About/RUS/about02.png");

        secBUT = new Texture("MenuAssets/SecBut.png");

        this.batch = batch;
        this.camera = camera;
        this.myGdxGame = myGdxGame;
        System.out.println(Gdx.graphics.getWidth());
        System.out.println(Gdx.graphics.getHeight());
        playENG = new Button(
                play01ENG,
                batch,
                (int) ((600 / 2984f) * Gdx.graphics.getWidth()),
                (int) ((200 / 1440f) * Gdx.graphics.getHeight()),
                new Vector2(
                        (int) (1192 / 2984f * Gdx.graphics.getWidth()),
                        (int) (620 / 1440f * Gdx.graphics.getHeight())
                )
        );
        playRUS = new Button(
                play01RUS,
                batch,
                (int) ((600 / 2984f) * Gdx.graphics.getWidth()),
                (int) ((200 / 1440f) * Gdx.graphics.getHeight()),
                new Vector2(
                        (int) (1192 / 2984f * Gdx.graphics.getWidth()),
                        (int) (620 / 1440f * Gdx.graphics.getHeight())
                )
        );

        settingsENG = new Button(
                settings01ENG,
                batch,
                (int) ((600 / 2984f) * Gdx.graphics.getWidth()),
                (int) ((200 / 1440f) * Gdx.graphics.getHeight()),
                new Vector2(
                        (int) (1192 / 2984f * Gdx.graphics.getWidth()),
                        (int) (420 / 1440f * Gdx.graphics.getHeight())
                )
        );
        settingsRUS = new Button(
                settings01RUS,
                batch,
                (int) ((600 / 2984f) * Gdx.graphics.getWidth()),
                (int) ((200 / 1440f) * Gdx.graphics.getHeight()),
                new Vector2(
                        (int) (1192 / 2984f * Gdx.graphics.getWidth()),
                        (int) (420 / 1440f * Gdx.graphics.getHeight())
                )
        );

        aboutENG = new Button(
                about01ENG,
                batch,
                (int) ((600 / 2984f) * Gdx.graphics.getWidth()),
                (int) ((200 / 1440f) * Gdx.graphics.getHeight()),
                new Vector2(
                        (int) (1192 / 2984f * Gdx.graphics.getWidth()),
                        (int) (220 / 1440f * Gdx.graphics.getHeight())
                )
        );
        aboutRUS = new Button(about01RUS,
                batch,
                (int) ((600 / 2984f) * Gdx.graphics.getWidth()),
                (int) ((200 / 1440f) * Gdx.graphics.getHeight()),
                new Vector2(
                        (int) (1192 / 2984f * Gdx.graphics.getWidth()),
                        (int) (220 / 1440f * Gdx.graphics.getHeight())
                ));

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

        secBut = new Button(secBUT, batch, (int) (menuTitleENG.getWidth() * 4),
                (int) (menuTitleENG.getHeight() * 4), new Vector2(
                camera.position.x - menuTitleENG.getWidth() * 2,
                Gdx.graphics.getHeight() - 400));
    }

    @Override
    public void show() {
        this.lang = MyGdxGame.language;
        isStarted = false;
        counter = 0;
        musicM.play();
    }

    @Override
    public void render(float delta) {
        if (TimeUtils.millis() - startMillis > 5000 && isStarted == true) {
            myGdxGame.setScreen(myGdxGame.memeMenuScreen);
        }
        lang = MyGdxGame.language;
        MyGdxGame.leftBottomPointCamera.set(
                (int) (camera.position.x) - MyGdxGame.WIDTH / 2,
                (int) (camera.position.y) - MyGdxGame.HEIGHT / 2
        );
        camera.position.add(3, 0, 0);
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
        batch.begin();
        chooseBackground(delta);
        if (lang == 1) {
            batch.draw(menuTitleENGShad,
                    camera.position.x - 440 / 2982f * Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight() - (1000 / 440f) / 1440f * Gdx.graphics.getHeight() * (324 / 1440f * Gdx.graphics.getHeight()),
                    880 / 2982f * Gdx.graphics.getWidth(),
                    324 / 1440f * Gdx.graphics.getHeight());

            batch.draw(menuTitleENG,
                    camera.position.x - 460 / 2982f * Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight() - (960 / 440f) / 1440f * Gdx.graphics.getHeight() * (324 / 1440f * Gdx.graphics.getHeight()),
                    880 / 2982f * Gdx.graphics.getWidth(),
                    324 / 1440f * Gdx.graphics.getHeight());

            playENG.draw();
            settingsENG.draw();
            aboutENG.draw();

            if (playENG.isHit()) {
                batch.draw(play02ENG,
                        camera.position.x - 300/2984f*Gdx.graphics.getWidth(),
                        Gdx.graphics.getHeight() / 2f - 420/1440f*Gdx.graphics.getHeight(),
                        600/2984f*Gdx.graphics.getWidth(),
                        200/1440f*Gdx.graphics.getHeight());
                click.play();
                MyGdxGame.WIDTH = MyGdxGame.SCREEN_WIDTH / (2f * 10);
                MyGdxGame.HEIGHT = MyGdxGame.SCREEN_HEIGHT / (2f * 10);
                myGdxGame.setScreen(myGdxGame.gameScreen);
                musicM.stop();
            }
            if (settingsENG.isHit()) {
                batch.draw(settings02ENG,
                        camera.position.x - 300 / 2984f * Gdx.graphics.getWidth(),
                        Gdx.graphics.getHeight() / 2f - 615 / 1440f * Gdx.graphics.getHeight(),
                        600 / 2984f * Gdx.graphics.getWidth(),
                        200 / 1440f * Gdx.graphics.getHeight());
                click.play();
                myGdxGame.setScreen(myGdxGame.settingsScreen);
            }
            if (aboutENG.isHit()) {
                batch.draw(about02ENG,
                        camera.position.x - 300 / 2984f * Gdx.graphics.getWidth(),
                        Gdx.graphics.getHeight() / 2f - 820 / 1440f * Gdx.graphics.getHeight(),
                        600 / 2984f * Gdx.graphics.getWidth(),
                        200 / 1440f * Gdx.graphics.getHeight());

                Gdx.net.openURI("https://github.com/Pasha883/SamsungGameDevProject");
                click.play();
            }
        } else if (lang == 2) {
            batch.draw(menuTitleRUSShad,
                    camera.position.x - 440 / 2982f * Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight() - (1000 / 440f) / 1440f * Gdx.graphics.getHeight() * (324 / 1440f * Gdx.graphics.getHeight()),
                    880 / 2982f * Gdx.graphics.getWidth(),
                    324 / 1440f * Gdx.graphics.getHeight());

            batch.draw(menuTitleRUS,
                    camera.position.x - 460 / 2982f * Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight() - (960 / 440f) / 1440f * Gdx.graphics.getHeight() * (324 / 1440f * Gdx.graphics.getHeight()),
                    880 / 2982f * Gdx.graphics.getWidth(),
                    324 / 1440f * Gdx.graphics.getHeight());


            playRUS.draw();
            settingsRUS.draw();
            aboutRUS.draw();

            if (playRUS.isHit()) {
                batch.draw(play02RUS,
                        camera.position.x - 300 / 2984f * Gdx.graphics.getWidth(),
                        Gdx.graphics.getHeight() / 2f - 420 / 1440f * Gdx.graphics.getHeight(),
                        600 / 2984f * Gdx.graphics.getWidth(),
                        200 / 1440f * Gdx.graphics.getHeight());
                click.play();
                MyGdxGame.WIDTH = 2316 / (2f * 10);
                MyGdxGame.HEIGHT = 1080 / (2f * 10);
                myGdxGame.setScreen(myGdxGame.gameScreen);
                musicM.stop();
            }
            if (settingsRUS.isHit()) {
                batch.draw(settings02RUS,
                        camera.position.x - 300 / 2984f * Gdx.graphics.getWidth(),
                        Gdx.graphics.getHeight() / 2f - 615 / 1440f * Gdx.graphics.getHeight(),
                        600 / 2984f * Gdx.graphics.getWidth(),
                        200 / 1440f * Gdx.graphics.getHeight());
                click.play();
                myGdxGame.setScreen(myGdxGame.settingsScreen);
            }
            if (aboutRUS.isHit()) {
                batch.draw(about02RUS,
                        camera.position.x - 300 / 2984f * Gdx.graphics.getWidth(),
                        Gdx.graphics.getHeight() / 2f - 820 / 1440f * Gdx.graphics.getHeight(),
                        600 / 2984f * Gdx.graphics.getWidth(),
                        200 / 1440f * Gdx.graphics.getHeight());
                click.play();
                Gdx.net.openURI("https://github.com/Pasha883/SamsungGameDevProject");


            }
        }
        if (secBut.isHit()) {
            if (counter < 5) {
                huh.play();
                counter += 1;
            }
            if (counter == 4) {
                boom.play();
                isStarted = true;
                startMillis = TimeUtils.millis();
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
        click.dispose();
        huh.dispose();
        boom.dispose();

        play01ENG.dispose();
        play02ENG.dispose();
        play01RUS.dispose();
        play02RUS.dispose();

        settings01ENG.dispose();
        settings02ENG.dispose();
        settings01RUS.dispose();
        settings02RUS.dispose();

        about01ENG.dispose();
        about02ENG.dispose();
        about01RUS.dispose();
        about02RUS.dispose();

        secBUT.dispose();

        menuTitleRUS.dispose();
        menuTitleRUSShad.dispose();

        menuTitleENG.dispose();
        menuTitleENGShad.dispose();

    }

    public long findTime() {
        long time = TimeUtils.millis();
        long seconds = (long) (time / 1000);
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long now = hours % 24 + 3;
        if (now >= 24) {
            now = now % 24;
        }
        return now;
    }

    public void chooseBackground(float delta) {
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

    public void initializeMenuElementsRUS() {
        menuTitleRUS = new Texture("MenuAssets/RUS/TitleMenu.png");
        menuTitleRUSShad = new Texture("MenuAssets/RUS/TitleMenuShad.png");
    }

    public void initializeMenuElementsENG() {
        menuTitleENG = new Texture("MenuAssets/ENG/TitleENG.png");
        menuTitleENGShad = new Texture("MenuAssets/ENG/TitleENGShad.png");
    }

    public void initializateBackground1() {
        B8L4 = new Texture("MenuBackground1/1.png");
        B8L3 = new Texture("MenuBackground1/2.png");
        B8L2 = new Texture("MenuBackground1/3.png");
        B8L1 = new Texture("MenuBackground1/4.png");


        parallaxBg1.put("B8L1", new BackgroundCircle(B8L1, batch, camera, 0.1f));
        parallaxBg1.put("B8L2", new BackgroundCircle(B8L2, batch, camera, 0.4f));
        parallaxBg1.put("B8L3", new BackgroundCircle(B8L3, batch, camera, -0.13f));
        parallaxBg1.put("B8L4", new BackgroundCircle(B8L4, batch, camera, 0.07f));
    }

    public void initializateBackground2() {
        B7L4 = new Texture("MenuBackground2/1.png");
        B7L3 = new Texture("MenuBackground2/2.png");
        B7L2 = new Texture("MenuBackground2/3.png");
        B7L1 = new Texture("MenuBackground2/4.png");


        parallaxBg2.put("B7L1", new BackgroundCircle(B7L1, batch, camera, 0.1f));
        parallaxBg2.put("B7L2", new BackgroundCircle(B7L2, batch, camera, 0.4f));
        parallaxBg2.put("B7L3", new BackgroundCircle(B7L3, batch, camera, -0.13f));
        parallaxBg2.put("B7L4", new BackgroundCircle(B7L4, batch, camera, 0.07f));
    }

    public void initializateBackground3() {
        B6L4 = new Texture("MenuBackground3/1.png");
        B6L3 = new Texture("MenuBackground3/2.png");
        B6L2 = new Texture("MenuBackground3/3.png");
        B6L1 = new Texture("MenuBackground3/4.png");


        parallaxBg3.put("B6L1", new BackgroundCircle(B6L1, batch, camera, 0.1f));
        parallaxBg3.put("B6L2", new BackgroundCircle(B6L2, batch, camera, 0.4f));
        parallaxBg3.put("B6L3", new BackgroundCircle(B6L3, batch, camera, -0.13f));
        parallaxBg3.put("B6L4", new BackgroundCircle(B6L4, batch, camera, 0.07f));
    }

    public void initializateBackground4() {
        B5L4 = new Texture("MenuBackground4/1.png");
        B5L3 = new Texture("MenuBackground4/2.png");
        B5L2 = new Texture("MenuBackground4/3.png");
        B5L1 = new Texture("MenuBackground4/4.png");


        parallaxBg4.put("B5L1", new BackgroundCircle(B5L1, batch, camera, 0.1f));
        parallaxBg4.put("B5L2", new BackgroundCircle(B5L2, batch, camera, 0.4f));
        parallaxBg4.put("B5L3", new BackgroundCircle(B5L3, batch, camera, -0.13f));
        parallaxBg4.put("B5L4", new BackgroundCircle(B5L4, batch, camera, 0.07f));
    }

    public void initializateBackground5() {
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

    public void initializateBackground6() {
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

    public void initializateBackground7() {
        B2L4 = new Texture("MenuBackground7/1.png");
        B2L3 = new Texture("MenuBackground7/2.png");
        B2L2 = new Texture("MenuBackground7/3.png");
        B2L1 = new Texture("MenuBackground7/4.png");


        parallaxBg7.put("B2L1", new BackgroundCircle(B2L1, batch, camera, 0.1f));
        parallaxBg7.put("B2L2", new BackgroundCircle(B2L2, batch, camera, 0.4f));
        parallaxBg7.put("B2L3", new BackgroundCircle(B2L3, batch, camera, -0.13f));
        parallaxBg7.put("B2L4", new BackgroundCircle(B2L4, batch, camera, 0.07f));
    }

    public void initializateBackground8() {
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

    public void renderBackground1(float delta) {
        for (BackgroundCircle bgCircle : parallaxBg1.values()) {
            bgCircle.render(delta);
        }
    }

    public void renderBackground2(float delta) {
        for (BackgroundCircle bgCircle : parallaxBg2.values()) {
            bgCircle.render(delta);
        }
    }

    public void renderBackground3(float delta) {
        for (BackgroundCircle bgCircle : parallaxBg3.values()) {
            bgCircle.render(delta);
        }
    }

    public void renderBackground4(float delta) {
        for (BackgroundCircle bgCircle : parallaxBg4.values()) {
            bgCircle.render(delta);
        }
    }

    public void renderBackground5(float delta) {
        for (BackgroundCircle bgCircle : parallaxBg5.values()) {
            bgCircle.render(delta);
        }
    }

    public void renderBackground6(float delta) {
        for (BackgroundCircle bgCircle : parallaxBg6.values()) {
            bgCircle.render(delta);
        }
    }

    public void renderBackground7(float delta) {
        for (BackgroundCircle bgCircle : parallaxBg7.values()) {
            bgCircle.render(delta);
        }
    }

    public void renderBackground8(float delta) {
        for (BackgroundCircle bgCircle : parallaxBg8.values()) {
            bgCircle.render(delta);
        }
    }
}