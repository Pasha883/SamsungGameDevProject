package com.maxadventure.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

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


    public Enemy(GameScreen gameScreen,World world, float x, float y) {
        this.world = world;
        this.gameScreen=gameScreen;

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


        texture = new Texture(Gdx.files.internal("pin.png"));
        texture2 = new Texture(Gdx.files.internal("pinkill.png"));
        textureRegion = new TextureRegion(texture);

    }

    public void render(SpriteBatch batch) {
        if (isAlive) {
            batch.draw(textureRegion,
                    body.getPosition().x - 5,
                    body.getPosition().y - 5,
                    5, 5, 7, 7, 1, 1,
                    body.getAngle() * MathUtils.radiansToDegrees);
            System.out.println(body.getAngle());
        }
        else
            batch.draw(texture2, body.getPosition().x - 5, body.getPosition().y - 5, 7, 7);
    }

    public void update(float deltaTime, Player player) {
        float x = player.body.getPosition().x;
        float y = player.body.getPosition().y;
        Random rnd = new Random();

        System.out.println(body.getPosition());
        System.out.println(isAlive);
        if (isAlive) {
            if (!gameScreen.getCanMove()){
                body.applyLinearImpulse(new Vector2(20, 30), body.getPosition(), true);
            } else {
//                System.out.println(y - body.getPosition().y);
                if (body.getPosition().y - y > 50) {
                    body.applyLinearImpulse(new Vector2(0, -100), body.getPosition(), true);
                }
                if (body.getPosition().x > x) {
                    body.applyLinearImpulse(new Vector2(-10, 15), body.getPosition(), true);
                } else if (body.getPosition().x < x) {
                    body.applyLinearImpulse(new Vector2(10, 15), body.getPosition(), true);
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