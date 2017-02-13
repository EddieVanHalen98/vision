package com.evh98.vision.ui;

import java.io.File;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

public class ExplorerFile {

	private final File file;
	private final Sprite type;
	private final BitmapFont font;
	private final int x;
	private final int y;
	
	public ExplorerFile(File file, Sprite type, BitmapFont font, int[] position) {
		this.file = file;
		this.type = type;
		this.font = font;
		this.x = ((position[0] - 1) * 640) - 1600;
		this.y = ((position[1] - 1) * 450) - 855;
	}
	
	public void draw(SpriteBatch sprite_batch) {
		drawIcon(sprite_batch, Palette.DARK_GRAY);
		drawText(sprite_batch);
	}
	
	public void drawSelected(SpriteBatch sprite_batch, Color color) {
		drawIcon(sprite_batch, color);
		drawText(sprite_batch);
	}
	
	private void drawIcon(SpriteBatch sprite_batch, Color color) {
		sprite_batch.begin();
			Graphics.drawSprite(sprite_batch, type, x + 320, y + 112, color);
		sprite_batch.end();
	}
	
	private void drawText(SpriteBatch sprite_batch) {
		sprite_batch.begin();
			font.setColor(Color.DARK_GRAY);
			Graphics.drawText(sprite_batch, font, file.getName(), x + 320, y + 304);
		sprite_batch.end();
	}

	public File getFile() {
		return file;
	}

	public Sprite getType() {
		return type;
	}
}