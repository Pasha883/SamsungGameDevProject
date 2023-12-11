package com.maxadventure.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	public OrthographicCamera camera;

	public IntroScreen introScreen;

	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		introScreen = new IntroScreen(this, batch, camera);
		setScreen(introScreen);
	}

	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
