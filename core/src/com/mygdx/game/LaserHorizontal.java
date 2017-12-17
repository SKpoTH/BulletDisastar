package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import sun.applet.Main;

public class LaserHorizontal {

	private float ImageSize_X = 475;
	private float ImageSize_Y = 17;

	private float Radius_X = ImageSize_X/2;
	private float Radius_Y = ImageSize_Y/2;

	private float Center_X;
	private float Center_Y;

	private Vector2 position;

	private MainGirl mainGirl;

	private int LaserSpeed = 7;

	private World world;

	public LaserHorizontal(World world, int x, int y) {
	    this.world = world;
		position = new Vector2(x, y);
        Center_X = position.x + Radius_X;
        Center_Y = position.y + Radius_Y;

		mainGirl = world.getMainGirl();
	}

	public void Move(){
	    position.x += LaserSpeed;
    }

	public Vector2 getPosition() {
		return position;
	}

	public void update(float delta){
	    Center_X += LaserSpeed;

	    if(!world.Dead) {
            if (mainGirl.getCenter_X() > Center_X - Radius_X && mainGirl.getCenter_X() < Center_X + Radius_X) {

                if (mainGirl.getCenter_Y() > Center_Y - Radius_Y && mainGirl.getCenter_Y() < Center_Y + Radius_Y) {
                    world.Dead = true;
                }
            }
        }
	}
}
