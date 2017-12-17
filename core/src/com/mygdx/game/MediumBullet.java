package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class MediumBullet {

    private Random random;

    private float ImageSize_X = 40;
    private float ImageSize_Y = 40;

    private float Radius_X = ImageSize_X/2;
    private float Radius_Y = ImageSize_Y/2;

    private float Center_X;
    private float Center_Y;

    private Vector2 position;

    private MainGirl mainGirl;

    private int BulletSpeed = 5;
    private int Type;

    private int RandomMove;

    private World world;

    public MediumBullet(World world, int type){
        this.world = world;

        mainGirl = world.getMainGirl();

        Type = type;
        random = new Random();

        if(Type == 0) {
            position = new Vector2(0, random.nextInt(GameScreen.SCREEN_HEIGHT));    //Spawn on the left
        } else if(Type == 1) {
            position = new Vector2(GameScreen.SCREEN_WIDTH-200, random.nextInt(GameScreen.SCREEN_HEIGHT));     //Spawn on the right
        } else if(Type == 2) {
            position = new Vector2(random.nextInt(GameScreen.SCREEN_WIDTH-200), GameScreen.SCREEN_HEIGHT);      //Spawn on the top
        } else if(Type == 3) {
            position = new Vector2(random.nextInt(GameScreen.SCREEN_WIDTH-200), GameScreen.SCREEN_HEIGHT);      //Spawn on the bottom
        }
        RandomMove = random.nextInt(BulletSpeed) - random.nextInt(BulletSpeed);

        Center_X = position.x + Radius_X;
        Center_Y = position.y + Radius_Y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float delta) {
        if(Type == 0) {
            position.x += BulletSpeed;
            position.y += RandomMove;

            Center_X += BulletSpeed;
            Center_Y += RandomMove;
        } else if(Type == 1) {
            position.x -= BulletSpeed;
            position.y += RandomMove;

            Center_X -= BulletSpeed;
            Center_Y += RandomMove;
        } else if(Type == 2) {
            position.x += RandomMove;
            position.y -= BulletSpeed;

            Center_X += RandomMove;
            Center_Y -= BulletSpeed;
        } else if(Type == 3) {
            position.x += RandomMove;
            position.y += BulletSpeed;

            Center_X += RandomMove;
            Center_Y += BulletSpeed;
        }

        if(!world.Dead && !world.Immortal) {
            if (mainGirl.getCenter_X() > Center_X - Radius_X && mainGirl.getCenter_X() < Center_X + Radius_X) {

                if (mainGirl.getCenter_Y() > Center_Y - Radius_Y && mainGirl.getCenter_Y() < Center_Y + Radius_Y) {
                    world.Dead = true;
                }
            }
        }
    }
}
