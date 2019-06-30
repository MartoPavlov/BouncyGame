package com.mygdx.bauncynova;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.bauncynova.assets.Assets;
import com.mygdx.bauncynova.screen.GameScreen;
import com.mygdx.bauncynova.screen.LoadingScreen;
import com.mygdx.bauncynova.screen.MenuScreen;
import com.mygdx.bauncynova.world.GameWorld;

public class BouncyNova extends Game {

	public static float WIDTH = 2520;
	public static float HEIGHT = 4160;
	public static int highscore = 0;
	private Preferences preferences;
	public Assets assets;

	@Override
	public void create () {
		assets = new Assets();
		this.setScreen(new LoadingScreen(this));
		preferences = Gdx.app.getPreferences("highscorePreferences");
		if (preferences.contains("highscore"))
		{
			highscore = preferences.getInteger("highscore");
		}
		else
		{
			updateHighscore(0);
			highscore = 0;
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();

	}
	
	@Override
	public void dispose () {
		this.assets.dispose();
		super.dispose();
	}

	public void updateHighscore(int newHighscore){
		preferences.putInteger("highscore",newHighscore);
		preferences.flush();
		highscore = newHighscore;
	}
}
