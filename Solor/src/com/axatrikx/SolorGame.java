package com.axatrikx;

import com.axatrikx.screens.GameScreen;
import com.badlogic.gdx.Game;

public class SolorGame extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
