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

	private Texture girl1;
    private Texture girl2;
    private Texture girl3;
    private Texture girl4;
    private Texture girl5;
    private Texture girl6;
    private Texture girl7;
    private Texture girl8;
    private double AnimateChange = 0.01251;
    private float AnimateCount = 0;
    private int AnimateFlame = 0;

	private Texture yinyangImg;

	private Texture mediumBulletImg;
	private Texture laserHorizontalImg;
	private Texture laserVerticalImg;
	private Texture meteorImg;

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

		girl1 = new Texture("girl_1.png");
        girl2 = new Texture("girl_2.png");
        girl3 = new Texture("girl_3.png");
        girl4 = new Texture("girl_4.png");
        girl5 = new Texture("girl_5.png");
        girl6 = new Texture("girl_6.png");
        girl7 = new Texture("girl_7.png");
        girl8 = new Texture("girl_8.png");

		yinyangImg = new Texture("ying_yang.png");

		mediumBulletImg = new Texture("medium_bullet.png");
		laserHorizontalImg = new Texture("laser_purple_horizontal.png");
		laserVerticalImg = new Texture("laser_purple_vertical.png");

		meteorImg = new Texture("meteor.png");

		font.getData().setScale(2.5f, 2.5f);
	}

	public void GirlAnimation(float delta, Vector2 pos){
	    AnimateCount += delta;
	    if(AnimateCount >= AnimateChange && AnimateFlame == 0){
            batch.draw(girl1, pos.x-7, pos.y-20);
            AnimateFlame = 1;
        }
        else if(AnimateCount >= AnimateChange*2 && AnimateFlame == 1){
            batch.draw(girl2, pos.x-7, pos.y-20);
            AnimateFlame = 2;
        }
        else if(AnimateCount >= AnimateChange*3 && AnimateFlame == 2){
            batch.draw(girl3, pos.x-7, pos.y-20);
            AnimateFlame = 3;
        }
        else if(AnimateCount >= AnimateChange*4 && AnimateFlame == 3){
            batch.draw(girl4, pos.x-7, pos.y-20);
            AnimateFlame = 4;
        }
        else if(AnimateCount >= AnimateChange*5 && AnimateFlame == 4){
            batch.draw(girl5, pos.x-7, pos.y-20);
            AnimateFlame = 5;
        }
        else if(AnimateCount >= AnimateChange*6 && AnimateFlame == 5){
            batch.draw(girl6, pos.x-7, pos.y-20);
            AnimateFlame = 6;
        }
        else if(AnimateCount >= AnimateChange*7 && AnimateFlame == 6){
            batch.draw(girl7, pos.x-7, pos.y-20);
            AnimateFlame = 7;
        }
        else if(AnimateCount >= AnimateChange*8 && AnimateFlame == 7){
            batch.draw(girl8, pos.x-7, pos.y-20);
            AnimateFlame = 0;
            AnimateCount -= AnimateChange*8;
        }
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
                GirlAnimation(delta, pos);
                if(MainGirl.Hold) {
                    batch.draw(heroImg, pos.x, pos.y);
                }
            }

            //Yin-Yang (Life)
            for (int i = 0; i < world.getYinYangs().size(); i++) {
                Vector2 PosLife = world.getYinYangs().get(i).getPosition();
                batch.draw(yinyangImg, PosLife.x, PosLife.y);
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

            //Medium Bullet
            for (int i = 0; i < world.getMediumBullets().size(); i++) {
                Vector2 PosMediumBullet = world.getMediumBullets().get(i).getPosition();
                batch.draw(mediumBulletImg, PosMediumBullet.x, PosMediumBullet.y);
            }

            //Meteor Bullet
            for (int i = 0; i < world.getMeteor().size(); i++) {
                Vector2 PosMeteor = world.getMeteor().get(i).getPosition();
                batch.draw(meteorImg, PosMeteor.x, PosMeteor.y);
            }

            //Information Tab
            batch.draw(infoTabImg, 900, 0);

            //Show Score
            font.draw(batch, "" + world.Score, 980, 580);

            //Show Life now
            if (world.LifeValue >= 1) {
                batch.draw(yinyangImg, 920, 430);
            }
            if (world.LifeValue >= 2) {
                batch.draw(yinyangImg, 950, 430);
            }
            if (world.LifeValue >= 3) {
                batch.draw(yinyangImg, 980, 430);
            }
            if (world.LifeValue >= 4) {
                batch.draw(yinyangImg, 1010, 430);
            }
            if (world.LifeValue >= 5) {
                batch.draw(yinyangImg, 1040, 430);
            }
        }

        if(GameScreen.GameStatus == 3){
            batch.draw(endingImg, 0, 0);
            font.draw(batch, "" + world.Score, 340, 300);
            font.draw(batch, "" + world.Score, 700, 300);
        }

	    batch.end();
	}
}
