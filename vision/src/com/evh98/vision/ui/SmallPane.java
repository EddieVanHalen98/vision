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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class SmallPane {

	Color screen_color;
	Color pane_color;
	String text;
	char icon;
	int x;
	int y;
	Font font_text;
	Font font_icon;
	
	/**
	 * Initializes a small pane object
	 */
	public SmallPane(Color screen_color, Color pane_color, String text, String iconpack, char icon, int x, int y) {
		this.screen_color = screen_color;
		this.pane_color = pane_color;
		this.text = text;
		this.icon = icon;
		this.x = x;
		this.y = y;
		
		font_text = Font.font("Roboto Thin", 108 * Vision.SCALE);
		font_icon = Font.font(iconpack, 300 * Vision.SCALE);
	}
	
	/*
	 * Rendering of the main pane
	 */
	public void render(GraphicsContext gc) {
		// Draw rectangle
		gc.setFill(pane_color);
		gc.fillRect(x * Vision.SCALE, y * Vision.SCALE, 720 * Vision.SCALE, 542 * Vision.SCALE);
		gc.setFill(Palette.DARK_GRAY);
		gc.fillRect(x * Vision.SCALE, (y + 540) * Vision.SCALE, 720 * Vision.SCALE, 180 * Vision.SCALE);
		// Sets up font
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font_text);
		// Sets up font alignment
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
        // Draws text and icon
		gc.fillText(text, (x + 360) * Vision.SCALE, (y + 630) * Vision.SCALE);
		gc.setFont(font_icon);
		gc.fillText(icon + "", (x + 360) * Vision.SCALE, (y + 270) * Vision.SCALE);
	}
	
	/*
	 * Rendering of the selected pane
	 */
	public void renderAlt(GraphicsContext gc) {
		// Draw rectangle
		gc.setFill(pane_color);
		gc.fillRect(x * Vision.SCALE, y * Vision.SCALE, 720 * Vision.SCALE, 542 * Vision.SCALE);
		gc.setFill(screen_color);
		gc.fillRect(x * Vision.SCALE, (y + 540) * Vision.SCALE, 720 * Vision.SCALE, 180 * Vision.SCALE);
		// Sets up font
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font_text);
		// Sets up font alignment
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
        // Draws text and icon
		gc.fillText(text, (x + 360) * Vision.SCALE, (y + 630) * Vision.SCALE);
		gc.setFont(font_icon);
		gc.fillText(icon + "", (x + 360) * Vision.SCALE, (y + 270) * Vision.SCALE);		
	}
}