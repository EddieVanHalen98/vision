package com.evh98.vision.screens;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;
import com.evh98.vision.util.Update;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

public class UpdateScreen extends Screen {

	Font font;
	
	int stage = 0;
	String text = "An update is available!\nPress Space/Green to continue\nPress Escape/Red to cancel";
	
	public UpdateScreen(GraphicsContext gc) {
		super(gc);
	}

	@Override
	public void start() {
		font = Font.font("Roboto Light", 192 * Vision.SCALE);
	}
	
	@Override
	public void render() {
		Graphics.drawImageRaw(gc, Graphics.background_purple, 0, 0);
		
		gc.setFill(Palette.PURPLE);
		gc.setFont(font);
		Graphics.text(gc, text, 1920, 1080);
	}
	
	@Override
	public void update(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				if (Controller.isRed(e)) {
					System.exit(0);
				}
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
		});
	}
}