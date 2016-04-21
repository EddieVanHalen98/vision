/**
 * Vision - Created and owned by Muhammad Saeed (EddieVanHalen98)
 * 
 * Vision.java
 * The main class
 * 
 * File created on 18th April 2016
 */

package com.evh98.vision;

import com.evh98.vision.screens.AppScreen;
import com.evh98.vision.screens.GameScreen;
import com.evh98.vision.screens.MainScreen;
import com.evh98.vision.screens.MediaScreen;
import com.evh98.vision.screens.Screen;
import com.evh98.vision.screens.SystemScreen;
import com.evh98.vision.util.Palette;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Vision extends Application {

	public static Group root;
	
	public static float SCALE = 0.25F;
	public static float WIDTH = 3840;
	public static float HEIGHT = 2160;
	int x = 0;
	int y = 0;
	
	public static Screen current_screen;
	public static MainScreen main_screen;
	public static GameScreen game_screen;
	public static MediaScreen media_screen;
	public static AppScreen app_screen;
	public static SystemScreen system_screen;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		stage.setTitle("Vision");
		
		root = new Group();
		Scene scene = new Scene(root, Palette.LIGHT_GRAY);
		scene.getStylesheets().add("fonts.css");
		stage.setScene(scene);
		Canvas canvas = new Canvas(WIDTH * SCALE, HEIGHT * SCALE);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		main_screen = new MainScreen(gc);
		game_screen = new GameScreen(gc);
		media_screen = new MediaScreen(gc);
		app_screen = new AppScreen(gc);
		system_screen = new SystemScreen(gc);
		
		setScreen(media_screen);

		Timeline renderCycle = new Timeline();
		renderCycle.setCycleCount(Timeline.INDEFINITE);
		
		KeyFrame kf = new KeyFrame(Duration.seconds(0.016), // 60 FPS
	            new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent e) {
	                    gc.clearRect(0, 0, 3840 * Vision.SCALE, 2160 * Vision.SCALE);

	            		current_screen.render();
	            		current_screen.update(scene);
	                }
		});
	        
		renderCycle.getKeyFrames().add(kf);
		renderCycle.play();
		
		stage.show();
	}
	
	public static void setScreen(Screen screen) {
		current_screen = screen;
	}
}