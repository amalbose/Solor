package com.axatrikx;

import com.axatrikx.model.Enemy;
import com.axatrikx.model.Player;
import com.axatrikx.model.World;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class WorldRenderer {

	private World world;
	private OrthographicCamera cam;

	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	public WorldRenderer(World world) {
		this.world = world;
		this.cam = new OrthographicCamera(10, 7);
		this.cam.position.set(5, 3.5f, 0);
		this.cam.update();
	}

	public void render() {
		// render blocks
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
		for (Enemy block : world.getEnemies()) {
			Rectangle rect = block.bounds;
			float x1 = block.position.x + rect.x;
			float y1 = block.position.y + rect.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		// render Bob
		Player player = world.getPlayer();
		Rectangle rect = player.bounds;
		float x1 = player.position.x + rect.x;
		float y1 = player.position.y + rect.y;
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();
	}
}