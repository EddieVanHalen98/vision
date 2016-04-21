/**
 * Vision - Created and owned by Muhammad Saeed (EddieVanHalen98)
 * 
 * Pane.java
 * Object class for Pane UI
 * 
 * File created on 19th April 2016
 */

package com.evh98.vision.ui;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Palette;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

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
		gc.fillRect(x * Vision.SCALE, y * Vision.SCALE, 1536 * Vision.SCALE, 768 * Vision.SCALE);
		// Sets up font
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font_text);
		// Sets up font alignment
		gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        // Draws text and icon
		gc.fillText(text, (x + 768) * Vision.SCALE, (y + 532) * Vision.SCALE);
		gc.setFont(font_icon);
		gc.fillText(icon + "", (x + 768) * Vision.SCALE, (y + 232) * Vision.SCALE);
	}
	
	/*
	 * Rendering of the selected pane
	 */
	public void renderAlt(GraphicsContext gc) {
		// Draw rectangle
		gc.setStroke(color);
		gc.strokeRect(x * Vision.SCALE, y * Vision.SCALE, 1536 * Vision.SCALE, 768 * Vision.SCALE);
		// Sets up font
		gc.setFill(color);
		gc.setFont(font_text);
		// Sets up font alignment
		gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        // Draws text and icon
		gc.fillText(text, (x + 768) * Vision.SCALE, (y + 532) * Vision.SCALE);
		gc.setFont(font_icon);
		gc.fillText(icon + "", (x + 768) * Vision.SCALE, (y + 232) * Vision.SCALE);
	}
}