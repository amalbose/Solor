package com.axatrikx.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends BasePlayer {

	public static final String name = "Solor";
	
	static final float SPEED = 2f; // unit per second
	static final float SIZE = 0.5f; // half a unit

	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();

	public Rectangle bounds = new Rectangle();
	
	State state = State.IDLE;
	Direction facingDir = Direction.UP;

	public Player(Vector2 position) {
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
		this.isHero = true;
	}
}
