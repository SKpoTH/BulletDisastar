package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class BadGirl {
	private Vector2 position;

	public BadGirl(int x, int y) {
		position = new Vector2(x, y);
	}

	public Vector2 getPosition() {
		return position;
	}
}
