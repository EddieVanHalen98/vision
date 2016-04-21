/**
 * Vision - Created and owned by Muhammad Saeed (EddieVanHalen98)
 * 
 * SmallPane.java
 * Object class for SmallPane UI
 * 
 * File created on 20th April 2016
 */

package com.evh98.vision.ui;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Palette;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

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
		
		if (temp_text.length() > 15 && temp_text.length() < 30) {
			temp_text = new StringBuilder(temp_text).insert(15, "\n").toString();
		} else if (temp_text.length() > 30) {
			temp_text = temp_text.substring(0, 30);
			temp_text = new StringBuilder(temp_text).insert(15, "\n").toString();
			temp_text = new StringBuilder(temp_text).insert(31, "...").toString();
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
		// Draw rectangle
		gc.drawImage(thumbnail, x * Vision.SCALE, y * Vision.SCALE, 720 * Vision.SCALE, 405 * Vision.SCALE);
		gc.setFill(Palette.DARK_GRAY);
		gc.fillRect(x * Vision.SCALE, (y + 405) * Vision.SCALE, 720 * Vision.SCALE, 202 * Vision.SCALE);
		// Sets up font
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font_title);
		// Sets up font alignment
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
        // Draws text
		gc.fillText(title, (x + 360) * Vision.SCALE, (y + 506) * Vision.SCALE);
		gc.setFont(font_title);
	}
	
	/*
	 * Rendering of the selected pane
	 */
	public void renderAlt(GraphicsContext gc) {
		// Draw rectangle
		gc.drawImage(thumbnail, x * Vision.SCALE, y * Vision.SCALE, 720 * Vision.SCALE, 405 * Vision.SCALE);
		gc.setFill(Palette.RED);
		gc.fillRect(x * Vision.SCALE, (y + 405) * Vision.SCALE, 720 * Vision.SCALE, 202 * Vision.SCALE);
		// Sets up font
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font_title);
		// Sets up font alignment
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
        // Draws text
		gc.fillText(title, (x + 360) * Vision.SCALE, (y + 506) * Vision.SCALE);
		gc.setFont(font_title);		
	}

	public String getUrl() {
		return url;
	}
}