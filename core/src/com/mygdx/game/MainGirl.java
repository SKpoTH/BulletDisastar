package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class MainGirl {

	private float ImageSize_X = 20;
	private float ImageSize_Y = 20;

	private float Radius_X = ImageSize_X/2;
	private float Radius_Y = ImageSize_Y/2;

	private float Center_X;
	private float Center_Y;

	private Vector2 position;
	
	private int Speed = 7;
	
	public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_LEFT = 4;
    public static final int DIRECTION_STILL = 0;

    private World world;
	
	public MainGirl(World world, int x, int y) {
		position = new Vector2(x,y);

		this.world = world;
    }    
 
    public Vector2 getPosition() {
		return position;
    }

    public float getCenter_X(){
	    return position.x + Radius_X;
    }
    public float getCenter_Y(){
	    return position.y + Radius_Y;
    }

    public void move(int dir) { 
    	if(dir == DIRECTION_UP)
            position.y += 1*Speed;
    	if(dir == DIRECTION_DOWN)
            position.y -= 1*Speed;
    	if(dir == DIRECTION_LEFT)
            position.x -= 1*Speed;
    	if(dir == DIRECTION_RIGHT)
            position.x += 1*Speed;
    	
    	if(position.x < 0)
    		position.x += 1*Speed;
    	else if(position.x > ((GameScreen.SCREEN_WIDTH)-200)-20)
    		position.x -= 1*Speed;
    	if(position.y < 0)
    		position.y += 1*Speed;
    	else if(position.y > GameScreen.SCREEN_HEIGHT-20)
    		position.y -= 1*Speed;
    	
    }
    public void holdSpeed(boolean hold) {
    	if(hold)
    		Speed = 3;
    	else 
    		Speed = 7;
    }
    public void ReviveGirl(){
	    position.x = ((GameScreen.SCREEN_WIDTH)-200)/2;
	    position.y = (GameScreen.SCREEN_HEIGHT)/2;
	}
}
