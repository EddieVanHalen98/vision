/**
 * Vision - Created and owned by Muhammad Saeed (EddieVanHalen98)
 * 
 * AppsScreen.java
 * Apps screen
 * 
 * File created on 20th April 2016
 */

package com.evh98.vision.screens;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Controller;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class AppScreen extends Screen {

	int x = 0;
	int y = 0;
	
	public AppScreen(GraphicsContext gc) {
		super(gc);
	}

	@Override
	public void render() {
		
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
				if (Controller.isRed(e)) {
					Vision.setScreen(Vision.main_screen);
				}
			}
		});
	}
}