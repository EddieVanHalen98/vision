/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 *
 * MediaScreen.java
 * Media screen
 *
 * File created on 28th June 2016
 */

package com.evh98.vision.ui;

import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SearchResult {

	private final Color color;
	private final String icon;
	private final String result;
	
	public SearchResult(Color color, char icon, String result) {
		this.color = color;
		this.icon = String.valueOf(icon);
		this.result = result;
	}

	public void render(GraphicsContext gc, Font font, Font iconFont, int y) {
		gc.setFill(Palette.LIGHT_GRAY);
		Graphics.fillRect(gc, -1920, y, 3840, 256);
		gc.setFill(color);
		gc.setFont(iconFont);
		Graphics.text(gc, icon, -1760, y);
		gc.setFont(font);
		Graphics.text(gc, result, 0, y);
	}

	public void renderAlt(GraphicsContext gc, Font font, Font iconFont, int y) {
		gc.setFill(Palette.LIGHT_GRAY);
		Graphics.fillRect(gc, -1920, y, 3840, 256);
		gc.setFill(Palette.DARK_GRAY);
		gc.setFont(iconFont);
		Graphics.text(gc, icon, -1760, y);
		gc.setFont(font);
		Graphics.text(gc, result, 0, y);
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getIcon() {
		return icon;
	}

	public String getResult() {
		return result;
	}
}