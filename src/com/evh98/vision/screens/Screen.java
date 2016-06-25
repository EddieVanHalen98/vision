/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 * 
 * Screen.java
 * The superclass for Screen objects
 * 
 * File created on 19th April 2016
 */

package com.evh98.vision.screens;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Control;

public class Screen {

	protected GraphicsContext gc;
	
	public Screen(GraphicsContext gc) {
		this.gc = gc;
	}
	
	public void start() {
		
	}
	
	public void render() {
        
	}
	
	public void update(Scene scene) {
		
	}

	public void setPos(Control c, float x, float y) {
		c.setLayoutX(x);
		c.setLayoutY(y);
	}

	public void setSize(Control c, float w, float h) {
		c.setPrefWidth(w);
		c.setPrefHeight(h);
	}
}