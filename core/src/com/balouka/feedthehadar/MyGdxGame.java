package com.balouka.feedthehadar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.org.apache.regexp.internal.RE;

import static com.badlogic.gdx.Gdx.graphics;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor
{

	private Background background;
	private MyFont font;

	Preferences prefs;		// the high score
	private int highScore;

	// Hadar
	Hadar hadar;	// the Hadar object
	int posX;		// the Hadar image X position
	int posY;		// the Hadar image Y position

	// the vegan food
	VeganFood food1;
	VeganFood food2;


	private int scoreCounter;	// the score
	private int mone1 = 0; 			// counts the first food
	private int mone2 = 0; 			// counts the first food
	private float timePast;

	// game flags
	private String gameStatus = "beforeGame";



	@Override
	public void create ()
	{
		//o the high score
		prefs = Gdx.app.getPreferences("highScore1");
		highScore = prefs.getInteger("highScore1");

		background = new Background();
		font = new MyFont();

		// setting Hadar Stats
		hadar = new Hadar();
		posY = 0;
		posX = Gdx.graphics.getWidth()/2 - hadar.getWidth() / 2;

		scoreCounter = 0;
		timePast = 0;
		food1 = new VeganFood();
		food2 = new VeganFood();
		food2.setSpeed(7);
	}



	@Override
	public void render ()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.input.setInputProcessor(this);		//o setting the input from the phone (pressing the screen)
		timePast += graphics.getDeltaTime();

		background.drawBackground();

		if (gameStatus.equals("beforeGame"))
			font.drawStart();

		if (gameStatus.equals("inGame"))
			font.drawScore("Food Eaten: " + scoreCounter);

		if (gameStatus.equals("lost"))
		{
			//o put the new score as the new high score
			if (scoreCounter > highScore)
			{
				highScore = scoreCounter;
				prefs.putInteger("highScore1", scoreCounter);
			}

			//o update your preferences
			prefs.flush();
			font.drawLost(scoreCounter, highScore);
		}

		if (gameStatus.equals("inGame"))
		{
			hadar.drawHadar(posX, timePast);

			mone1 = food1.drawVeganFood(posX, mone1);
			mone2 = food2.drawVeganFood(posX, mone2);

			if (mone1 >= 0 && mone2 >= 0)
				scoreCounter = mone1 + mone2;
			else
				gameStatus = "lost";
		}

		//System.out.println(gameStatus);


	}






	public void resetGame()
	{
		scoreCounter = 0;
		gameStatus = "inGame";
		mone1 = 0;
		mone2 = 0;
		food1.resetVeganFood();
		food2.resetVeganFood();
	}

	@Override
	public void dispose ()
	{
		// Hadar
		hadar.getBatch().dispose();
		hadar.getAtlas().dispose();
		food1.getBatch().dispose();
		food1.getVeganFood().dispose();
	}


	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		posX = screenX - hadar.getWidth()/2;
		posY = Gdx.graphics.getHeight() - screenY;

		if (gameStatus.equals("lost") || gameStatus.equals("beforeGame"))
			resetGame();

		return true;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		posX = screenX - hadar.getWidth()/2;
		posY = Gdx.graphics.getHeight() - screenY;
		return true;
	}


	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}