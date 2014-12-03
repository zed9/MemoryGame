package com.zaid.memory;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.zaid.memory.Card.FACES;
import com.zaid.memory.Card.SUITE;

public class Deck {
	private ArrayList<Card> cards;
	private int cardsLeft;
	private int startX;
	private int startY;
	private int buffer;
	private int firstCard = -1;
	
	public Deck() {
		cards = new ArrayList<Card>();
	}
	
	public boolean deckEmpty(){
		return cardsLeft == 0;
	}
	
	public void draw(SpriteBatch sp){
		for (Card c : cards){
			if (c != null)
				c.draw(sp);
		}
	}
	
	public void resetDeck(){
		cards.clear();
		for (FACES face : FACES.values()){
			cards.add(new Card(SUITE.Spades, face));
			cards.add(new Card(SUITE.Hearts, face));
		}
		Collections.shuffle(cards);
		cardsLeft = 16;
		int posX = startX;
		int posY = startY;
		int cardW = Card.getCardWidth();
		int cardH = Card.getCardHeight();
		int i = 0;
		for(Card c : cards){
			c.setPosition(posX, posY);
			c.setSize(cardW, cardH);
			posX += buffer + cardW;
			if (i++ == 3){
				i = 0;
				posX = startX;
				posY = posY + cardH + buffer;
			}
		}
	}
	
	public void setDimensions(int buffer, int cardW, int cardH, int X, int Y){
		this.startX = X;
		this.startY = Y;
		this.buffer = buffer;
		Card.setCardWidth(cardW);
		Card.setCardHeight(cardH);
	}

	public void flipCardNo(int i) {
		cards.get(i).flip();
	}
	
	public void playCardAtPosition(Vector3 touch) {
		//Find the position of the card in a 4x4 matrix
		//better than an isClicked() for the whole cards ArrayList
		int cardNoX = (int) (touch.x-startX) / (Card.getCardWidth() + buffer);
		int cardNoY = (int) (touch.y-startY) / (Card.getCardHeight() + buffer);
		final int i = cardNoX + (4* cardNoY);
		final Card c = cards.get(i);
		//Null cards are held for consistency of matrix
		if (c != null){
			//Check if card is already open
			if (firstCard != i){
				c.flip();
				if (firstCard == -1){
					firstCard = i;
				}
				else{
					//used to flip old cards, with a time delay
					final int oldCard = firstCard;
					//Both cards are same
					if( c.getface() == cards.get(firstCard).getface()){
							Timer.schedule(new Timer.Task() {
							
							@Override
							public void run() {
								cards.set(i, null);
								cards.set(oldCard, null);
								cardsLeft -= 2;
								}
							},0.5f);
					
					}
					//Cards are different
					else{
							Timer.schedule(new Timer.Task() {
							
							@Override
							public void run() {
								c.flip();
								cards.get(oldCard).flip();
							}
							},0.5f);
						}
					firstCard = -1;
				}
			}
		}
	}
}
