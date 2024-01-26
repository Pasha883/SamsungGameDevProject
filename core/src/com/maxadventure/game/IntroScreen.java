package com.maxadventure.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;

import java.io.FileNotFoundException;

public class IntroScreen implements Screen {
    //I am natural
    private  MyGdxGame myGdxGame;
    private final SpriteBatch batch;
    private OrthographicCamera camera;
    private VideoPlayer videoPlayer;
    private Texture tex;

    public IntroScreen(final MyGdxGame myGdxGame, SpriteBatch batch, OrthographicCamera camera) {
        this.batch = batch;
        this.camera = camera;
        this.myGdxGame = myGdxGame;
        tex = new Texture(Gdx.files.internal("badlogic.jpg"));
        videoPlayer = VideoPlayerCreator.createVideoPlayer();
        videoPlayer.setOnCompletionListener(new VideoPlayer.CompletionListener() {
            @Override
            public void onCompletionListener(FileHandle file) {
                myGdxGame.setScreen(myGdxGame.menuScreen);
            }
        });
        System.out.println("Длина"+Gdx.graphics.getWidth());

    }

    @Override
    public void show() {
        try {
            videoPlayer.play(Gdx.files.internal("Intro.webm"));
        } catch (FileNotFoundException e){
            ScreenUtils.clear(0,0,0, 1);
            batch.begin();
            batch.draw(tex, 0,0);
            batch.end();
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        videoPlayer.update();

        batch.begin();
        Texture frame = videoPlayer.getTexture();
        if (frame != null){
            batch.draw(frame,
                    0,
                    0,
                    Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight());
        }
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
}
