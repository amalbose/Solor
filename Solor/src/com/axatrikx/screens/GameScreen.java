package com.axatrikx.screens;

import com.axatrikx.controller.InputController;
import com.axatrikx.controller.WorldController;
import com.axatrikx.model.World;
import com.axatrikx.view.WorldRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;

public class GameScreen implements Screen {

	private World world;
	private WorldRenderer renderer;
	private InputController iController;

	public GameScreen() {
		iController = new InputController();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		iController.updateController(delta);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);
		iController.setSize(width, height);
	}

	@Override
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world, false);
		iController.setController(new WorldController(world));

		switch (Gdx.app.getType()) {
		case Android:
			Gdx.input.setInputProcessor(new GestureDetector(20, 0.5f, 2, 0.15f, iController));
			break;
		default:
			Gdx.input.setInputProcessor(iController);
			break;
		}
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

}