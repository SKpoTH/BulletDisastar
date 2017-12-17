package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class MeteorBullet {
    private Random random;

    private float ImageSize_X = 70;
    private float ImageSize_Y = 70;

    private float Radius_X = ImageSize_X / 2;
    private float Radius_Y = ImageSize_Y / 2;

    private float Center_X;
    private float Center_Y;

    private Vector2 position;

    private MainGirl mainGirl;

    public static int MeteorSpeed = 4;

    private World world;

    public MeteorBullet(World world, int Type) {
        this.world = world;

        mainGirl = world.getMainGirl();

        random = new Random();

        if(Type == 0) {
            position = new Vector2(0, random.nextInt(GameScreen.SCREEN_HEIGHT));
        }
        else if(Type == 1){
            position = new Vector2(random.nextInt(GameScreen.SCREEN_WIDTH-200), GameScreen.SCREEN_HEIGHT);
        }

        Center_X = position.x + Radius_X;
        Center_Y = position.y + Radius_Y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float delta) {
        Center_X += MeteorSpeed;
        Center_Y -= MeteorSpeed;

        position.x += MeteorSpeed;
        position.y -= MeteorSpeed;

        if (!world.Dead && !world.Immortal) {
            if (mainGirl.getCenter_X() > Center_X - Radius_X && mainGirl.getCenter_X() < Center_X + Radius_X) {

                if (mainGirl.getCenter_Y() > Center_Y - Radius_Y && mainGirl.getCenter_Y() < Center_Y + Radius_Y) {
                    world.Dead = true;
                }
            }
        }
    }
}

