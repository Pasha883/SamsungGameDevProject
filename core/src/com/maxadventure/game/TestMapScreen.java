//package com.mygdx.game;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.maps.MapObject;
//import com.badlogic.gdx.maps.objects.RectangleMapObject;
//import com.badlogic.gdx.maps.tiled.TiledMap;
//import com.badlogic.gdx.maps.tiled.TmxMapLoader;
//import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.Body;
//import com.badlogic.gdx.physics.box2d.BodyDef;
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
//import com.badlogic.gdx.physics.box2d.FixtureDef;
//import com.badlogic.gdx.physics.box2d.PolygonShape;
//import com.badlogic.gdx.physics.box2d.World;
//
//public class TestMapScreen implements Screen {
//    private MyGdxGame myGdxGame;
//    private final SpriteBatch batch;
//    private OrthographicCamera camera;
//    private TmxMapLoader tmxMapLoader;
//    private TiledMap tiledMap;
//    private OrthogonalTiledMapRenderer renderer;
//    private Box2DDebugRenderer box2DDebugRenderer;
//    private World world;
//    private float unitscale = 5;
//    Player player;
//
//
//    public TestMapScreen(MyGdxGame myGdxGame, SpriteBatch batch, OrthographicCamera camera) {
//        this.myGdxGame = myGdxGame;
//        this.batch = batch;
//        this.camera = camera;
//        tmxMapLoader = new TmxMapLoader();
//        box2DDebugRenderer = new Box2DDebugRenderer();
//        tiledMap = tmxMapLoader.load("test.tmx");
//        renderer = new OrthogonalTiledMapRenderer(tiledMap, unitscale);
//
//        world = new World(new Vector2(0, -9.8f), true);
//        BodyDef bodyDef = new BodyDef();
//        PolygonShape shape = new PolygonShape();
//        FixtureDef fixtureDef = new FixtureDef();
//        Body body;
//
//        for (MapObject object : tiledMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//
//            bodyDef.type = BodyDef.BodyType.StaticBody;
//            bodyDef.position.set((rect.x + rect.getWidth() / 2) * unitscale, (rect.y + rect.getHeight()/2) * unitscale);
//
//            body = world.createBody(bodyDef);
//            shape.setAsBox(rect.getWidth() / 2 * unitscale, rect.getHeight() / 2 * unitscale);
//            fixtureDef.shape = shape;
//            body.createFixture(fixtureDef);
//
//        }
//        player = new Player(world);
//    }
//
//    @Override
//    public void show() {
//
//    }
//
//    @Override
//    public void render(float delta) {
//        world.step(1/60f, 6, 2);
//        if (Gdx.input.isTouched() && Gdx.input.getX() > MyGdxGame.WIDTH / 2) {
//            camera.position.x += 5f;
//        }
//        if (Gdx.input.isTouched() && Gdx.input.getX() < MyGdxGame.WIDTH / 2) {
//            camera.position.x += -5f;
//        }
//        camera.update();
//        renderer.setView(camera);
//        renderer.render();
//        box2DDebugRenderer.render(world, camera.combined);
//        batch.begin();
//        batch.setProjectionMatrix(camera.combined);
//        batch.end();
//
//    }
//
//    @Override
//    public void resize(int width, int height) {
//
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//
//    }
//}
