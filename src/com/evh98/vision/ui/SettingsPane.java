package com.evh98.vision.ui;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class SettingsPane {

	private String text;
	private char icon;
	private int x;
	private int y;
	private Font font_text;
	private Font font_icon;
	
	/**
	 * Initializes a settings pane object
	 */
	public SettingsPane(String text, String iconpack, char icon, int x, int y) {
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
		gc.setFill(Palette.GREEN);
		Graphics.fillRect(gc, x, y, 768, 768);
		// Draw title
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font_text);
		Graphics.text(gc, text, x + 384, y + 580);
		// Draw icon
		gc.setFont(font_icon);
		Graphics.text(gc, icon + "", x + 384, y + 300);
	}
	
	/*
	 * Rendering of the main pane
	 */
	public void renderAlt(GraphicsContext gc) {
		// Draw rectangle
		gc.setFill(Palette.LIGHT_GRAY);
		Graphics.fillRect(gc, x, y, 768, 768);
		// Draw title
		gc.setFill(Palette.GREEN);
		gc.setFont(font_text);
		Graphics.text(gc, text, x + 384, y + 580);
		// Draw icon
		gc.setFont(font_icon);
		Graphics.text(gc, icon + "", x + 384, y + 300);
	}
}