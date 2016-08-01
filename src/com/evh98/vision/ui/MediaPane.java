/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 * 
 * SmallPane.java
 * Object class for SmallPane UI
 * 
 * File created on 20th April 2016
 */

package com.evh98.vision.ui;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MediaPane {

	private final Color screen_color;
	private final String text;
	private final int x;
	private final int y;
	private Font font;
	
	/**
	 * Initialises a small pane object
	 */
	public MediaPane(Color screen_color, String text, int x, int y) {
		this.screen_color = screen_color;
		
		String temp_text = text;
		int limit = (int) (15 / Vision.HORIZONTAL_SCALE);
		font = Font.font("Roboto Thin", 100 * Vision.SCALE);
		if (temp_text.length() > limit && temp_text.length() < (limit * 2)) {
			temp_text = new StringBuilder(temp_text).insert(limit, "\n").toString();
		} else if (temp_text.length() > (limit * 2)) {
			temp_text = temp_text.substring(0, limit * 2);
			temp_text = new StringBuilder(temp_text).insert(limit, "\n").toString();
			temp_text = new StringBuilder(temp_text).insert((limit * 2) + 1, "...").toString();
		}
		this.text = temp_text;
		
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Rendering of the main pane
	 */
	public void render(GraphicsContext gc, Image poster) {
		// Draw poster
		Graphics.drawImage(gc, poster, x, y, 730, 1082);
		// Draw title
		gc.setFill(Palette.DARK_GRAY);
		Graphics.fillRect(gc, x, y + 1080, 730, 260);
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font);
		Graphics.text(gc, text, x + 365, y + 1210);
	}
	
	/**
	 * Render main pane but provide x axis rather than using panes' x axis
	 * @param gc
	 * @param poster
	 * @param x
	 */
	public void render(GraphicsContext gc, Image poster, float x) {
		// Draw poster
		Graphics.drawImage(gc, poster, x, y, 730, 1082);
		// Draw title
		gc.setFill(Palette.DARK_GRAY);
		Graphics.fillRect(gc, x, y + 1080, 730, 260);
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font);
		Graphics.text(gc, text, x + 365, y + 1210);
	}
	
	/*
	 * Rendering of the selected pane
	 */
	public void renderAlt(GraphicsContext gc, Image poster) {
		// Draw poster
		Graphics.drawImage(gc, poster, x, y, 730, 1082);
		// Draw title
		gc.setFill(screen_color);
		Graphics.fillRect(gc, x, y + 1080, 730, 260);
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font);
		Graphics.text(gc, text, x + 365, y + 1210);
	}
	
	public void renderAlt(GraphicsContext gc, Image poster, float x) {
		// Draw poster
		Graphics.drawImage(gc, poster, x, y, 730, 1082);
		// Draw title
		gc.setFill(screen_color);
		Graphics.fillRect(gc, x, y + 1080, 730, 260);
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font);
		Graphics.text(gc, text, x + 365, y + 1210);
	}
}