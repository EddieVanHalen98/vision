/**
 * Vision - Created and owned by Muhammad Saeed (EddieVanHalen98)
 * 
 * Graphics.java
 * All shorthand Vision rendering methods
 * 
 * File created on 19th April 2016
 */

package com.evh98.vision.util;

import com.evh98.vision.Vision;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Graphics {
	
	/*
	 * Draws an image using GraphicsContext under Vision scaling
	 */
	public static void drawImage(GraphicsContext gc, Image image, double x, double y) {
		gc.drawImage(image, x * Vision.SCALE, y * Vision.SCALE, (image.getWidth() * 2) * Vision.SCALE, (image.getHeight() * 2) * Vision.SCALE);
	}
}