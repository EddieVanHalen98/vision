package com.evh98.vision.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;

public class MetaWindow extends Window {

	private final String[] meta;
	private String linearMeta;
	
	private final BitmapFont font;
	
	public MetaWindow(Color color, Color borderColor, String[] meta) {
		super(color, borderColor);
		
		this.meta = meta;
		for (String s : meta) {
			this.linearMeta += s + "\n";
		}
		this.linearMeta = this.linearMeta.substring(4, this.linearMeta.length());
		font = Graphics.createFont(Graphics.font_roboto_light, 128);
		font.setColor(borderColor);
	}

	@Override
	public void draw(SpriteBatch sprite_batch) {
		sprite_batch.begin();
			Graphics.drawText(sprite_batch, font, linearMeta, 0, 0);
		sprite_batch.end();
	}
	
	@Override
	public void update() {
		if (Controller.isRed() || Controller.isYellow()) {
			setInactive();
		}
	}

	public String[] getMeta() {
		return meta;
	}
}