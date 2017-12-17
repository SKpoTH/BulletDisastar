package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.prism.shader.Texture_Color_AlphaTest_Loader;

public class WorldRenderer {
	
	private DisastarGame disastarGame;

	private Texture backGroundImg;
	private Texture infoTabImg;
	
	private Texture heroImg;
	private Texture mediumBulletImg;
	private Texture laserHorizontalImg;
	private Texture laserVerticalImg;

	private World world;

	private SpriteBatch batch;

	public WorldRenderer(DisastarGame disastarGame, World world) {
		this.disastarGame = disastarGame;
		batch = disastarGame.batch;

		this.world = world;

        backGroundImg = new Texture("background_sky.jpg");
        infoTabImg = new Texture("info_tab.png");

		heroImg = new Texture("hit_point.png");
		mediumBulletImg = new Texture("medium_bullet.png");
		laserHorizontalImg = new Texture("laser_purple_horizontal.png");
		laserVerticalImg = new Texture("laser_purple_vertical.png");
	}
	
	public void render(float delta) {
		SpriteBatch batch = disastarGame.batch;
	    batch.begin();

	    //Background
	    batch.draw(backGroundImg, 0 , 0);

        //Main Girl Player
        if(!world.Dead) {
            Vector2 pos = world.getMainGirl().getPosition();
            batch.draw(heroImg, pos.x, pos.y);
        }

	    //Medium Bullet
        for (int i = 0 ; i<world.getMediumBullets().size() ; i++){
            Vector2 PosMediumBullet = world.getMediumBullets().get(i).getPosition();
            batch.draw(mediumBulletImg, PosMediumBullet.x, PosMediumBullet.y);
        }

	    //Laser
	    for(int i = 0; i<world.getLaserHorizontal().size() ; i++){
            Vector2 PosH = world.getLaserHorizontal().get(i).getPosition();
            batch.draw(laserHorizontalImg, PosH.x, PosH.y);
        }
        for(int i = 0; i<world.getLaserVertical().size() ; i++){
            Vector2 PosV = world.getLaserVertical().get(i).getPosition();
            batch.draw(laserVerticalImg, PosV.x, PosV.y);
        }

        //Information Tab
        batch.draw(infoTabImg, 900, 0);

	    batch.end();
	}
}
