package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class YinYang {
    private Random random;

    private float ImageSize_X = 50;
    private float ImageSize_Y = 50;

    private float Radius_X = ImageSize_X/2;
    private float Radius_Y = ImageSize_Y/2;

    private float Center_X;
    private float Center_Y;

    private Vector2 position;

    private MainGirl mainGirl;

    private World world;

    public YinYang(World world){
        random = new Random();

        mainGirl = world.getMainGirl();

        position = new Vector2(random.nextInt(GameScreen.SCREEN_WIDTH-200), random.nextInt(GameScreen.SCREEN_HEIGHT));

        this.world = world;

        Center_X = position.x + Radius_X;
        Center_Y = position.y + Radius_Y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float delta)
    {
        if(!world.Dead) {
            if (mainGirl.getCenter_X() > Center_X - Radius_X && mainGirl.getCenter_X() < Center_X + Radius_X) {

                if (mainGirl.getCenter_Y() > Center_Y - Radius_Y && mainGirl.getCenter_Y() < Center_Y + Radius_Y) {
                    world.GetLife = true;

                }
            }
        }
    }
}
