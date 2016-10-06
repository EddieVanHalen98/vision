package com.evh98.vision.screens;

import com.evh98.vision.Vision;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

public class MediaManagerScreen extends Screen {

	Label l = new Label("sexy");
	
	public MediaManagerScreen(GraphicsContext gc, Group root, Scene scene) {
		super(gc, root, scene);
	}
	
	@Override
	public void start() {
		root.getChildren().add(l);
	}
	
	@Override
	public void render() {
		
	}
	
	@Override
	public void update(KeyEvent e) {
		
	}
	
	@Override
	public void exit() {
		root.getChildren().remove(l);
		Vision.setScreen(Vision.system_screen);
	}
}