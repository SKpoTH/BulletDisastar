package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;
import java.util.Random;

public class World {

	private Random random;

	Sound bgm = Gdx.audio.newSound(Gdx.files.internal("sounds/pure_furies.mp3"));
    Sound deadSound = Gdx.audio.newSound(Gdx.files.internal("sounds/death.mp3"));
    Sound lifeSound = Gdx.audio.newSound(Gdx.files.internal("sounds/BONUS.wav"));
    Sound laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/ATTACK2.wav"));
    Sound explode = Gdx.audio.newSound(Gdx.files.internal("sounds/DEFEATED.wav"));
    Sound shinkSound = Gdx.audio.newSound(Gdx.files.internal("sounds/TWINKLE3.wav"));

    private boolean JustDead = true;
    private boolean JustLife = true;

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
    private double LifeSpawnTime = 20;
    private float LifeTimeCounter = 0;
    public boolean GetLife = false;
    public int LifeValue = 3;

    //Yin-Yang List
    private ArrayList<YinYang> yinYangs = new ArrayList<YinYang>();

	//Laser Cage Spawn Time Setting
	private static double LaserCageSpawnTime = 2.5;
	private float LaserTimeCounter = 0;

	private int LaserCageScoreAppear = 75;
	private float LaserCageScore = 0;

	private int LaserCageCount = 0;

	//Medium bullet Spawn Time Setting
	private double MediumBulletSpawnTime = 1;
	private float MediumBulletTimeCounter = 0;

	private int MediumBulletScoreLevelSpeed = 300;
    private float MediumBulletScoreSpeed = 0;

    private int MediumBulletScoreLevelSpawn = 80;
    private float MediumBulletScoreSpawn = 0;

    //Meteor Spawn Time Setting
    private double MeteorSpawnTime = 1;
    private float MeteorTimeCounter = 0;

    private int MeteorScoreApear = 50;
    private float MeteorScore = 0;

    private int MeteorCount = 0;


	private DisastarGame disastarGame;

	//Medium Bullet List
	private ArrayList<MediumBullet> mediumBullets = new ArrayList<MediumBullet>();

	//Laser List
	private ArrayList<LaserHorizontal> laserCageHorizontal = new ArrayList<LaserHorizontal>();
    private ArrayList<LaserVertical> laserCageVertical = new ArrayList<LaserVertical>();

    //Meteor List
    private  ArrayList<MeteorBullet> meteorBullets = new ArrayList<MeteorBullet>();

    public int Score = 0;
    private static final double ScoreRate = 0.3;
    private float ScoreTimeCounter = 0;

	
	World(DisastarGame disastarGame) {

		this.disastarGame = disastarGame;
		mainGirl = new MainGirl(this ,(GameScreen.SCREEN_WIDTH-200)/2,GameScreen.SCREEN_HEIGHT/2);

		random = new Random();

		long id = bgm.play(0.55f);
		bgm.setLooping(id, true);
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

    ArrayList<MeteorBullet> getMeteor(){
	    return meteorBullets;
    }

	public void update(float delta)
	{
	    if(GameScreen.GameStatus == 0){
	        Score = 0;
        }

	    if(GameScreen.GameStatus == 2) {
            //Life(Yin-Yang)
            LifeTimeCounter += delta;
            if (LifeTimeCounter >= (random.nextInt(10) + LifeSpawnTime)) {
                yinYangs.add(new YinYang(this));
                LifeTimeCounter = 0;
            }
            for (int i = 0; i < yinYangs.size(); i++) {
                yinYangs.get(i).update(delta);
                if (GetLife) {
                    long live = lifeSound.play(0.80f);
                    if (LifeValue < 5 && LifeValue > 0) {
                        LifeValue += 1;
                    }
                    yinYangs.remove(yinYangs.get(i));
                    GetLife = false;
                }
            }

            //Live Status
            if (Dead) {
                if(LifeValue > 0) {
                    mainGirl.ReviveGirl();
                    ReviveTimeCounter += delta;
                    if(JustDead){
                        long death = deadSound.play(0.80f);
                        JustDead = false;
                    }

                    if (ReviveTimeCounter >= ReviveTime) {
                        LifeValue -= 1;
                        Dead = false;
                        ReviveTimeCounter -= ReviveTime;
                        Immortal = true;
                        JustDead = true;
                    }
                }
            }
            if(LifeValue <= 0){
                GameScreen.GameStatus = 3;
            }

            if (Immortal) {
                ImmortalTimeCounter += delta;
                if (ImmortalTimeCounter >= ImmortalTime) {
                    ImmortalTimeCounter -= ImmortalTime;
                    Immortal = false;
                }
            }

            //Scoring System
            ScoreTimeCounter += delta;
            if (ScoreTimeCounter >= ScoreRate) {
                Score += 1;
                LaserCageScore += 1;
                MediumBulletScoreSpeed += 1;
                MediumBulletScoreSpawn += 1;

                MeteorScore += 1;

                ScoreTimeCounter -= ScoreRate;
            }

            //Medium Bullet normally spawn
            MediumBulletTimeCounter += delta;
            if (MediumBulletTimeCounter >= MediumBulletSpawnTime) {
                mediumBullets.add(new MediumBullet(this, random.nextInt(4)));
                MediumBulletTimeCounter -= MediumBulletSpawnTime;
            }
            for (int i = 0; i < mediumBullets.size(); i++) {
                mediumBullets.get(i).update(delta);
            }
            if(MediumBulletScoreSpeed >= MediumBulletScoreLevelSpeed){
                MediumBullet.BulletSpeed += 1;
                MediumBulletScoreSpeed -= MediumBulletScoreLevelSpeed;
            }
            if(MediumBulletScoreSpawn >= MediumBulletScoreLevelSpawn){
                long ving = shinkSound.play(0.80f);
                MediumBulletSpawnTime = MediumBulletSpawnTime*0.7;
                MediumBulletScoreSpawn -= MediumBulletScoreLevelSpawn;
            }


            //Laser Cage Part
            if (LaserCageScore >= LaserCageScoreAppear) {
                LaserTimeCounter += delta;
                if (LaserTimeCounter >= LaserCageSpawnTime) {
                    long laser = laserSound.play(0.80f);

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
                if (LaserCageCount == 5) {
                    LaserCageCount = 0;
                    LaserCageScore = 0;
                    LaserVertical.LaserSpeed += 1;
                    LaserHorizontal.LaserSpeed += 1;
                    LaserCageSpawnTime *= 0.75;
                }
            }
            for (int i = 0; i < laserCageHorizontal.size(); i++) {
                laserCageHorizontal.get(i).Move();
                laserCageHorizontal.get(i).update(delta);
            }

            for (int i = 0; i < laserCageVertical.size(); i++) {
                laserCageVertical.get(i).Move();
                laserCageVertical.get(i).update(delta);
            }

            //Meteor Part
            if(MeteorScore >= MeteorScoreApear) {
                MeteorTimeCounter += delta;
                if (MeteorTimeCounter >= MeteorSpawnTime) {
                    long boom = explode.play(0.15f);
                    meteorBullets.add(new MeteorBullet(this, random.nextInt(2)));
                    MeteorTimeCounter -= MeteorSpawnTime;

                    MeteorCount += 1;
                }
                if (MeteorCount == 8) {
                    MeteorCount = 0;
                    MeteorScore = 0;
                    MeteorBullet.MeteorSpeed += 1;

                    MeteorSpawnTime *= 0.75;
                }
            }
            for (int i = 0; i < meteorBullets.size(); i++) {
                meteorBullets.get(i).update(delta);
            }
        }
        if(GameScreen.GameStatus == 3){
            Dead = false;
            ReviveTimeCounter = 0;

            Immortal = false;
            ImmortalTimeCounter = 0;

            //Power(Life)
            LifeTimeCounter = 0;
            GetLife = false;
            LifeValue = 3;

            //Yin-Yang List
            for (int i = 0; i < yinYangs.size(); i++) {
                yinYangs.remove(yinYangs.get(i));
            }

            //Laser Cage Spawn Time Setting
            LaserTimeCounter = 0;
            LaserCageScore = 0;
            LaserCageCount = 0;
            LaserHorizontal.LaserSpeed = 5;
            LaserVertical.LaserSpeed = 5;

            //Medium bullet Spawn Time Setting
            MediumBulletTimeCounter = 0;
            MediumBulletScoreSpeed = 0;
            MediumBulletScoreSpawn = 0;

            MediumBullet.BulletSpeed = 4;
            MediumBulletSpawnTime = 1;

            //Meteor Setting
            MeteorSpawnTime = 1;
            MeteorTimeCounter = 0;
            MeteorScore = 0;
            MeteorCount = 0;

            //Medium Bullet List
            for (int i = 0; i < mediumBullets.size(); i++) {
                mediumBullets.remove(mediumBullets.get(i));
            }

            //Laser List
            for (int i = 0; i < laserCageHorizontal.size(); i++) {
                    laserCageHorizontal.remove(laserCageHorizontal.get(i));
            }
            for (int i = 0; i < laserCageVertical.size(); i++) {
                laserCageVertical.remove(laserCageVertical.get(i));
            }

            //Meteor List
            for (int i = 0; i < meteorBullets.size(); i++) {
                meteorBullets.remove(meteorBullets.get(i));
            }

            ScoreTimeCounter = 0;
        }
	}

}
