package com.maxadventure.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

//import com.maxadventure.game.Button;

import java.time.Duration;
import java.time.Instant;

import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GameScreen implements Screen {
    long startMills;
    private static final float unitScale = 0.225f;
    private final SpriteBatch batch;
    private final OrthographicCamera camera, hudCamera;
    private FitViewport gameViewport;
    private FitViewport hudViewport;
    private Stage hudStage;
    private TmxMapLoader tmxMapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Box2DDebugRenderer b2dr;
    private World world;
    private Player player;
    private Vector2 touch, worldTouch;
    private Joystick joystick;

    private Texture sky, rock1, rock2, clouds1, clouds2, clouds3, clouds4;
    private Texture first, second, third;
    private final HashMap<String, BackgroundCircle> parallaxBg = new HashMap<>();
    private Music musicG = Gdx.audio.newMusic(Gdx.files.internal("Game.wav"));

    private long startTime = TimeUtils.millis(), startTouchTime = TimeUtils.millis();
    Texture deleteLater;

    Sound j, s, c;

    List<Body> ground = new ArrayList<>();
    List<Body> coins = new ArrayList<>();
    HashMap<Body, Rectangle> coinsRect = new HashMap<>();
    List<Body> bodyForDelete = new ArrayList<>();
    private TiledMapTileLayer coinLayer;
    private Enemy enemy;
    private boolean canMove = true;
    Sound bruh, huh;

    int direction = -1;
    private float time = 0;
    private MyGdxGame myGdxGame;

    private com.maxadventure.game.Button leftStaticB, leftDinamicB;

    private boolean buttonPressedLeft = false, buttonPressedRight = false;

    private Texture shkala, greenRect;
    private int coinTakes = 0;
    private int clickCount = 0;
    private long lastClickTime = 0;
    private long doubleClickTimeThreshold = 50;

    private float targetSpeed = 200f;

    private long clickTimeDouble = 0;
    private int clicks = 0;

    private boolean speedTwo = false;


    public GameScreen(SpriteBatch batch, OrthographicCamera camera, OrthographicCamera hudCamera, MyGdxGame myGdxGame) {
        this.batch = batch;
        this.camera = camera;
        this.hudCamera = hudCamera;
        this.myGdxGame = myGdxGame;

        bruh = Gdx.audio.newSound(Gdx.files.internal("TMS/bruh.mp3"));
        huh = Gdx.audio.newSound(Gdx.files.internal("TMS/huh.mp3"));

        j = Gdx.audio.newSound(Gdx.files.internal("J.mp3"));
        s = Gdx.audio.newSound(Gdx.files.internal("S.mp3"));
        c = Gdx.audio.newSound(Gdx.files.internal("Coin.mp3"));

//        leftDinamicB = new com.maxadventure.game.Button(leftStatic, batch, 10, 10, new Vector2(7, 3));

        gameViewport = new FitViewport(MyGdxGame.SCREEN_WIDTH / (2f * 10), MyGdxGame.SCREEN_HEIGHT / (2f * 10), camera);
        hudViewport = new FitViewport(MyGdxGame.SCREEN_WIDTH / (2f * 10), MyGdxGame.SCREEN_HEIGHT / (2f * 10), hudCamera);
        hudStage = new Stage(hudViewport, batch);

        Drawable active_button1 = new TextureRegionDrawable(new Texture("buttons/up.png"));
        Drawable none_active_button1 = new TextureRegionDrawable(new Texture("buttons/upS.png"));

        Drawable active_button2 = new TextureRegionDrawable(new Texture("buttons/attakD.png"));
        Drawable none_active_button2 = new TextureRegionDrawable(new Texture("buttons/attackS.png"));

        Drawable active_button3 = new TextureRegionDrawable(new Texture("buttons/leftDinamic.png"));
        Drawable none_active_button3 = new TextureRegionDrawable(new Texture("buttons/left.png"));

        Drawable active_button4 = new TextureRegionDrawable(new Texture("buttons/rightDinamic.png"));
        Drawable none_active_button4 = new TextureRegionDrawable(new Texture("buttons/right.png"));

        shkala = new Texture("progressbar.png");
        greenRect = new Texture("greenRect.png");


        deleteLater = new Texture("badlogic.jpg");
        tmxMapLoader = new TmxMapLoader();
        map = tmxMapLoader.load("TMS/jo.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, unitScale, batch);


        coinLayer = (TiledMapTileLayer) map.getLayers().get("coin");

        world = new World(new Vector2(0, -3600), true);
        //enemy = new Enemy(world, 5, 10);


        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if (contact.getFixtureB().getBody() == player.body && coins.contains(contact.getFixtureA().getBody()))
                    if (world != null) {
                        coins.remove(contact.getFixtureA().getBody());
                        bodyForDelete.add(contact.getFixtureA().getBody());
                        c.play();
                        coinTakes++;
                    }
                if (contact.getFixtureB().getBody() == player.body && contact.getFixtureA().getBody() == enemy.getBody()) {
                    enemy.handleCollision(player);
                    if (!player.isInvease())
                        player.startInvease();

                } else if (contact.getFixtureA().getBody() == player.body && contact.getFixtureB().getBody() == enemy.getBody()) {
                    enemy.handleCollision(player);
//                    System.out.println((TimeUtils.millis() - startTime) + "сек");
                    if ((TimeUtils.millis() - startTouchTime) / 1000f > 1 && enemy.isAlive) {
//                        System.out.println("время пошло");
                        startTime = TimeUtils.millis();
                        startTouchTime = TimeUtils.millis();
//                        canMove=!canMove;
                    }
//                    System.out.println("elsfme;wf");
                    if (!player.isInvease())
                        player.startInvease();
                }
                if (contact.getFixtureB().getBody() == player.body && ground.contains(contact.getFixtureA().getBody()))
                    player.setJumpCounter(0);
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();

        b2dr = new Box2DDebugRenderer();

        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Body body;
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.x + rect.getWidth() / 2) * unitScale,
                    (rect.y + rect.getHeight() / 2) * unitScale);

            body = world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth() / 2 * unitScale, rect.getHeight() / 2 * unitScale);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);

            ground.add(body);
        }

        initCoinsBody(bodyDef, shape, fixtureDef);

        startConfig();
        touch = new Vector2(
                Gdx.input.getX() - gameViewport.getScreenX(),
                gameViewport.getScreenHeight() - Gdx.input.getY() + gameViewport.getScreenY()
        );

        joystick = new Joystick(hudViewport, hudCamera, new Texture("bgJoystick.png"),
                new Texture("fgStick.png"), 20, 6);

        Button button = new Button(none_active_button1, active_button1);
        button.setPosition(90 / 2400f * Gdx.graphics.getWidth(), 5 / 1080f * Gdx.graphics.getHeight());

        Button button2 = new Button(none_active_button2, active_button2);
        button2.setPosition(105 / 2400f * Gdx.graphics.getWidth(), 20 / 1080f * Gdx.graphics.getHeight());

        Button button3 = new Button(active_button3, none_active_button3);
        button3.setPosition(7, 5);

        Button button4 = new Button(active_button4, none_active_button4);
        button4.setPosition(25, 5);

        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int jumpCounter = player.getJumpCounter();

                if (jumpCounter < 2) {
                    player.body.applyLinearImpulse(new Vector2(0, 700000),
                            player.body.getPosition(), true);
                    player.setJumpCounter(jumpCounter + 1);
                    j.play();
                }
            }
        });

        button2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!player.getAttack()) {
                    Body body = enemy.getBody();
                    player.setAttack(true);
                    s.play();
                    player.setStartAttack(TimeUtils.millis());
                    if (body.getPosition().x < player.body.getPosition().x + 20 &&
                            player.body.getPosition().x < body.getPosition().x && player.getDirect() == 1) {
                        huh.play();
                        enemy.isAlive = false;
                    } else if (body.getPosition().x > player.body.getPosition().x - 20 &&
                            player.body.getPosition().x > body.getPosition().x && player.getDirect() == -1) {
                        huh.play();
                        enemy.isAlive = false;
                    }
                }
            }
        });


        button3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonPressedLeft = true;
                speedTwo = TimeUtils.millis() - clickTimeDouble >= 1000;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                buttonPressedLeft = false;
                speedTwo = false;
                clickTimeDouble = TimeUtils.millis();
            }
        });

        button4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonPressedRight = true;
                return true; // Возвращаем true, чтобы продолжить получать события touchUp
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                buttonPressedRight = false;
            }
        });


        button.setWidth(11 / 2400f * Gdx.graphics.getWidth());
        button.setHeight(11 / 2400f * Gdx.graphics.getWidth());
//        button.setWidth(10);
//        button.setHeight(10);


        button2.setWidth(11 / 2400f * Gdx.graphics.getWidth());
        button2.setHeight(11 / 2400f * Gdx.graphics.getWidth());
//        button2.setWidth(10);
//        button2.setHeight(10);

        button3.setWidth(10);
        button3.setHeight(10);

        button4.setWidth(10);
        button4.setHeight(10);

        System.out.println("управление джойстиком " + MyGdxGame.isJoysticMode);


        hudStage.addActor(button);
        hudStage.addActor(button2);


        initBackground();
        enemy = new Enemy(batch, this, world, player.body.getPosition().x - 40, player.body.getPosition().y);
//        enemy = new Enemy(this, world, player.body.getPosition().x - 100, player.body.getPosition().y + 60);
//        enemy = new Enemy(this, world, player.body.getPosition().x + 60, player.body.getPosition().y - 20);

        MyGdxGame.inputMultiplexer.addProcessor(hudStage);
    }

    public boolean getCanMove() {
        return (TimeUtils.millis() - startTime) / 1000f > 3;
    }

    private void initCoinsBody(BodyDef bodyDef, PolygonShape shape, FixtureDef fixtureDef) {
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Body body;
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.x + rect.getWidth() / 2) * unitScale,
                    (rect.y + rect.getHeight() / 2) * unitScale);

            body = world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth() / 2 * unitScale, rect.getHeight() / 2 * unitScale);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);

            coins.add(body);
            coinsRect.put(body, rect);
        }
    }

    private void startConfig() {
        Vector2 startPos = new Vector2(640, 400);
        player = new Player(this, world, batch, 50, 50);
        player.body.setTransform(startPos, player.body.getAngle());
        camera.position.set(player.body.getPosition().x, player.body.getPosition().y, 0);
        System.out.println("Player: " + player.body);
    }

    private void correctCamera() {
        float cameraSpeed = 0.8f;
        float cameraSpeedY = 1f;
        float xDirection = player.body.getPosition().x - camera.position.x;
        float yDirection = player.body.getPosition().y - camera.position.y;
        if (Math.abs(xDirection) > MyGdxGame.WIDTH / 2 * 0.4f) {
            if (xDirection > 0)
                camera.position.add(cameraSpeed, 0, 0);
            else
                camera.position.add(-cameraSpeed, 0, 0);
        }
        if (Math.abs(yDirection) > MyGdxGame.HEIGHT / 2 * 0.4f) {
            if (yDirection > 0)
                camera.position.add(0, cameraSpeed, 0);
            else
                camera.position.add(0, -cameraSpeedY, 0);
        }
    }

    public void renderBackground(float delta) {
        for (BackgroundCircle bgCircle : parallaxBg.values()) {
            bgCircle.render(delta);
        }
    }

    private void initBackground() {
        Date date1 = new Date();

        if (date1.getHours() > 18) {
            first = new Texture("bg/SET1/png/SET1_bakcground_night1.png");
            second = new Texture("bg/SET1/png/SET1_bakcground_night2.png");
            third = new Texture("bg/SET1/png/SET1_bakcground_night3.png");
        } else {
            first = new Texture("bg/SET1/png/SET1_bakcground_day1.png");
            second = new Texture("bg/SET1/png/SET1_bakcground_day2.png");
            third = new Texture("bg/SET1/png/SET1_bakcground_day3.png");
        }

        parallaxBg.put("thirdBg", new BackgroundCircle(third, batch, camera, -0.3f));
        parallaxBg.put("secondBg", new BackgroundCircle(second, batch, camera, -0.15f));
        parallaxBg.put("firstBg", new BackgroundCircle(first, batch, camera, -0.2f));
    }

    @Override
    public void show() {
        musicG.play();
        startMills = TimeUtils.millis();
        initBackground();

        Drawable active_button3 = new TextureRegionDrawable(new Texture("buttons/leftDinamic.png"));
        Drawable none_active_button3 = new TextureRegionDrawable(new Texture("buttons/left.png"));

        Drawable active_button4 = new TextureRegionDrawable(new Texture("buttons/rightDinamic.png"));
        Drawable none_active_button4 = new TextureRegionDrawable(new Texture("buttons/right.png"));

        Button button3 = new Button(active_button3, none_active_button3);
        button3.setPosition(7 / 2400f * Gdx.graphics.getWidth(), 5 / 1080f * Gdx.graphics.getHeight());

        Button button4 = new Button(active_button4, none_active_button4);
        button4.setPosition(25 / 2400f * Gdx.graphics.getWidth(), 5 / 1080f * Gdx.graphics.getHeight());

        button3.setWidth(10 / 2400f * Gdx.graphics.getWidth());
        button3.setHeight(10 / 2400f * Gdx.graphics.getWidth());

        button4.setWidth(10 / 2400f * Gdx.graphics.getWidth());
        button4.setHeight(10 / 2400f * Gdx.graphics.getWidth());

        button3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonPressedLeft = true;
                return true; // Возвращаем true, чтобы продолжить получать события touchUp
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                buttonPressedLeft = false;
            }
        });

        button4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buttonPressedRight = true;
                return true; // Возвращаем true, чтобы продолжить получать события touchUp
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                buttonPressedRight = false;
            }
        });

        if (!MyGdxGame.isJoysticMode) {
            hudStage.addActor(button3);
            hudStage.addActor(button4);
        } else if (MyGdxGame.isJoysticMode) {
            hudStage.addActor(joystick);
        }
        //resize((int) MyGdxGame.WIDTH, (int) MyGdxGame.HEIGHT);
    }

    @Override
    public void render(float delta) {


        ScreenUtils.clear(1, 1, 1, 1);
        time += delta;
        updateTouch();


//        System.out.println(((TimeUtils.millis() - startTime) / 1000f) + "секунд с удара прошло");
        if ((TimeUtils.millis() - startTime) / 1000f > 3) {
            // Желаемая скорость игрока

//            if (speedTwo){
//                targetSpeed = 250f;
//            }
//            else targetSpeed = 100f;

            System.out.println("двойной клик " + targetSpeed);

// Поддерживаем постоянную скорость для движения влево
            float maxSpeed = 130; // Максимальная скорость игрока

            if (buttonPressedLeft || (joystick.getResult().x < -0.75f && joystick.getResult().y < 0.5f)) {
                if (player.body.getLinearVelocity().x > -maxSpeed) {
                    player.body.applyForceToCenter(new Vector2(-3000, 0), true);
                }
                player.setDirect(-1);
            } else if (buttonPressedRight || (joystick.getResult().x > 0.75f && joystick.getResult().y < 0.5f)) {
                if (player.body.getLinearVelocity().x < maxSpeed) {
                    player.body.applyForceToCenter(new Vector2(3000, 0), true);
                }
                player.setDirect(1);
            }


//            startTime = TimeUtils.millis();

//            if (joystick.getResult().x > 0.75f && joystick.getResult().y < 0.5f) {
//                player.body.applyForceToCenter(new Vector2(3000, 0), true);
//                player.setDirect(1);
//            }
//            if (joystick.getResult().x < -0.75f && joystick.getResult().y < 0.5f) {
//                player.body.applyForceToCenter(new Vector2(-3000, 0), true);
//                player.setDirect(-1);
//            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                int jumpCounter = player.getJumpCounter();

                if (jumpCounter < 2) {
                    player.body.applyLinearImpulse(new Vector2(0, 7000),
                            player.body.getPosition(), true);
                    player.setJumpCounter(jumpCounter + 1);
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                player.body.applyForceToCenter(new Vector2(3000, 0), true);
                player.setDirect(1);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                player.body.applyForceToCenter(new Vector2(-3000, 0), true);
                player.setDirect(-1);
            }


        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.L) && !player.getAttack()) {
            Body body = enemy.getBody();
            player.setAttack(true);
            player.setStartAttack(TimeUtils.millis());
            if (body.getPosition().x < player.body.getPosition().x + 20 &&
                    player.body.getPosition().x < body.getPosition().x && player.getDirect() == 1) {
                huh.play();
                enemy.isAlive = false;
            } else if (body.getPosition().x > player.body.getPosition().x - 20 &&
                    player.body.getPosition().x > body.getPosition().x && player.getDirect() == -1) {
                huh.play();
                enemy.isAlive = false;
            }
        }


        moveCamera();
        if (Gdx.input.isTouched()) {
//            System.out.println(
//                    (touch.x) + ", " +
//                    (touch.y)
//            );
            System.out.println(
                    "! " + worldTouch.x + ", " + worldTouch.y
            );
        }

//        camera.position.add(
//                joystick.getResult().x/3f,
//                joystick.getResult().y/3f,
//                0
//        );
        correctCamera();

        //Обновление камеры
        camera.update();
        hudCamera.update();

        //Для отображения объектов через batch.begin() batch.end()
        batch.setProjectionMatrix(camera.combined);

        enemy.update(delta, player);
        batch.begin();
        renderBackground(delta);
        batch.end();

        //Отображение карты
        renderer.setView(camera);
        renderer.render();

        batch.begin();
        enemy.render(delta);
        player.render(delta);
        batch.end();

        batch.begin();
        batch.draw(greenRect, camera.position.x - 19.73f, camera.position.y + 20 / 1080f * Gdx.graphics.getHeight(),
                (coinTakes / 69f * 39.73f) / 2400f * Gdx.graphics.getWidth(),
                3 / 1080f * Gdx.graphics.getHeight());
        batch.draw(shkala, camera.position.x - 20,
                camera.position.y + 20 / 1080f * Gdx.graphics.getHeight(),
                40 / 2400f * Gdx.graphics.getWidth(), 3 / 1080f * Gdx.graphics.getHeight());
        batch.end();

        //Отвечает за отрисовку границ rectangle
        //b2dr.render(world, camera.combined);

        hudStage.act(delta);
        hudStage.draw();

        deleteBodies();
        //Физическая симуляция мира
        world.step(0.006f, 6, 2);

//        if (enemy.isAlive == false){
//            myGdxGame.resultScreen.setFinalTime(time);
//            myGdxGame.setScreen(myGdxGame.resultScreen);
//        }
//
//        if (Gdx.input.justTouched()){
//            enemy.isAlive = false;
//        }

    }

    private void deleteBodies() {
        for (Body body : bodyForDelete) {
            if (!world.isLocked()) {
                world.destroyBody(body);

                int mapHeightInTiles = map.getProperties().get("height", Integer.class);
                int tilePixelHeight = map.getProperties().get("tileheight", Integer.class);
                int mapHeightInPixels = mapHeightInTiles * tilePixelHeight;

                Rectangle rect = coinsRect.get(body);
                int tileX = (int) (rect.x) / tilePixelHeight;
//                int tileY = (int) (Math.abs(tilePixelHeight - rect.y));
                int tileY = (int) (rect.y) / tilePixelHeight;

                TiledMapTileLayer.Cell cell = coinLayer.getCell(tileX, tileY);

                if (cell != null) {
                    coinLayer.setCell(tileX, tileY, null);
                } else {
                    System.out.println(tileX + " and " + tileY);
                    System.out.println("Не та ячейка!");
                }
            }
        }
        bodyForDelete.clear();
    }

    private void moveCamera() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            camera.position.add(1, 0, 0);
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
        hudViewport.update(width, height, true);
//		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        MyGdxGame.SCREEN_WIDTH = gameViewport.getScreenWidth();
        MyGdxGame.SCREEN_HEIGHT = gameViewport.getScreenHeight();
        camera.position.set(player.body.getPosition().x, player.body.getPosition().y, 0);
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
        bruh.dispose();
        huh.dispose();

        j.dispose();
        s.dispose();
        c.dispose();

        deleteLater.dispose();
        map.dispose();
        renderer.dispose();
    }

    private void updateTouch() {
        touch.set(
                Gdx.input.getX() - gameViewport.getScreenX(),
                gameViewport.getScreenHeight() - Gdx.input.getY() + gameViewport.getScreenY()
        );
        worldTouch = gameViewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
    }
}