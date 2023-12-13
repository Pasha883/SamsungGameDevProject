package com.maxadventure.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.maxadventure.game.MyGdxGame;


public class MemeMenuScreen implements Screen{

    private MyGdxGame myGdxGame;
    private final SpriteBatch batch;
    private OrthographicCamera camera;

    Texture atlas;
    Animation<TextureRegion> animation;

    private Texture men;
    private Music musicM = Gdx.audio.newMusic(Gdx.files.internal("MenuAssets/Amogus.mp3"));

    float time = 0;




    public MemeMenuScreen(MyGdxGame myGdxGame, SpriteBatch batch, OrthographicCamera camera) {
        this.batch = batch;
        this.camera = camera;
        this.myGdxGame = myGdxGame;
        men = new Texture("MenuAssets/Menu.png");

        atlas = new Texture(Gdx.files.internal("MenuAssets/Birdy.png"));
        TextureRegion[][] textures = TextureRegion.split(atlas,640, 640);
        Array<TextureRegion> animationFrames = new Array<>();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                animationFrames.add(textures[i][j]);
            }
        }

        animation = new Animation<TextureRegion>(0.1f, animationFrames, Animation.PlayMode.LOOP);
    }

    @Override
    public void show() {
        musicM.play();
        musicM.setLooping(true);
        camera.position.x = Gdx.graphics.getWidth() / 2;
        camera.position.y = Gdx.graphics.getHeight() / 2;
        //camera.position.x =  Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 12;

    }

    @Override
    public void render(float delta) {
        time += delta;
        ScreenUtils.clear(1,1,1,1);
        if ((Gdx.input.justTouched())){
            myGdxGame.setScreen(myGdxGame.menuScreen);
        }
        TextureRegion currientFrame = animation.getKeyFrame(time);
        camera.update();
        batch.begin();
        batch.draw(men, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);
        batch.draw(currientFrame, Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 - 200, 400, 400);
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
        musicM.stop();
    }

    @Override
    public void dispose() {

    }
}