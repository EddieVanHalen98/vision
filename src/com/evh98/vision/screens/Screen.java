/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 * 
 * Screen.java
 * The superclass for Screen objects
 * 
 * File created on 19th April 2016
 */

package com.evh98.vision.screens;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Palette;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import static com.evh98.vision.Vision.WIDTH;
import static com.evh98.vision.Vision.HEIGHT;

public class Screen {

	protected GraphicsContext gc;
	protected Scene scene;
	protected AnchorPane root;
	
	public Screen(GraphicsContext gc) {
		this.gc = gc;
	}
	
	public void start() {
		this.root = new AnchorPane();
		root.getChildren().add(gc.getCanvas());

		scene = new Scene(root, WIDTH, HEIGHT, Palette.LIGHT_GRAY);
		scene.setCursor(null);
		Vision.main_stage.setScene(scene);
	}
	
	public void render() {
        
	}
	
	public void update() {
		
	}

	public void setPos(Region c, float x, float y) {
		c.setLayoutX(x);
		c.setLayoutY(y);
	}

	public void setSize(Region c, float w, float h) {
		c.setPrefWidth(w);
		c.setPrefHeight(h);
	}
}