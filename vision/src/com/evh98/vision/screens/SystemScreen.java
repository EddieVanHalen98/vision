package com.evh98.vision.screens;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Palette;

import javafx.scene.canvas.GraphicsContext;

public class SystemScreen extends Screen {

	public SystemScreen(GraphicsContext gc) {
		super(gc);
	}

	@Override
	public void render() {
		gc.setFill(Palette.GREEN);
		gc.setStroke(Palette.LIGHT_GRAY);
		gc.fillRect(0, 0, 1080 * Vision.SCALE, 384 * Vision.SCALE);
		gc.strokeRect(0, 0, 1080 * Vision.SCALE, 384 * Vision.SCALE);
	}
}