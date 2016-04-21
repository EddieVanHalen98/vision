/**
 * Vision - Created and owned by Muhammad Saeed (EddieVanHalen98)
 * 
 * MediaScreen.java
 * Media screen
 * 
 * File created on 20th April 2016
 */

package com.evh98.vision.screens;

import com.evh98.vision.Vision;
import com.evh98.vision.ui.SmallPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Palette;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class MediaScreen extends Screen {

	int x = 0;
	int y = 0;

	SmallPane videos = new SmallPane(Palette.BLUE, Palette.PINK, "Videos", "Material-Design-Iconic-Font", '\uf19e', 192, 270);
	SmallPane netflix = new SmallPane(Palette.BLUE, Palette.RED, "Netflix", "Material-Design-Iconic-Font", '\uf3a9', 1104, 270);
	SmallPane youtube = new SmallPane(Palette.BLUE, Palette.RED, "YouTube", "Material-Design-Iconic-Font", '\uf409', 2016, 270);
	SmallPane music = new SmallPane(Palette.BLUE, Palette.YELLOW, "Music", "Material-Design-Iconic-Font", '\uf3bc', 2928, 270);
	SmallPane spotify = new SmallPane(Palette.BLUE, Palette.GREEN, "Spotify", "FontAwesome", '\uf1bc', 192, 1170);
	SmallPane photos = new SmallPane(Palette.BLUE, Palette.PURPLE, "Photos", "Material-Design-Iconic-Font", '\uf140', 1104, 1170);
	
	public MediaScreen(GraphicsContext gc) {
		super(gc);
	}

	@Override
	public void render() {
		if (x == 1 & y == 1) {
			videos.renderAlt(gc);
		} else {
			videos.render(gc);
		}
		
		if (x == 2 & y == 1) {
			netflix.renderAlt(gc);
		} else {
			netflix.render(gc);
		}
		
		if (x == 3 && y == 1) {
			youtube.renderAlt(gc);
		} else {
			youtube.render(gc);
		}
		
		if (x == 4 && y == 1) {
			music.renderAlt(gc);
		} else {
			music.render(gc);
		}
		
		if (x == 1 && y == 2) {
			spotify.renderAlt(gc);
		} else {
			spotify.render(gc);
		}
		
		if (x == 2 && y == 2) {
			photos.renderAlt(gc);
		} else {
			photos.render(gc);
		}
	}
	
	@Override
	public void update(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				if (Controller.isLeft(e)) {
					if (x >= 2 && x <= 4) {
						x--;
					}
				}
				if (Controller.isRight(e)) {
					if (x >= 0 && x <= 3) {
						x++;
						if (y == 0) {
							y = 1;
						}
					}
				}
				if (Controller.isUp(e)) {
					if (y == 2) {
						y = 1;
					}
				}
				if (Controller.isDown(e)) {
					if (y == 0 || y == 1) {
						y++;
						if (x == 0) {
							x = 1;
						}
					}
				}
				if (Controller.isGreen(e)) {
					if (x == 2 && y == 1) {
						Vision.setScreen(Vision.netflix_screen);
					}
					
					if (x == 3 && y == 1) {
						Vision.setScreen(Vision.youtube_screen);
					}
				}
				if (Controller.isRed(e)) {
					Vision.setScreen(Vision.main_screen);
				}
			}
		});
	}
}