package com.axatrikx.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends BasePlayer {

	static final float SIZE = 1f;

	public Vector2 position = new Vector2();
	public Rectangle bounds = new Rectangle();

	public Enemy(Vector2 pos) {
		this.position = pos;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		isHero = false;
	}

}
