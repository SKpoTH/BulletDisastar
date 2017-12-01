package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.DisastarGame;
import com.mygdx.game.GameScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = GameScreen.SCREEN_WIDTH;
        config.height = GameScreen.SCREEN_HEIGHT;
        new LwjglApplication(new DisastarGame(), config);
	}
}