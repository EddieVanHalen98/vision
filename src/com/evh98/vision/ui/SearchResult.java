/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 *
 * MediaScreen.java
 * Media screen
 *
 * File created on 28th June 2016
 */

package com.evh98.vision.ui;

import java.awt.Desktop;
import java.io.IOException;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Icons;
import com.evh98.vision.util.Palette;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SearchResult {

	private final Color color;
	private final String icon;
	private final String result;
	private final int position;
	
	public SearchResult(Color color, char icon, String result, int position) {
		this.color = color;
		this.icon = String.valueOf(icon);
		this.result = result;
		this.position = position;
	}

	public void render(GraphicsContext gc, Font font, Font iconFont, int y) {
		gc.setFill(Palette.LIGHT_GRAY);
		Graphics.fillRect(gc, -1920, y, 3840, 256);
		gc.setFill(Palette.DARK_GRAY);
		gc.setFont(iconFont);
		Graphics.text(gc, icon, -1760, y + 128);
		gc.setFont(font);
		Graphics.text(gc, result, 0, y + 128);
	}

	public void renderAlt(GraphicsContext gc, Font font, Font iconFont, int y) {
		gc.setFill(Palette.LIGHT_GRAY);
		Graphics.fillRect(gc, -1920, y, 3840, 256);
		gc.setFill(color);
		gc.setFont(iconFont);
		Graphics.text(gc, icon, -1760, y + 128);
		gc.setFont(font);
		Graphics.text(gc, result, 0, y + 128);
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
	
	public void open() {
//		if (icon == String.valueOf(Icons.MOVIE)) {
			try {
				Desktop.getDesktop().open(Vision.movies.get(position).getFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
//		}
	}
}