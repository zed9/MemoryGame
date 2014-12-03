package com.zaid.memory;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ImageCache {
	public static AssetManager manager = new AssetManager();
	public static HashMap<String, TextureAtlas> atlasHashMap= new HashMap<String, TextureAtlas>();
	
	public static void setUpCardAtlas(){
		atlasHashMap.put("cardimages", manager.get("images/scumcards.txt", TextureAtlas.class));
	}
	
}
