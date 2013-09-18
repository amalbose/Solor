package com.axatrikx.view;

import com.axatrikx.model.Enemy;
import com.axatrikx.model.Player;
import com.axatrikx.model.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class WorldRenderer {

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;

	private World world;
	private OrthographicCamera cam;

	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	/** Sprites **/
	private Sprite playerSprite;
	private Sprite enemySprite;

	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private int width;
	private int height;
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis
	TextureAtlas atlas;

	public WorldRenderer(World world, boolean debug) {
		this.world = world;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		this.debug = debug;
		spriteBatch = new SpriteBatch();
		atlas = new TextureAtlas(Gdx.files.internal("images/sample.pack"));
		loadTextures();
	}

	private void loadTextures() {
		/*playerTexture = new Texture(Gdx.files.internal("images/ship.png"));
		enemyTexture = new Texture(Gdx.files.internal("images/crate.png"));*/
		playerSprite = atlas.createSprite("ship");
		enemySprite = atlas.createSprite("crate");
	}

	public void render() {
		spriteBatch.begin();
		drawEnemies();
		drawPlayer();
		spriteBatch.end();
		if (debug)
			drawDebug();
	}

	private void drawEnemies() {
		for (Enemy enemy : world.getEnemies()) {
			spriteBatch.draw(enemySprite, enemy.position.x * ppuX, enemy.position.y * ppuY, Enemy.SIZE * ppuX, Enemy.SIZE * ppuY);
		}
	}

	private void drawPlayer() {
		Player player = world.getPlayer();
		spriteBatch.draw(playerSprite, player.position.x * ppuX, player.position.y * ppuY, Player.SIZE * ppuX, Player.SIZE * ppuY);
	}

	private void drawDebug() {
		// render blocks
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
		for (Enemy enemy : world.getEnemies()) {
			Rectangle rect = enemy.bounds;
			float x1 = enemy.position.x + rect.x;
			float y1 = enemy.position.y + rect.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		// render Bob
		Player bob = world.getPlayer();
		Rectangle rect = bob.bounds;
		float x1 = bob.position.x + rect.x;
		float y1 = bob.position.y + rect.y;
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();
	}

	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float) width / CAMERA_WIDTH;
		ppuY = (float) height / CAMERA_HEIGHT;
	}
}