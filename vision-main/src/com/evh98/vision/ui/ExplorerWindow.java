package com.evh98.vision.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evh98.vision.util.Palette;

public class ExplorerWindow extends Window {

	int x = -1; int y = 1;
	
	private final String path;
	private final String type;
	
	public ExplorerWindow(String path, String type, Color border) {
		super(3200, 1800, Palette.LIGHT_GRAY, border);
		this.path = path;
		this.type = type;
	}
	
	@Override
	public void draw(SpriteBatch sprite_batch) {
		
	}
	
	@Override
	public void update() {
		
	}
	
	public String getPath() {
		return path;
	}

	public String getType() {
		return type;
	}
}