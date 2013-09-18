package com.axatrikx.controller;

import java.util.HashMap;
import java.util.Map;

import com.axatrikx.model.BasePlayer.Direction;
import com.axatrikx.model.BasePlayer.State;
import com.axatrikx.model.Player;
import com.axatrikx.model.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;

public class WorldController {

	enum Keys {
		LEFT, RIGHT, UP, DOWN, IDLE
	}

	private World world;
	private Player player;

	private Keys lastKey;

	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		makeIdle();
		keys.put(Keys.IDLE, true);
	};

	public WorldController(World world) {
		this.world = world;
		this.player = world.getPlayer();
		lastKey = Keys.IDLE;
	}

	private static void makeIdle() {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
	}

	// ** Key presses and touches **************** //

	public void leftPressed() {
		keys.put(Keys.LEFT, true);
	}

	public void rightPressed() {
		keys.put(Keys.RIGHT, true);
	}

	public void downPressed() {
		keys.put(Keys.DOWN, true);
	}

	public void upPressed() {
		keys.put(Keys.UP, true);
	}

	public void leftReleased() {
		keys.put(Keys.LEFT, false);
	}

	public void rightReleased() {
		keys.put(Keys.RIGHT, false);
	}

	public void downReleased() {
		keys.put(Keys.DOWN, false);
	}

	public void upReleased() {
		keys.put(Keys.UP, false);
	}

	public void flingLeft() {
		setFlingKey(Keys.LEFT);
	}

	public void flingRight() {
		setFlingKey(Keys.RIGHT);
	}

	public void flingUp() {
		setFlingKey(Keys.UP);
	}

	public void flingDown() {
		setFlingKey(Keys.DOWN);
	}

	public void tap() {
		makeIdle();
		if (!keys.get(Keys.IDLE)) {
			keys.put(Keys.IDLE, true);
		} else {
			keys.put(Keys.IDLE, false);
			keys.put(lastKey, true);
		}
	}

	private void setFlingKey(Keys key) {
		makeIdle();
		keys.put(Keys.IDLE, false);
		keys.put(key, true);
		this.lastKey = key;
	}

	/** The main update method **/
	public void update(float delta) {
		processInput();
		player.update(delta);
	}

	/** Change player's state and parameters based on input controls **/
	private void processInput() {

		switch (Gdx.app.getType()) {
		case Android:
			player.state = State.MOVING;
			player.velocity.x = 0;
			player.velocity.y = 0;
			if (keys.get(Keys.LEFT)) {
				// left fling
				player.facingDir = Direction.LEFT;
				player.velocity.x = -Player.SPEED;
			} else if (keys.get(Keys.RIGHT)) {
				// right fling
				player.facingDir = Direction.RIGHT;
				player.velocity.x = Player.SPEED;
			} else if (keys.get(Keys.UP)) {
				// up fling
				player.facingDir = Direction.UP;
				player.velocity.y = -Player.SPEED;
			} else if (keys.get(Keys.DOWN)) {
				// down fling
				player.facingDir = Direction.DOWN;
				player.velocity.y = Player.SPEED;
			} else if (keys.get(Keys.IDLE)) {
				// tap
				player.facingDir = Direction.DOWN;
				player.state = State.IDLE;
			}
			break;
		default:
			if (keys.get(Keys.LEFT)) {
				// left is pressed
				player.facingDir = Direction.LEFT;
				player.state = State.MOVING;
				player.velocity.x = -Player.SPEED;
			}
			if (keys.get(Keys.RIGHT)) {
				// left is pressed
				player.facingDir = Direction.RIGHT;
				player.state = State.MOVING;
				player.velocity.x = Player.SPEED;
			}
			if (keys.get(Keys.UP)) {
				// up is pressed
				player.facingDir = Direction.UP;
				player.state = State.MOVING;
				player.velocity.y = Player.SPEED;
			}
			if (keys.get(Keys.DOWN)) {
				// up is pressed
				player.facingDir = Direction.DOWN;
				player.state = State.MOVING;
				player.velocity.y = -Player.SPEED;
			}
			// need to check if both or none direction(x) are pressed, then player is idle
			if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) || (!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
				player.state = State.IDLE;
				// horizontal speed is 0
				player.velocity.x = 0;
			}
			// need to check if both or none direction(y) are pressed, then player is idle
			if ((keys.get(Keys.UP) && keys.get(Keys.DOWN)) || (!keys.get(Keys.UP) && !(keys.get(Keys.DOWN)))) {
				player.state = State.IDLE;
				// horizontal speed is 0
				player.velocity.y = 0;
			}
			break;
		}
	}
}
