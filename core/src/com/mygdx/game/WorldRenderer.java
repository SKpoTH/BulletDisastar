package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	
	private DisastarGame disastarGame;

	private Texture backGroundImg;
	
	private Texture heroImg;
	private Texture laserHorizontalImg;
	private Texture laserVerticalImg;

	private World world;

	private SpriteBatch batch;

	public WorldRenderer(DisastarGame disastarGame, World world) {
		this.disastarGame = disastarGame;
		batch = disastarGame.batch;

		this.world = world;

		backGroundImg = new Texture("background_sky.jpg");

		heroImg = new Texture("pacman.png");
		laserHorizontalImg = new Texture("laser_orange_horizontal.png");
		laserVerticalImg = new Texture("laser_orange_vertical.png");
	}
	
	public void render(float delta) {
		SpriteBatch batch = disastarGame.batch;
	    batch.begin();

	    batch.draw(backGroundImg, 0 , 0);

	    for(int i = 0; i<world.getLaserHorizontal().size() ; i++){
            Vector2 PosH = world.getLaserHorizontal().get(i).getPosition();
            batch.draw(laserHorizontalImg, PosH.x, PosH.y);
        }

        for(int i = 0; i<world.getLaserVertical().size() ; i++){
            Vector2 PosV = world.getLaserVertical().get(i).getPosition();
            batch.draw(laserVerticalImg, PosV.x, PosV.y);
        }

	    Vector2 pos = world.getMainGirl().getPosition();
        batch.draw(heroImg, pos.x , pos.y);

	    batch.end();
	}
}
