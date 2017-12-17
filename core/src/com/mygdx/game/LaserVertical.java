package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import sun.applet.Main;

public class LaserVertical {

    private float ImageSize_X = 17;
    private float ImageSize_Y = 475;

    private float Radius_X = ImageSize_X/2;
    private float Radius_Y = ImageSize_Y/2;

    private float Center_X;
    private float Center_Y;

    private Vector2 position;

    private MainGirl mainGirl;

    public static int LaserSpeed = 5;

    private World world;

    public LaserVertical(World world, int x, int y) {
        this.world = world;
        position = new Vector2(x, y);

        Center_X = Radius_X + position.x;
        Center_Y = Radius_Y + position.y;

        mainGirl = world.getMainGirl();
    }
    public void Move(){
        position.y -= LaserSpeed;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float delta){
        Center_Y -= LaserSpeed;

        // Check the Radius of MainGirl and Laser

        if(!world.Dead && !world.Immortal) {
            if (mainGirl.getCenter_X() > Center_X - Radius_X && mainGirl.getCenter_X() < Center_X + Radius_X) {

                if (mainGirl.getCenter_Y() > Center_Y - Radius_Y && mainGirl.getCenter_Y() < Center_Y + Radius_Y) {
                    world.Dead = true;
                }
            }
        }
    }
}

