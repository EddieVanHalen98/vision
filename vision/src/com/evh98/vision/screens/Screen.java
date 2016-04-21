/**
 * Vision - Created and owned by Muhammad Saeed (EddieVanHalen98)
 * 
 * Screen.java
 * The superclass for Screen objects
 * 
 * File created on 19th April 2016
 */

package com.evh98.vision.screens;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public class Screen {

	GraphicsContext gc;
	
	public Screen(GraphicsContext gc) {
		this.gc = gc;
	}
	
	public void render() {
        
	}
	
	public void update(Scene scene) {
		
	}
}