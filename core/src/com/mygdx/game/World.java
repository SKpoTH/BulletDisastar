package com.mygdx.game;

import java.util.ArrayList;

public class World {

	private MainGirl mainGirl;

	private Laser laser;

	//Laser Cage Spawn Time Setting
	private static final float LaserCageSpawnTime = 2;
	private float BadGirld_TimeCounter = 0;

	private DisastarGame disastarGame;

	private ArrayList<Laser> laserCageHorizontal = new ArrayList<Laser>();
    private ArrayList<Laser> laserCageVertical = new ArrayList<Laser>();
	
	World(DisastarGame disastarGame) {

		this.disastarGame = disastarGame;
		mainGirl = new MainGirl(GameScreen.SCREEN_WIDTH/2,GameScreen.SCREEN_HEIGHT/2);
	}

	MainGirl getMainGirl() {
		return mainGirl;
	}

	ArrayList<Laser> getLaserHorizontal() {
		return laserCageHorizontal;
	}

    ArrayList<Laser> getLaserVertical() {
        return laserCageVertical;
    }

	public void update(float delta)
	{
		BadGirld_TimeCounter += delta;
		if(BadGirld_TimeCounter >= LaserCageSpawnTime){
            laserCageHorizontal.add(new Laser(-475, 600));
            laserCageHorizontal.add(new Laser(-475, 480));
            laserCageHorizontal.add(new Laser(-475, 360));
            laserCageHorizontal.add(new Laser(-475, 240));
            laserCageHorizontal.add(new Laser(-475, 120));
            laserCageHorizontal.add(new Laser(-475, 0));

            laserCageVertical.add(new Laser(840, 1125));
            laserCageVertical.add(new Laser(720, 1125));
            laserCageVertical.add(new Laser(600, 1125));
            laserCageVertical.add(new Laser(480, 1125));
            laserCageVertical.add(new Laser(360, 1125));
            laserCageVertical.add(new Laser(240, 1125));
            laserCageVertical.add(new Laser(120, 1125));
            laserCageVertical.add(new Laser(0, 1125));

			BadGirld_TimeCounter -= LaserCageSpawnTime;

		}
		for(int i = 0; i< laserCageHorizontal.size() ; i++) {
            laserCageHorizontal.get(i).MoveHorizontal();
        }

        for(int i = 0; i< laserCageVertical.size() ; i++) {
            laserCageVertical.get(i).MoveVertical();
        }
	}

}
