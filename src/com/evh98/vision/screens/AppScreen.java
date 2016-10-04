/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 * 
 * AppsScreen.java
 * Apps screen
 * 
 * File created on 20th April 2016
 */

package com.evh98.vision.screens;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;

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
		Graphics.drawBackground(gc, Graphics.background_yellow);
	}
	
	@Override
	public void update(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				generalUpdate(e);

				if (Controller.isRed(e)) {
					Vision.setScreen(Vision.main_screen);
				}
			}
		});
	}
}