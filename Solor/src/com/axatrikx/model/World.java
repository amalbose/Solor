package com.axatrikx.model;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class World {

	/** The blocks making up the world **/
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	/** Our player controlled hero **/
	Player player;

	// Getters -----------
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public Player getPlayer() {
		return player;
	}

	// --------------------

	public World() {
		createDemoWorld();
	}

	private void createDemoWorld() {
		player = new Player(new Vector2(7, 2));

		for (int i = 0; i < 10; i++) {
			enemies.add(new Enemy(new Vector2(i, 0)));
			enemies.add(new Enemy(new Vector2(i, 7)));
			if (i > 2)
				enemies.add(new Enemy(new Vector2(i, 1)));
		}
		enemies.add(new Enemy(new Vector2(9, 2)));
		enemies.add(new Enemy(new Vector2(9, 3)));
		enemies.add(new Enemy(new Vector2(9, 4)));
		enemies.add(new Enemy(new Vector2(9, 5)));

		enemies.add(new Enemy(new Vector2(6, 3)));
		enemies.add(new Enemy(new Vector2(6, 4)));
		enemies.add(new Enemy(new Vector2(6, 5)));
	}
}