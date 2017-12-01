package com.mygdx.game;

import java.util.ArrayList;

public class World {
	private MainGirl mainGirl;

	private BadGirl badGirl;

	private DisastarGame disastarGame;

	public ArrayList<BadGirl> badGirl_list = new ArrayList<BadGirl>();
	
	World(DisastarGame disastarGame) {
		 this.disastarGame = disastarGame;
		 mainGirl = new MainGirl(GameScreen.SCREEN_WIDTH/2,GameScreen.SCREEN_HEIGHT/2);
	}

	MainGirl getMainGirl() {
		return mainGirl;
	}

	public void update(float delta)
	{
		
	}

}
