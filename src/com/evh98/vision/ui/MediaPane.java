/**
 * Vision - Created and owned by Muhammad Saeed (EddieVanHalen98)
 * 
 * SmallPane.java
 * Object class for SmallPane UI
 * 
 * File created on 20th April 2016
 */

package com.evh98.vision.ui;

import java.io.File;

import com.evh98.vision.util.Graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MediaPane {

	Color screen_color;
	Image poster;
	int x;
	int y;
	private final File path;
	
	/**
	 * Initialises a small pane object
	 */
	public MediaPane(Color screen_color, String poster, int x, int y, File path) {
		this.screen_color = screen_color;
		this.poster = new Image(poster);
		this.x = x;
		this.y = y;
		this.path = path;
	}
	
	/*
	 * Rendering of the main pane
	 */
	public void render(GraphicsContext gc) {
		// Draw poster
		Graphics.drawImage(gc, poster, x, y, 500, 740);
	}
	
	/*
	 * Rendering of the selected pane
	 */
	public void renderAlt(GraphicsContext gc) {
		// Draw poster
		Graphics.drawImage(gc, poster, x, y, 500, 740);
		// Draw selected rect
		gc.setStroke(screen_color);
		Graphics.strokeRect(gc, x, y, 500, 740);
	}

	public File getPath() {
		return path;
	}
}