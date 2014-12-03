package com.zaid.memory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MemoryScreen implements Screen, GestureListener {
	private SpriteBatch sp;
	private Deck gameDeck;
	private OrthographicCamera camera;
	
	public MemoryScreen() {
		gameDeck = new Deck();
		Gdx.input.setInputProcessor(new GestureDetector(this));
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sp.begin();
		gameDeck.draw(sp);
		sp.end();
	}

	@Override
	public void resize(int width, int height) {
		//Max Card h = 190
		//Max Card w = 140
		//Card height is 20% of the screen size, width is scaled proportionately
		camera = new OrthographicCamera(width, height);
		camera.setToOrtho(false);
		sp.setProjectionMatrix(camera.combined);
		//Calculations to allow for any screen size
		//Assuming landscape mode ( width > height )
		int cardHeight = (int) (height * 0.2);
		if (cardHeight > 190){
			cardHeight = 190;
		}
		int cardWidth = cardHeight * 140 / 190;
		int buffer = (int) (cardHeight/5);
		int xStart = (int) ((width/2) - (2 * cardWidth) - (1.5 * buffer));
		int yStart = (int) ((height/2) - (2 * cardHeight) - (1.5 * buffer));
		gameDeck.setDimensions(buffer, cardWidth, cardHeight, xStart, yStart);
		//Create and shuffle the deck
		gameDeck.resetDeck();
		
	}
	
	@Override
	public void show() {
		sp = new SpriteBatch();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		sp.dispose();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		Vector3 touch = new Vector3(x,y,0);
		camera.unproject(touch);
		gameDeck.playCardAtPosition(touch);
		if (gameDeck.deckEmpty()){
			gameDeck.resetDeck();
		}
		return true;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
