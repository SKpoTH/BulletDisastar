package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class MainGirl {
	private Vector2 position;
	
	private int Speed = 10;
	
	public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_LEFT = 4;
    public static final int DIRECTION_STILL = 0;
	
	public MainGirl(int x, int y) {
		position = new Vector2(x,y);
    }    
 
    public Vector2 getPosition() {
		return position;
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
    	else if(position.x > GameScreen.SCREEN_WIDTH-40)
    		position.x -= 1*Speed;
    	if(position.y < 0)
    		position.y += 1*Speed;
    	else if(position.y > GameScreen.SCREEN_HEIGHT-40)
    		position.y -= 1*Speed;
    	
    }
    public void holdSpeed(boolean hold) {
    	if(hold)
    		Speed = 5;
    	else 
    		Speed = 10;
    		
    }
    
}
