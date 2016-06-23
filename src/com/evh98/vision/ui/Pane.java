/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 * 
 * Pane.java
 * Object class for Pane UI
 * 
 * File created on 19th April 2016
 */

package com.evh98.vision.ui;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Pane {
	
	Color color;
	String text;
	char icon;
	int x;
	int y;
	Font font_text;
	Font font_icon;
	
	/**
	 * Initializes a pane object
	 */
	public Pane(Color color, String text, char icon, int x, int y) {
		this.color = color;
		this.text = text;
		this.icon = icon;
		this.x = x;
		this.y = y;
		
		font_text = Font.font("Roboto Thin", 192 * Vision.SCALE);
		font_icon = Font.font("Material-Design-Iconic-Font", 300 * Vision.SCALE);
	}
	
	/*
	 * Rendering of the main pane
	 */
	public void render(GraphicsContext gc) {
		// Draw rectangle
		gc.setFill(color);
		Graphics.fillRect(gc, x, y, 1536, 768);
		// Draw title
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font_text);
		Graphics.text(gc, text, x + 768, y + 532);
		// Draw icon
		gc.setFont(font_icon);
		Graphics.text(gc, icon + "", x + 768, y + 232);
	}
	
	/*
	 * Rendering of the selected pane
	 */
	public void renderAlt(GraphicsContext gc) {
		// Draw rectangle
		gc.setFill(Palette.LIGHT_GRAY);
		Graphics.fillRect(gc, x, y, 1536, 768);
		gc.setStroke(color);
		Graphics.strokeRect(gc, x, y, 1536, 768);
		// Draw title
		gc.setFill(color);
		gc.setFont(font_text);
		Graphics.text(gc, text, x + 768, y + 532);
		// Draw icon
		gc.setFont(font_icon);
		Graphics.text(gc, icon + "", x + 768, y + 232);
	}
}