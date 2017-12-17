package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
    BitmapFont font = new BitmapFont();
	
	private DisastarGame disastarGame;

	private Texture titleImg;
	private Texture tutorialImg;
	private Texture endingImg;

	private Texture backGroundImg;
	private Texture infoTabImg;
	
	private Texture heroImg;
	private Texture yinyangImg;


	private Texture mediumBulletImg;
	private Texture laserHorizontalImg;
	private Texture laserVerticalImg;

	private World world;

	private SpriteBatch batch;

	public WorldRenderer(DisastarGame disastarGame, World world) {
		this.disastarGame = disastarGame;
		batch = disastarGame.batch;

		this.world = world;

		titleImg = new Texture("title_screen.png");
		tutorialImg = new Texture("tutorial_screen.png");
		endingImg = new Texture("ending_screen.png");

        backGroundImg = new Texture("background_sky.jpg");
        infoTabImg = new Texture("info_tab.png");

		heroImg = new Texture("hit_point.png");
		yinyangImg = new Texture("ying_yang.png");

		mediumBulletImg = new Texture("medium_bullet.png");
		laserHorizontalImg = new Texture("laser_purple_horizontal.png");
		laserVerticalImg = new Texture("laser_purple_vertical.png");
	}
	
	public void render(float delta) {
		SpriteBatch batch = disastarGame.batch;
	    batch.begin();

        if(GameScreen.GameStatus == 0) {
            batch.draw(titleImg, 0, 0);
        }

        if(GameScreen.GameStatus == 1){
            batch.draw(tutorialImg, 0, 0);
        }


	    if(GameScreen.GameStatus == 2) {
            //Background
            batch.draw(backGroundImg, 0, 0);

            //Main Girl Player
            if (!world.Dead) {
                Vector2 pos = world.getMainGirl().getPosition();
                batch.draw(heroImg, pos.x, pos.y);
            }

            //Yin-Yang (Life)
            for (int i = 0; i < world.getYinYangs().size(); i++) {
                Vector2 PosLife = world.getYinYangs().get(i).getPosition();
                batch.draw(yinyangImg, PosLife.x, PosLife.y);
            }

            //Medium Bullet
            for (int i = 0; i < world.getMediumBullets().size(); i++) {
                Vector2 PosMediumBullet = world.getMediumBullets().get(i).getPosition();
                batch.draw(mediumBulletImg, PosMediumBullet.x, PosMediumBullet.y);
            }

            //Laser
            for (int i = 0; i < world.getLaserHorizontal().size(); i++) {
                Vector2 PosH = world.getLaserHorizontal().get(i).getPosition();
                batch.draw(laserHorizontalImg, PosH.x, PosH.y);
            }
            for (int i = 0; i < world.getLaserVertical().size(); i++) {
                Vector2 PosV = world.getLaserVertical().get(i).getPosition();
                batch.draw(laserVerticalImg, PosV.x, PosV.y);
            }

            //Information Tab
            batch.draw(infoTabImg, 900, 0);

            //Show Score
            font.draw(batch, "" + world.Score, 990, 460);

            //Show Life now
            if (world.LifeValue >= 1) {
                batch.draw(yinyangImg, 920, 315);
            }
            if (world.LifeValue >= 2) {
                batch.draw(yinyangImg, 950, 315);
            }
            if (world.LifeValue >= 3) {
                batch.draw(yinyangImg, 980, 315);
            }
            if (world.LifeValue >= 4) {
                batch.draw(yinyangImg, 1010, 315);
            }
            if (world.LifeValue >= 5) {
                batch.draw(yinyangImg, 1040, 315);
            }
        }

        if(GameScreen.GameStatus == 3){
            batch.draw(endingImg, 0, 0);
            font.draw(batch, "" + world.Score, 360, 300);
            font.draw(batch, "" + world.Score, 720, 300);
        }

	    batch.end();
	}
}
