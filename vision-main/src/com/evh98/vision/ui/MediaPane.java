/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 * 
 * SmallPane.java
 * Object class for SmallPane UI
 * 
 * File created on 20th April 2016
 */

package com.evh98.vision.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.evh98.vision.Vision;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

public class MediaPane {

	private final Color screen_color;
	private final String text;
	private final Sprite poster;
	private BitmapFont font;
	
	/**
	 * Initialises a small pane object
	 */
	public MediaPane(Color screen_color, String text, Sprite poster) {
		this.screen_color = screen_color;
		
		String temp_text = text;
		int limit = (int) (15 / Vision.HORIZONTAL_SCALE);
		if (temp_text.length() > limit && temp_text.length() < (limit * 2)) {
			temp_text = new StringBuilder(temp_text).insert(limit, "\n").toString();
		} else if (temp_text.length() > (limit * 2)) {
			temp_text = temp_text.substring(0, limit * 2);
			temp_text = new StringBuilder(temp_text).insert(limit, "\n").toString();
			temp_text = new StringBuilder(temp_text).insert((limit * 2) + 1, "...").toString();
		}
		this.text = temp_text;
		this.poster = poster;

		font = Graphics.createFont(Graphics.font_roboto_thin, 100);
	}
	
	public void render(SpriteBatch sprite_batch, ShapeRenderer shape_renderer, float x) {
		// Draw poster
		drawPoster(sprite_batch, poster, x);
		// Draw title
		drawTitle(sprite_batch, shape_renderer, Palette.DARK_GRAY, x);
	}
	
	public void renderAlt(SpriteBatch sprite_batch, ShapeRenderer shape_renderer, float x) {
		// Draw poster
		drawPoster(sprite_batch, poster, x);
		// Draw title
		drawTitle(sprite_batch, shape_renderer, screen_color, x);
	}
	
	private void drawPoster(SpriteBatch sprite_batch, Sprite poster, float x) {
		sprite_batch.begin();
		Graphics.drawSprite(sprite_batch, poster, x, -670, 730, 1082);
		sprite_batch.end();
	}
	
	private void drawTitle(SpriteBatch sprite_batch, ShapeRenderer shape_renderer, Color color, float x) {
		shape_renderer.begin(ShapeType.Filled);
		shape_renderer.setColor(color);
		Graphics.drawRect(shape_renderer, x, 410, 730, 260);
		shape_renderer.end();
		sprite_batch.begin();
		Graphics.drawText(sprite_batch, font, text, x + 365, 540);
		sprite_batch.end();
	}
}