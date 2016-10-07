package com.evh98.vision.screens;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

public class MediaManagerScreen extends Screen {

	int position = 0;
	
	Font font_text;
	FileChooser file_chooser;
	
	public MediaManagerScreen(GraphicsContext gc, Group root, Scene scene) {
		super(gc, root, scene);
		
		font_text = Font.font("Roboto Thin", 128 * Vision.SCALE);
		file_chooser = new FileChooser();
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	public void render() {
		Graphics.drawBackground(gc, Graphics.background_green);
		
		gc.setFont(font_text);
		gc.setFill(Palette.GREEN);
		Graphics.text(gc, "Movies Folder Path", -1792, -1016);
	}
	
	@Override
	public void update(KeyEvent e) {
		
	}
	
	@Override
	public void exit() {
		Vision.setScreen(Vision.system_screen);
	}
}