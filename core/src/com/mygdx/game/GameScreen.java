package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter{
	public static final int SCREEN_WIDTH = 1100;
	public static final int SCREEN_HEIGHT = 650;

	public static int GameStatus = 0;
	
	World world;
	
	private DisastarGame disastarGame;
	
	private Texture heroImg;
	
	private MainGirl mainGirl;

	private WorldRenderer worldRenderer;
	
	public GameScreen(DisastarGame disastarGame) {
		this.disastarGame = disastarGame;
		
		world = new World(disastarGame);
		worldRenderer = new WorldRenderer(disastarGame, world);
		
		heroImg = new Texture("pacman.png");
	}
	
	private void update(float delta) {

	    //Title Screen
	    if(GameStatus == 0) {
            if (Gdx.input.isKeyPressed((Keys.SPACE))) {
                GameStatus = 1;                             //Go to Tutorial Screen
            }
        }

        //Tutorial Screen
        if(GameStatus == 1){
            if (Gdx.input.isKeyPressed((Keys.ENTER))) {
                GameStatus = 2;                             //Go to Playing Screen
            }
        }

        //Playing Screen
        if(GameStatus == 2) {
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				world.getMainGirl().move(MainGirl.DIRECTION_LEFT);
			}
			if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				world.getMainGirl().move(MainGirl.DIRECTION_RIGHT);
			}
			if (Gdx.input.isKeyPressed(Keys.UP)) {
				world.getMainGirl().move(MainGirl.DIRECTION_UP);
			}
			if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				world.getMainGirl().move(MainGirl.DIRECTION_DOWN);
			}

			if (Gdx.input.isKeyPressed(Keys.SPACE)) {
				world.getMainGirl().holdSpeed(true);
			} else {
				world.getMainGirl().holdSpeed(false);
			}
		}

		//Ending Screen
        if(GameStatus == 3){
            if (Gdx.input.isKeyPressed((Keys.ENTER))) {
                GameStatus = 0;                             //Go to Title Screen
            }
        }
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	    update(delta);

	    world.update(delta);
		worldRenderer.render(delta);
	 }

}
