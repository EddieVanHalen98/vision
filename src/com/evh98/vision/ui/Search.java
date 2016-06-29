/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 *
 * MediaScreen.java
 * Media screen
 *
 * File created on 27th June 2016
 */

package com.evh98.vision.ui;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

public class Search {
	
	Font font;
	Font iconFont;
	
	String input;
	
	private boolean isActive;
	
	public Search() {
		font = Font.font("Roboto Thin", 176 * Vision.SCALE);
		iconFont = Font.font("Material-Design-Iconic-Font", 160 * Vision.SCALE);
		
		input = "";
		
		setActive(false);
	}
	
	public void render(GraphicsContext gc) {
		// Draw rectangle
		gc.setFill(Palette.SYSTEM);
		Graphics.fillRect(gc, -1920, 640, 3840, 256);

		// Search icon
		gc.setFont(iconFont);
		gc.setFill(Palette.LIGHT_GRAY);
		Graphics.text(gc, String.valueOf('\uf1c3'), -1760, 768);
		
		// Search input
		gc.setFont(font);
		gc.setFill(Palette.LIGHT_GRAY);
		Graphics.text(gc, input, 0, 768);
	}
	
	public void update(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				if (Controller.isSearch(e)) {
					toggleSearch();
				}
				// Enter letter
				else if (e.getCode().isLetterKey()) {
					input = input + e.getCode().name().toLowerCase();
				}
				else if (e.getCode().isDigitKey()) {
					input = input + e.getCode().name().substring(5, e.getCode().name().length());
				}
				// Space
				else if (e.getCode() == KeyCode.SPACE) {
					input = input + " ";
				}
				// Backspace
				else if (e.getCode() == KeyCode.BACK_SPACE) {
					input = input.substring(0, input.length() - 1);
				}
			}
		});
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public void toggleSearch() {
		input = "";
		isActive = !isActive;
	}
}