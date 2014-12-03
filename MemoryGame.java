package com.zaid.memory;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MemoryGame extends Game {
	SpriteBatch batch;
	Texture img;
	
	
	@Override
	public void create () {
		this.setScreen(new Splash(this));
	}
}
