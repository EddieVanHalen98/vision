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
import javafx.scene.text.Font;

public class YouTubePane {

	private final String url;
	String title;
	Image thumbnail;
	int x;
	int y;
	Font font_title;
	
	/**
	 * Initializes a small pane object
	 */
	public YouTubePane(String url, String title, String thumbnail_url, int x, int y) {
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
		
		this.thumbnail = new Image(thumbnail_url);
		this.x = x;
		this.y = y;
		
		font_title = Font.font("Roboto Light", 74 * Vision.SCALE);
	}
	
	/*
	 * Rendering of the main pane
	 */
	public void render(GraphicsContext gc) {
		// Draw rect behind thumb nail
		gc.setFill(Palette.DARK_GRAY);
		Graphics.fillRect(gc, x, y, 720, 407);
		// Draw thumb nail
		Graphics.drawImage(gc, thumbnail, x, y, 720, 405);
		// Draw title bar
		Graphics.fillRect(gc, x, y + 405, 720, 202);
		// Draw title
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font_title);
		Graphics.text(gc, title, x + 360, y + 506);
	}
	
	/*
	 * Rendering of the selected pane
	 */
	public void renderAlt(GraphicsContext gc) {
		// Draw rect behind thumb nail
		gc.setFill(Palette.RED);
		Graphics.fillRect(gc, x, y, 720, 405);
		// Draw thumb nail
		Graphics.drawImage(gc, thumbnail, x, y, 720, 405);
		// Draw title bar
		Graphics.fillRect(gc, x, y + 405, 720, 202);
		// Draw text
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font_title);
		Graphics.text(gc, title, x + 360, y + 506);	
	}

	public String getUrl() {
		return url;
	}
}