package com.evh98.vision.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Icons;
import com.evh98.vision.util.Palette;

public class ExplorerFile {

	private final String path;
	private final String name;
	private final Sprite type;
	private final BitmapFont font;
	private final int x;
	private final int y;
	
	public ExplorerFile(String path, Sprite type, BitmapFont font, int[] position) {
		this.path = path;
		this.name = path.substring(path.lastIndexOf("/"), path.length());
		this.type = type;
		this.font = font;
		this.x = ((position[0] - 1) * 640) - 1920;
		this.y = ((position[1] - 1) * 450) - 1080;
	}
	
	public void draw(SpriteBatch sprite_batch) {
		drawIcon(sprite_batch, Palette.DARK_GRAY);
	}
	
	public void drawSelected(SpriteBatch sprite_batch) {
		if (type == Icons.GAMES) {
			
		}
	}
	
	private void drawIcon(SpriteBatch sprite_batch, Color color) {
		sprite_batch.begin();
			Graphics.drawSprite(sprite_batch, type, x + 320, y + 161, color);
		sprite_batch.end();
	}
	
	private void drawText(SpriteBatch sprite_batch) {
		sprite_batch.begin();
			Graphics.drawText(sprite_batch, font, name, x + 320, y + 387);
		sprite_batch.end();
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public Sprite getType() {
		return type;
	}
}