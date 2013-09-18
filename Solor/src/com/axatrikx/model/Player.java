package com.axatrikx.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends BasePlayer {

	public static final String name = "Solor";

	public static final float SPEED = 1f; // unit per second
	public static final float SIZE = 0.5f; // half a unit

	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();

	public Rectangle bounds = new Rectangle();

	public State state = State.IDLE;
	public Direction facingDir = Direction.UP;

	public Player(Vector2 position) {
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
		this.isHero = true;
	}

	public void update(float delta) {
		position.add(velocity.cpy().mul(delta));
	}
}
