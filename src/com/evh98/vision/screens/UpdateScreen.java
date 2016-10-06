/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 *
 * MediaScreen.java
 * Media screen
 *
 * File created on 24th April 2016
 */

package com.evh98.vision.screens;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;
import com.evh98.vision.util.Update;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

public class UpdateScreen extends Screen {

	Font font;
	
	int stage = 0;
	String text = "An update is available!\nPress Space/Green to continue\nPress Escape/Red to cancel";
	
	public UpdateScreen(GraphicsContext gc, Group root, Scene scene) {
		super(gc, root, scene);
		
		font = Font.font("Roboto Light", 192 * Vision.SCALE);
	}
	
	@Override
	public void render() {
		Graphics.drawBackground(gc, Graphics.background_purple);
		
		gc.setFill(Palette.PURPLE);
		gc.setFont(font);
		Graphics.text(gc, text, 0, 0);
	}
	
	@Override
	public void update(KeyEvent e) {
		if (Controller.isGreen(e)) {
			if (stage == 0) {
				text = "Downloading...";
				Update.downloadUpdate();
				text = "Finished!\nPress Space/Green to continue";
				stage++;
			} else if (stage == 1) {
				System.exit(0);
			}
		}
	}
	
	@Override
	public void exit() {
		System.exit(0);
	}
}