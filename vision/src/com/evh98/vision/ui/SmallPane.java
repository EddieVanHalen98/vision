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
import com.evh98.vision.screens.Screen;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SmallPane {

	private final Screen screen;
	private Color screen_color;
	private Color pane_color;
	private String text;
	private char icon;
	private int x;
	private int y;
	private Font font_text;
	private Font font_icon;

	/**
	 * Initializes a small pane object
	 */
	public SmallPane(Screen screen, Color screen_color, Color pane_color, String text, String iconpack, char icon, int x, int y) {
		this.screen = screen;
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
		Graphics.fillRect(gc, x, y, 720, 542);
		gc.setFill(Palette.DARK_GRAY);
		Graphics.fillRect(gc, x, y + 540, 720, 180);
		// Draw title
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font_text);
		Graphics.text(gc, text, x + 360, y + 630);
		// Draw icon
		gc.setFont(font_icon);
		Graphics.text(gc, icon + "", x + 360, y + 270);
	}

	/*
	 * Rendering of the selected pane
	 */
	public void renderAlt(GraphicsContext gc) {
		// Draw rectangle
		gc.setFill(pane_color);
		Graphics.fillRect(gc, x, y, 720, 542);
		gc.setFill(screen_color);
		Graphics.fillRect(gc, x, y + 540, 720, 180);
		// Draw title
		gc.setFill(Palette.LIGHT_GRAY);
		gc.setFont(font_text);
		Graphics.text(gc, text, x + 360, y + 630);
		// Draw icon
		gc.setFont(font_icon);
		Graphics.text(gc, icon + "", x + 360, y + 270);
	}

	public Screen getScreen() {
		return screen;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}