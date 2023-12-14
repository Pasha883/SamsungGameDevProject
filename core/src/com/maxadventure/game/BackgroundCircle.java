package com.maxadventure.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class BackgroundCircle {
    private Texture background;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private int n = -1;
    private int width, height;
    private float parallaxKoef;

    public BackgroundCircle(Texture background, SpriteBatch batch, OrthographicCamera camera, float parallaxKoef) {
        this.background = background;
        this.batch = batch;
        this.camera = camera;
        this.parallaxKoef = parallaxKoef;

        height = (int) MyGdxGame.SCREEN_HEIGHT;
        width = (int) ((height / (float) background.getHeight()) * background.getWidth());

        n = (int) (MyGdxGame.SCREEN_WIDTH) / width;
        if (n < 4)
            n = 4;
        else
            n+=2;
        n++;
    }

    public void render(float delta){
        int leftBottomPointCamera = (int)(camera.position.x) - (int) (MyGdxGame.WIDTH)/2;
        int startPoint = ((leftBottomPointCamera)/(width)) *
                width - width;
        for(int i = 0; i < n; i++) {
            batch.draw(background,
                    startPoint + i * width + (leftBottomPointCamera * parallaxKoef) % width,
                    (int)(camera.position.y) - (int) (MyGdxGame.HEIGHT)/2,
                    width,
                    height
            );
        }
//        System.out.println("Start point: " + startPoint + "; Position: " + (((int)(camera.position.x) - MyGdxGame.WIDTH/2)));
    }

    public void renderView(float delta, FitViewport game){
        OrthographicCamera cam;
        cam = (OrthographicCamera) game.getCamera();
        int leftBottomPointCamera = (int)(cam.position.x) - (int) (MyGdxGame.WIDTH)/2;
        int startPoint = ((leftBottomPointCamera)/(width)) *
                width;
        System.out.println(leftBottomPointCamera + "  "  + startPoint + " " + width);
        for(int i = 0; i < n; i++) {
            batch.draw(background,
                    startPoint + i * width + (leftBottomPointCamera * parallaxKoef) % width,
                    (int)(cam.position.y) - (int) (MyGdxGame.HEIGHT)/2,
                    MyGdxGame.WIDTH - 2000,
                    MyGdxGame.HEIGHT - (2000 * (MyGdxGame.HEIGHT / MyGdxGame.WIDTH))
            );
            //System.out.println(cam.position.x + "Бляяяяяяя " + cam.position.y);
        }
//        System.out.println("Start point: " + startPoint + "; Position: " + (((int)(camera.position.x) - MyGdxGame.WIDTH/2)));
    }

    public Texture getBackground() {
        return background;
    }
}