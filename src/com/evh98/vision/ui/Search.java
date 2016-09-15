/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 *
 * MediaScreen.java
 * Media screen
 *
 * File created on 27th June 2016
 */

package com.evh98.vision.ui;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Icons;
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
	private boolean showResults;
	
	ArrayList<SearchResult> results;
	
	int y = 0;
	
	public Search() {
		font = Font.font("Roboto Thin", 176 * Vision.SCALE);
		iconFont = Font.font("Material-Design-Iconic-Font", 160 * Vision.SCALE);
		
		input = "";
		
		isActive = false;
		showResults = false;
		
		results = new ArrayList<SearchResult>();
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
		
		if (showResults) {
			for (int i = 0; i < results.size(); i++) {
				if ((i + 1) != y) {
					results.get(i).render(gc, font, iconFont, 384 - (i * 256));
				} else {
					results.get(i).renderAlt(gc, font, iconFont, 384 - (i * 256));
				}
			}
		}
	}
	
	public void update(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				if (Controller.isSearch(e)) {
					toggleSearch();
					showResults = false;
				}
				
				if (showResults) {
					if (Controller.isGreen(e)) {
						results.get(y - 1).open();
					} 
					else if (Controller.isUp(e) && y < results.size()) {
						y++;
					}
					else if (Controller.isDown(e) && y > 0) {
						y--;
					}
				} else {
					// Enter letter
					if (e.getCode().isLetterKey()) {
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
					else if (e.getCode() == KeyCode.ENTER) {
						results.clear();
						search();
					}
				}
			}
		});
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void toggleSearch() {
		input = "";
		isActive = !isActive;
		y = 0;
	}
	
	public void toggleResults() {
		showResults = !showResults;
	}
	
	private void search() {
		for (int i = 0; i < Vision.movies.size(); i++) {
			String title = Vision.movies.get(i).getTitle().toLowerCase();
			if (similarity(title, input) >= 0.5 || title.contains(input)) {
				results.add(new SearchResult(Palette.PINK, Icons.MOVIE, Vision.movies.get(i).getTitle(), i));
			}
		}
		
		toggleResults();
	}
	
	public double similarity(String s1, String s2) {
		String longer = s1, shorter = s2;
		if (s1.length() < s2.length()) {
			longer = s2; shorter = s1;
		}
		int longerLength = longer.length();
		if (longerLength == 0) {
			return 1.0;
		}
		return (longerLength - StringUtils.getLevenshteinDistance(longer, shorter)) / (double) longerLength;
	}
}