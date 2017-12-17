package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

public class World {

	private Random random;

	private MainGirl mainGirl;

	private YinYang yinYang;

	private MediumBullet mediumBullet;

	private LaserHorizontal laserHorizontal;
	private LaserVertical laserVertical;

	//Status of Player
    public boolean Dead = false;
    private double ReviveTime = 3;
    private float ReviveTimeCounter = 0;

    public boolean Immortal = false;
    private double ImmortalTime = 2.5;
    private float ImmortalTimeCounter = 0;

    //Power(Life)
    private double LifeSpawnTime = 15;
    private float LifeTimeCounter = 0;
    public boolean GetLife = false;
    public int LifeValue = 3;

    //Yin-Yang List
    private ArrayList<YinYang> yinYangs = new ArrayList<YinYang>();

	//Laser Cage Spawn Time Setting
	private static final double LaserCageSpawnTime = 2;
	private float LaserTimeCounter = 0;

	private int LaserCageScoreAppear = 50;
	private float LaserCageScore = 0;

	private int LaserCageCount = 0;

	//Medium bullet Spawn Time Setting
	private  static final double MediumBulletSpawnTime = 0.5;
	private float MediumBulletTimeCounter = 0;

	private DisastarGame disastarGame;

	//Medium Bullet List
	private ArrayList<MediumBullet> mediumBullets = new ArrayList<MediumBullet>();

	//Laser List
	private ArrayList<LaserHorizontal> laserCageHorizontal = new ArrayList<LaserHorizontal>();
    private ArrayList<LaserVertical> laserCageVertical = new ArrayList<LaserVertical>();

    public int Score = 0;
    private static final double ScoreRate = 0.3;
    private float ScoreTimeCounter = 0;

	
	World(DisastarGame disastarGame) {

		this.disastarGame = disastarGame;
		mainGirl = new MainGirl(this ,(GameScreen.SCREEN_WIDTH-200)/2,GameScreen.SCREEN_HEIGHT/2);

		random = new Random();
	}

	MainGirl getMainGirl() {
		return mainGirl;
	}

	ArrayList<YinYang> getYinYangs() {
	    return yinYangs;
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
	    //Life(Yin-Yang)
        LifeTimeCounter += delta;
        if(LifeTimeCounter >= (random.nextInt(10) + LifeSpawnTime)){
            yinYangs.add(new YinYang(this));
            LifeTimeCounter = 0;
        }
        for(int i = 0; i< yinYangs.size() ; i++) {
            yinYangs.get(i).update(delta);
            if(GetLife) {
                if(LifeValue < 5 && LifeValue > 0) {
                    LifeValue += 1;
                }
                yinYangs.remove(yinYangs.get(i));
                GetLife = false;
            }
        }

	    //Live Status
        if(Dead){
            mainGirl.ReviveGirl();
            ReviveTimeCounter += delta;
            if(ReviveTimeCounter >= ReviveTime){
                LifeValue -= 1;
                Dead = false;
                ReviveTimeCounter -= ReviveTime;
                Immortal = true;
            }
        }
        if(Immortal){
            ImmortalTimeCounter += delta;
            if(ImmortalTimeCounter >= ImmortalTime) {
                ImmortalTimeCounter -= ImmortalTime;
                Immortal = false;
            }
        }

	    //Scoring System
        ScoreTimeCounter += delta;
        if(ScoreTimeCounter >= ScoreRate){
            Score += 1;
            LaserCageScore += 1;

            ScoreTimeCounter -= ScoreRate;
        }

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
        if(LaserCageScore >= LaserCageScoreAppear) {
            LaserTimeCounter += delta;
            if (LaserTimeCounter >= LaserCageSpawnTime) {
                laserCageHorizontal.add(new LaserHorizontal(this, -475, 600));
                laserCageHorizontal.add(new LaserHorizontal(this, -475, 480));
                laserCageHorizontal.add(new LaserHorizontal(this, -475, 360));
                laserCageHorizontal.add(new LaserHorizontal(this, -475, 240));
                laserCageHorizontal.add(new LaserHorizontal(this, -475, 120));
                laserCageHorizontal.add(new LaserHorizontal(this, -475, 0));

                laserCageVertical.add(new LaserVertical(this, 840, 1125));
                laserCageVertical.add(new LaserVertical(this, 720, 1125));
                laserCageVertical.add(new LaserVertical(this, 600, 1125));
                laserCageVertical.add(new LaserVertical(this, 480, 1125));
                laserCageVertical.add(new LaserVertical(this, 360, 1125));
                laserCageVertical.add(new LaserVertical(this, 240, 1125));
                laserCageVertical.add(new LaserVertical(this, 120, 1125));
                laserCageVertical.add(new LaserVertical(this, 0, 1125));

                LaserTimeCounter -= LaserCageSpawnTime;

                LaserCageCount += 1;
            }
            if(LaserCageCount == 5){
                LaserCageCount = 0;
                LaserCageScore = 0;
            }
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
