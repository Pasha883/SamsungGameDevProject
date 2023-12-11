package com.maxadventure.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundCircle {
    private Texture background;
    private final SpriteBatch batch;
    private OrthographicCamera camera;
    private int n = -1;
    private int width, height;
    private float parallaxKoef;

    public BackgroundCircle(Texture background, SpriteBatch batch, OrthographicCamera camera, float parallaxKoef) {
        this.background = background;
        this.batch = batch;
        this.camera = camera;
        this.parallaxKoef = parallaxKoef;

        height = MyGdxGame.HEIGHT;
        width = (int) ((height / (float)(background.getHeight())) * background.getWidth());

        System.out.println(background.getHeight() + " " + background.getWidth());
        System.out.println(height + " " + width);

        n = MyGdxGame.WIDTH / width;

        if (n < 4)
            n = 4;
        else
            n+=2;



    }

    public void render(float delta){
        int lefnBottomCamera = (int)(camera.position.x) - MyGdxGame.WIDTH/2;
        int startPoint = ((lefnBottomCamera)/(width)) * width - width;
        for(int i = 0; i < n; i++) {
            batch.draw(background,
                    startPoint + i * width + (lefnBottomCamera * parallaxKoef % width),
                    0,
                    width,
                    height
            );
        }
    }
}
