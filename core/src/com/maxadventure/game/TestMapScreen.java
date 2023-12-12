package com.maxadventure.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;


import java.util.Date;
import java.util.HashMap;

public class TestMapScreen implements Screen {
    private MyGdxGame myGdxGame;
    private final SpriteBatch batch;
    private OrthographicCamera camera;
    private TmxMapLoader tmxMapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private Box2DDebugRenderer box2DDebugRenderer;
    private World world;
    private float unitscale = 7;
    Player player;
    private Joystick joystick;
    private Texture first,second,third;
    private HashMap<String, BackgroundCircle> parallaxBg = new HashMap<>();



    public TestMapScreen(MyGdxGame myGdxGame, SpriteBatch batch, OrthographicCamera camera) {
        this.myGdxGame = myGdxGame;
        this.batch = batch;
        this.camera = camera;
        joystick = new Joystick(batch,camera, new Texture("bgJoystick.png"),
                new Texture("fgStick.png"), 400, 100);
        tmxMapLoader = new TmxMapLoader();
        box2DDebugRenderer = new Box2DDebugRenderer();
        tiledMap = tmxMapLoader.load("jo.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap, unitscale);

        world = new World(new Vector2(0, -98f), true);
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        for (MapObject object : tiledMap.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.x + rect.getWidth() / 2) * unitscale, (rect.y + rect.getHeight() / 2) * unitscale);

            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth() / 2 * unitscale, rect.getHeight() / 2 * unitscale);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);

        }
        player = new Player(world);
        Date date1= new Date();

        player = new Player(world);
        if (date1.getHours()>18){
            first = new Texture("bg/SET1/png/SET1_bakcground_night1.png");
            second = new Texture("bg/SET1/png/SET1_bakcground_night2.png");
            third = new Texture("bg/SET1/png/SET1_bakcground_night3.png");
        } else {
            first = new Texture("bg/SET1/png/SET1_bakcground_day1.png");
            second = new Texture("bg/SET1/png/SET1_bakcground_day2.png");
            third = new Texture("bg/SET1/png/SET1_bakcground_day3.png");
        }


        parallaxBg.put("firstBg", new BackgroundCircle(first, batch, camera, -0.2f));
        parallaxBg.put("secondBg", new BackgroundCircle(second, batch, camera, -0.15f));
        parallaxBg.put("thirdBg", new BackgroundCircle(third, batch, camera, -0.3f));
    }

    @Override
    public void show() {

    }
    public void renderBackground(float delta) {
        for (BackgroundCircle bgCircle : parallaxBg.values()) bgCircle.renderWithY(delta,camera.position.y);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,0);
        camera.position.x += joystick.getResult().x * 10;
        camera.position.y += joystick.getResult().y * 10;

        if(joystick.getResult().y >= 0.75f){
            player.body.applyForceToCenter(new Vector2(0, 30000), true);
        }
        camera.update();


        batch.begin();
        renderBackground(delta);
        joystick.render(delta);
        batch.setProjectionMatrix(camera.combined);
        batch.end();
        renderer.setView(camera);
        renderer.render();
        box2DDebugRenderer.render(world, camera.combined);

        world.step(1/60f, 6, 2);
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
}
