package com.zaid.memory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.zaid.memory.ImageCache;

public class Card {

	public static enum SUITE {
		Spades, Hearts
	}
	
	public static enum FACES {
		Seven, Eight, Nine, Ten,
		Jack, Queen, King, Ace;	
	}
	
	private Sprite frontface;
	private Sprite backface;
	private SUITE suit;
	private FACES face;
	private static int cardHeight;
	private static int cardWidth;
	private boolean isOpen = false;
	
	public Card(SUITE s, FACES f){
		this.suit = s;
		this.face = f;
		TextureRegion img = ImageCache.atlasHashMap.get("cardimages")
							.findRegion("card"+suit.name()+face.name()); 
		frontface = new Sprite(img);
		img = ImageCache.atlasHashMap.get("cardimages")
			  .findRegion("cardBack_blueFive");
		backface = new Sprite(img);
	}
	
	public void flip(){
		isOpen = !isOpen;
	}
	
	public void setSize(int w, int h){
		frontface.setSize(w, h);
		backface.setSize(w, h);
	}
	
	public boolean equals(Card x) {
		if (this.face == x.face && this.suit == x.suit)
			return true;
		return false;
	}
	
	public void draw(SpriteBatch sp){
		if (isOpen)
			frontface.draw(sp);
		else
			backface.draw(sp);
	}

	public void setPosition(int xStart, int yStart) {
		frontface.setPosition(xStart, yStart);
		backface.setPosition(xStart, yStart);
	}
	
	public FACES getface(){
		return this.face;
	}

	public static int getCardHeight() {
		return cardHeight;
	}

	public static void setCardHeight(int cardHeight) {
		Card.cardHeight = cardHeight;
	}

	public static int getCardWidth() {
		return cardWidth;
	}

	public static void setCardWidth(int cardWidth) {
		Card.cardWidth = cardWidth;
	}
	
}
