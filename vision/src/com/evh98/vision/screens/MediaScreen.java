/**
 * Vision - Created and owned by Muhammad Saeed (EddieVanHalen98)
 * 
 * MediaScreen.java
 * Media screen
 * 
 * File created on 20th April 2016
 */

package com.evh98.vision.screens;

import java.util.ArrayList;

import com.evh98.vision.Vision;
import com.evh98.vision.ui.SmallPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class MediaScreen extends Screen {

	int x = 0;
	int y = 0;
	
	ArrayList<SmallPane> panes;
    int[][] panesPos = {{1, 1}, {2, 1}, {3, 1}, {4, 1}, {1, 2}, {2, 2}};
	
	public MediaScreen(GraphicsContext gc) {
		super(gc);
	}

	@Override
	public void start() {
		panes = new ArrayList<SmallPane>();
		panes.add(new SmallPane(Vision.netflix_screen, Palette.BLUE, Palette.PINK, "Videos", "Material-Design-Iconic-Font", '\uf19e', 192, 270));
		panes.add(new SmallPane(Vision.netflix_screen, Palette.BLUE, Palette.RED, "Netflix", "Material-Design-Iconic-Font", '\uf3a9', 1104, 270));
		panes.add(new SmallPane(Vision.youtube_screen, Palette.BLUE, Palette.RED, "YouTube", "Material-Design-Iconic-Font", '\uf409', 2016, 270));
		panes.add(new SmallPane(Vision.netflix_screen, Palette.BLUE, Palette.YELLOW, "Music", "Material-Design-Iconic-Font", '\uf3bc', 2928, 270));
		panes.add(new SmallPane(Vision.netflix_screen, Palette.BLUE, Palette.GREEN, "Spotify", "FontAwesome", '\uf1bc', 192, 1170));
		panes.add(new SmallPane(Vision.netflix_screen, Palette.BLUE, Palette.PURPLE, "Photos", "Material-Design-Iconic-Font", '\uf140', 1104, 1170));
	}
	
	@Override
	public void render() {
		Graphics.drawImageRaw(gc, Graphics.background_blue, 0, 0);
		
		for (int i = 0; i < 6; i++) {
			if (panesPos[i][0] == x && panesPos[i][1] == y) {
				panes.get(i).renderAlt(gc);
			} else {
				panes.get(i).render(gc);
			}
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
					for (int i = 0; i < 6; i++) {
						if (panesPos[i][0] == x && panesPos[i][1] == y) {
							Vision.setScreen(panes.get(i).getScreen());
						}
					}
				}
				if (Controller.isRed(e)) {
					Vision.setScreen(Vision.main_screen);
				}
			}
		});
	}
}