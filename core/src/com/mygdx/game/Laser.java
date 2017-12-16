package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class Laser {
	private Vector2 position;

	public Laser(int x, int y) {
		position = new Vector2(x, y);
	}

	public void MoveHorizontal(){
	    position.x += 10;
    }

	public void MoveVertical(){
		position.y -= 10;
	}

	public Vector2 getPosition() {
		return position;
	}
}
