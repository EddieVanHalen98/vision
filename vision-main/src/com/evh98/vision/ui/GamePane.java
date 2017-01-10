/**
 * Vision
 * 
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.evh98.vision.Vision;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

public class GamePane {

	private final String url;
	String title;
	File thumbnail_file;
	Sprite thumbnail;
	int x;
	int y;
	BitmapFont font;
	
	/**
	 * Initializes a small pane object
	 */
	public GamePane(String url, String title, String thumbnail_url, int x, int y) {
		this.url = url;
		String temp_text = title;
		int limit = (int) (15 / Vision.HORIZONTAL_SCALE);
		if (temp_text.length() > limit && temp_text.length() < (limit * 2)) {
			temp_text = new StringBuilder(temp_text).insert(limit, "\n").toString();
		} else if (temp_text.length() > (limit * 2)) {
			temp_text = temp_text.substring(0, limit * 2);
			temp_text = new StringBuilder(temp_text).insert(limit, "\n").toString();
			temp_text = new StringBuilder(temp_text).insert((limit * 2) + 1, "...").toString();
		}
		this.title = temp_text;
		
		try {
			String s = title.replaceAll("/", "");
			s = s.replaceAll(" ", "_");
			thumbnail_file = new File(System.getProperty("user.home") + "/Vision/assets/temp/" + s + "_yt_thumbnail.jpg");
			FileUtils.copyURLToFile(new URL(thumbnail_url), thumbnail_file);
			this.thumbnail = Graphics.createSprite(Gdx.files.external("/Vision/assets/temp/" + s + "_yt_thumbnail.jpg"));
			thumbnail_file.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;

		font = Graphics.createFont(Graphics.font_roboto_thin, 74);
	}
	
	/*
	 * Rendering of the main pane
	 */
	public void render(SpriteBatch sprite_batch, ShapeRenderer shape_renderer) {
		// Draw thumb nail
		renderThumb(sprite_batch);
		// Draw title bar
		renderBar(shape_renderer, Palette.DARK_GRAY);
		// Draw title
		renderText(sprite_batch);
	}
	
	/*
	 * Rendering of the selected pane
	 */
	public void renderAlt(SpriteBatch sprite_batch, ShapeRenderer shape_renderer) {
		// Draw thumb nail
		renderThumb(sprite_batch);
		// Draw title bar
		renderBar(shape_renderer, Palette.RED);
		// Draw text
		renderText(sprite_batch);
	}
	
	private void renderThumb(SpriteBatch sprite_batch) {
		sprite_batch.begin();
		Graphics.drawSprite(sprite_batch, thumbnail, x, y, 720, 405);
		sprite_batch.end();
	}
	
	private void renderBar(ShapeRenderer shape_renderer, Color color) {
		shape_renderer.begin(ShapeType.Filled);
		shape_renderer.setColor(color);
		Graphics.drawRect(shape_renderer, x, y + 405, 720, 202);
		shape_renderer.end();
	}
	
	private void renderText(SpriteBatch sprite_batch) {
		sprite_batch.begin();
		Graphics.drawText(sprite_batch, font, title, x + 360, y + 506);
		sprite_batch.end();
	}

	public String getUrl() {
		return url;
	}
}