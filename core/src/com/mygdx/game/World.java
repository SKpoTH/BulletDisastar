package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

public class World {

	private Random random;

	private MainGirl mainGirl;

	private MediumBullet mediumBullet;

	private LaserHorizontal laserHorizontal;
	private LaserVertical laserVertical;

	//Laser Cage Spawn Time Setting
	private static final double LaserCageSpawnTime = 2;
	private float LaserTimeCounter = 0;

	//Medium bullet Spawn Time Setting
	private  static final double MediumBulletSpawnTime = 0.25;
	private float MediumBulletTimeCounter = 0;

	private DisastarGame disastarGame;

	//Medium Bullet List
	private ArrayList<MediumBullet> mediumBullets = new ArrayList<MediumBullet>();

	//Laser List
	private ArrayList<LaserHorizontal> laserCageHorizontal = new ArrayList<LaserHorizontal>();
    private ArrayList<LaserVertical> laserCageVertical = new ArrayList<LaserVertical>();

	
	World(DisastarGame disastarGame) {

		this.disastarGame = disastarGame;
		mainGirl = new MainGirl(GameScreen.SCREEN_WIDTH/2,GameScreen.SCREEN_HEIGHT/2);

		random = new Random();
	}

	MainGirl getMainGirl() {
		return mainGirl;
	}

    ArrayList<MediumBullet> getMediumBullets() {
        return mediumBullets;
    }

	ArrayList<LaserHorizontal> getLaserHorizontal() {
		return laserCageHorizontal;
	}

    ArrayList<LaserVertical> getLaserVertical() {
        return laserCageVertical;
    }

	public void update(float delta)
	{
		//Medium Bullet normally spawn
		MediumBulletTimeCounter += delta;
		if(MediumBulletTimeCounter >= MediumBulletSpawnTime){
			mediumBullets.add(new MediumBullet(this, random.nextInt(3)));
			MediumBulletTimeCounter -= MediumBulletSpawnTime;
		}
		for(int i = 0 ; i < mediumBullets.size() ; i++){
		    mediumBullets.get(i).update(delta);
        }

		//Laser Cage Part
		LaserTimeCounter += delta;
		if(LaserTimeCounter >= LaserCageSpawnTime){
            laserCageHorizontal.add(new LaserHorizontal(this,-475, 600));
            laserCageHorizontal.add(new LaserHorizontal(this,-475, 480));
            laserCageHorizontal.add(new LaserHorizontal(this,-475, 360));
            laserCageHorizontal.add(new LaserHorizontal(this,-475, 240));
            laserCageHorizontal.add(new LaserHorizontal(this,-475, 120));
            laserCageHorizontal.add(new LaserHorizontal(this,-475, 0));

            laserCageVertical.add(new LaserVertical(this,840, 1125));
            laserCageVertical.add(new LaserVertical(this,720, 1125));
            laserCageVertical.add(new LaserVertical(this,600, 1125));
            laserCageVertical.add(new LaserVertical(this,480, 1125));
            laserCageVertical.add(new LaserVertical(this,360, 1125));
            laserCageVertical.add(new LaserVertical(this,240, 1125));
            laserCageVertical.add(new LaserVertical(this,120, 1125));
            laserCageVertical.add(new LaserVertical(this,0, 1125));

			LaserTimeCounter -= LaserCageSpawnTime;

		}
		for(int i = 0; i< laserCageHorizontal.size() ; i++) {
            laserCageHorizontal.get(i).Move();
            laserCageHorizontal.get(i).update(delta);
        }

        for(int i = 0; i< laserCageVertical.size() ; i++) {
            laserCageVertical.get(i).Move();
            laserCageVertical.get(i).update(delta);
        }




	}

}
