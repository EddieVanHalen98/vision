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

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;

public class Graphics {
	
	public static float SD = 0.23F;
	public static float H_SD = 0.25F;
	public static float HD = 0.34F;
	public static float H_HD = 0.36F;
	public static float F_HD = 0.5F;
	public static float Q_HD = 0.67F;
	public static float U_HD = 1.0F;
	
	/*
	 * Draws an image using GraphicsContext under Vision scaling
	 */
	public static void drawImageRaw(GraphicsContext gc, Image image, double x, double y) {
		gc.drawImage(image, x * Vision.SCALE, y * Vision.SCALE, image.getWidth() * Vision.SCALE, image.getHeight() * Vision.SCALE);
	}

	/*
	 * Draws an image using GraphicsContext with a specified resolution under Vision scaling
	 */
	public static void drawImage(GraphicsContext gc, Image image, double x, double y, double width, double height) {
		gc.drawImage(image, x * Vision.SCALE, y * Vision.SCALE, width * Vision.SCALE, height * Vision.SCALE);
	}
	
	/*
	 * Draws a filled rectangle using GraphicsContext under Vision scaling
	 */
	public static void fillRect(GraphicsContext gc, double x, double y, double width, double height) {
		gc.fillRect(x * Vision.SCALE, y * Vision.SCALE, width * Vision.SCALE, height * Vision.SCALE);
	}
	
	/*
	 * Draws a stroked rectangle using GraphicsContext under Vision scaling
	 */
	public static void strokeRect(GraphicsContext gc, double x, double y, double width, double height) {
		gc.strokeRect(x * Vision.SCALE, y * Vision.SCALE, width * Vision.SCALE, height * Vision.SCALE);
	}
	
	/*
	 * Draws centered text using GraphicsContext under Vision scaling
	 */
	public static void text(GraphicsContext gc, String text, double x, double y) {
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText(text, x * Vision.SCALE, y * Vision.SCALE);
	}
}