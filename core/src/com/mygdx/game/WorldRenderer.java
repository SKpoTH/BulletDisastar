package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	
	private DisastarGame disastarGame;
	
	private Texture heroImg;

	private World world;

	private SpriteBatch batch;

	public WorldRenderer(DisastarGame disastarGame, World world) {
		this.disastarGame = disastarGame;
		batch = disastarGame.batch;

		this.world = world;

		heroImg = new Texture("pacman.png");
	}
	
	public void render(float delta) {
		SpriteBatch batch = disastarGame.batch;
	    batch.begin();
	    
	    Vector2 pos = world.getMainGirl().getPosition();
	    batch.draw(heroImg, pos.x, pos.y);
	    
	    batch.end();
	}
}
