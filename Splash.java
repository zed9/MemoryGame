package com.zaid.memory;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Splash implements Screen{
	private SpriteBatch sp;
	private BitmapFont fnt;
	private Game myGame;
	private String proceed;
	private int stringX;
	private int stringY;
	private OrthographicCamera camera;
	
	public Splash(Game g) {
		// TODO Auto-generated constructor stub
		myGame = g;
		ImageCache.manager.load("images/scumcards.txt", TextureAtlas.class);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(ImageCache.manager.update()){
        	sp.begin();
            fnt.draw(sp, "Click to Continue", stringX, stringY);
            sp.end();
            if (Gdx.input.isTouched()){
            	ImageCache.setUpCardAtlas();
            	myGame.setScreen(new MemoryScreen());
            }
        }
	}

	@Override
	public void resize(int width, int height) {
		camera = new OrthographicCamera(width, height);
		camera.setToOrtho(false);
		sp.setProjectionMatrix(camera.combined);
		setStringPosition(width, height);
	}

	@Override
	public void show() {
		sp = new SpriteBatch();
		fnt = new BitmapFont(Gdx.files.internal("fonts/kenney.fnt"),
		         Gdx.files.internal("fonts/kenney.png"), false);
		fnt.setColor(Color.BLACK);
		fnt.setScale(0.25f);
		proceed = "Click to Continue";
		setStringPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void setStringPosition(int width, int height){
		TextBounds proceedBounds = fnt.getBounds(proceed);
		stringX = (int) (width/2 - proceedBounds.width/2);
		stringY = (int) (height/2 + proceedBounds.height/2);
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
		fnt.dispose();
	}

}
