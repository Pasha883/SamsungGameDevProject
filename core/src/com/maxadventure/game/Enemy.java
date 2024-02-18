package com.maxadventure.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Random;

public class Enemy {
    Sound bruh, huh, q;
    private boolean playerContact = false;
    private World world;
    private Body body;
    private Texture texture, texture2;
    private TextureRegion textureRegion;

    private GameScreen gameScreen;

    public boolean isAlive = true;

    private SpriteBatch batch;
    private HashMap<String, Texture> bigTexturesForAnimation = new HashMap<>();
    private HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();

    private float startAnimTime = 0;
    private float time = 0;
    Vector2 direction = new Vector2();
    String curAnim = "ydar", lastAnim = "ydar";


    public Enemy(SpriteBatch batch,GameScreen gameScreen, World world, float x, float y) {
        this.world = world;
        this.gameScreen = gameScreen;
        this.batch=batch;

        bruh = Gdx.audio.newSound(Gdx.files.internal("TMS/bruh.mp3"));
        huh = Gdx.audio.newSound(Gdx.files.internal("TMS/huh.mp3"));
        q = Gdx.audio.newSound(Gdx.files.internal("Q.mp3"));

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);


        PolygonShape shape = new PolygonShape();

        shape.setAsBox(5f, 5f);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.01f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.5f;
        body.createFixture(fixtureDef);


        shape.dispose();

        addAnimation("ydar", "TMS/bg/players/enemy/Wisp.png",
                32, 32, .1f, 1, 10, 0,
                Animation.PlayMode.LOOP);
        addAnimation("rip", "TMS/bg/players/enemy/wisprip.png",
                32, 32, .1f, 1, 10, 0,
                Animation.PlayMode.LOOP);


        texture = new Texture(Gdx.files.internal("pin.png"));
        texture2 = new Texture(Gdx.files.internal("pinkill.png"));
        textureRegion = new TextureRegion(texture);

    }

    private void addAnimation(String name, String path,
                              int tileWidth, int tileHeight, float frameDuration,
                              int rowCount, int columnCount, int emptyCount,
                              Animation.PlayMode playMode) {
        Array<TextureRegion> animationFramesIdle = new Array<>();
        Texture animMap = new Texture(Gdx.files.internal(path));
        bigTexturesForAnimation.put(
                name,
                animMap
        );
        TextureRegion[][] texturesIdle = TextureRegion.split(animMap, tileWidth, tileHeight);
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) animationFramesIdle.add(texturesIdle[i][j]);
        }
        for(int i = 0; i < emptyCount; i++){
            animationFramesIdle.removeIndex(animationFramesIdle.size-1);
        }
        animations.put(
                name,
                new Animation<TextureRegion>(frameDuration, animationFramesIdle, playMode)
        );
    }

    public void resetAnimationTimer(){
        startAnimTime = time;
    }

    private Vector2 calculateDirection() { // Немножко говнокод, но пока норм!
        direction.set(
                getZnak(body.getLinearVelocity().x),
                getZnak(body.getLinearVelocity().y)
        );
        return new Vector2(direction);
    }

    public void render(float delta) {
        time += delta;

        calculateDirection();
        selectorAnimations(delta);

        lastAnim = curAnim;

    }

    private int getZnak(float f){
        if(f > 0) return 1;
        if(f < 0) return -1;
        return 0;
    }

    private void selectorAnimations(float delta) {
        Vector2 linearVelocity = body.getLinearVelocity();
        if (isAlive){
            curAnim = "ydar";
            direction.set(1, 1);
            playAnimation(curAnim, direction);
        } else {
            curAnim = "rip";
            direction.set(1, 1);
            playAnimation(curAnim, direction);
        }


        if(!lastAnim.equals(curAnim))
            resetAnimationTimer();
    }

    private void playAnimation(String name, Vector2 unitScale){
        TextureRegion textureRegion = animations.get(name).getKeyFrame(time - startAnimTime);
        batch.draw(textureRegion,
                    body.getPosition().x - 5,
                    body.getPosition().y - 5,
                    5, 5, 9, 9, 1, 1,
                    body.getAngle() * MathUtils.radiansToDegrees);
    }

    public void update(float deltaTime, Player player) {
        float x = player.body.getPosition().x;
        float y = player.body.getPosition().y;
        Random rnd = new Random();

        System.out.println(body.getPosition());
        System.out.println(isAlive);
        if (isAlive) {
            if (!gameScreen.getCanMove()) {
                body.applyLinearImpulse(new Vector2(5, 30), body.getPosition(), true);
            } else {
//                System.out.println(y - body.getPosition().y);
                if (body.getPosition().y - y > 50) {
                    System.out.println("бегу вниз"+body.getPosition().y);
                    body.applyLinearImpulse(new Vector2(0, -1000), body.getPosition(), true);
                } else if (body.getPosition().x > x) {
                    body.applyLinearImpulse(new Vector2(-2, 15), body.getPosition(), true);
                } else if (body.getPosition().x < x) {
                    body.applyLinearImpulse(new Vector2(2, 15), body.getPosition(), true);
                }
            }
        }

    }

    public void handleCollision(Player player) {
        if (isAlive == true && player.isInvease() == false) {
            q.play();
        }

        //System.out.println("Enemy collided!");
    }

    public void setUserData(String enemy) {
        body.setUserData(enemy);
    }

    public Body getBody() {
        return body;
    }


}