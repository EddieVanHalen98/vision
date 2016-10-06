/**
 * Vision
 * 
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.screens;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Graphics;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class AppScreen extends Screen {

	int x = 0;
	int y = 0;
	
	public AppScreen(GraphicsContext gc, Group root, Scene scene) {
		super(gc, root, scene);
	}

	@Override
	public void render() {
		Graphics.drawBackground(gc, Graphics.background_yellow);
	}
	
	@Override
	public void update(KeyEvent e) {
		
	}
	
	@Override
	public void exit() {
		Vision.setScreen(Vision.main_screen);
	}
}