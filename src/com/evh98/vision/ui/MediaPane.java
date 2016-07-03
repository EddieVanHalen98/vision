/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
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
	int x;
	int y;
	
	/**
	 * Initialises a small pane object
	 */
	public MediaPane(Color screen_color, int x, int y) {
		this.screen_color = screen_color;
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Rendering of the main pane
	 */
	public void render(GraphicsContext gc, Image poster) {
		// Draw poster
		Graphics.drawImage(gc, poster, x, y, 500, 740);
	}
	
	/*
	 * Rendering of the selected pane
	 */
	public void renderAlt(GraphicsContext gc, Image poster) {
		// Draw poster
		Graphics.drawImage(gc, poster, x, y, 500, 740);
		// Draw selected rect
		gc.setStroke(screen_color);
		Graphics.strokeRect(gc, x, y, 500, 740);
	}
}