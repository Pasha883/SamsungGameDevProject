package com.maxadventure.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.HashMap;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	public static int HEIGHT, WIDTH;
	public OrthographicCamera camera;

	public IntroScreen introScreen;
	public TestMapScreen testMapScreen;
	public MenuScreen menuScreen;

	Texture img;

	
	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
		introScreen = new IntroScreen(this, batch, camera);
		testMapScreen = new TestMapScreen(this,batch,camera);
		testMapScreen = new TestMapScreen(this,batch,camera);
		menuScreen = new MenuScreen(this, batch, camera);
		setScreen(introScreen);
	}

	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
