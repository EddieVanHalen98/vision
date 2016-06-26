/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 * 
 * Vision.java
 * The main class
 * 
 * File created on 18th April 2016
 */

package com.evh98.vision;

import com.esotericsoftware.kryonet.Server;
import com.evh98.vision.apps.YouTubeScreen;
import com.evh98.vision.screens.MainScreen;
import com.evh98.vision.screens.MediaScreen;
import com.evh98.vision.screens.Screen;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.RemoteListener;
import com.evh98.vision.util.Update;
import com.teamdev.jxbrowser.chromium.Browser;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import jiconfont.icons.FontAwesome;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.javafx.IconFontFX;

import java.awt.*;
import java.io.IOException;

public class Vision extends Application {

	public static int BUILD_NUMBER = 1;
	public static float WIDTH = 1024;
	public static float HEIGHT = 768;
	public static float[] ANCHOR = {WIDTH / 2, HEIGHT / 2};
	public static float SCALE = HEIGHT / 2160;
	public static float HORIZONTAL_SCALE = (3840.0F / 2160.0F) / (WIDTH / HEIGHT);
	public static boolean FULLSCREEN = false;

	//DONT TOUCH THIS I DONT KNOW WHAT TO CALL IT BUT I USE IT RATHER A LOT AND I WOULD BE SAD IF THIS DISAPPEARED :(
	public static float CONVERTER = ((WIDTH / 960) + (HEIGHT / 540)) / 2;

	public static Browser browser;
	public static Server server;

	public static Stage main_stage;

	public static Screen current_screen;
	public static MainScreen main_screen;
	public static MediaScreen media_screen;
	public static YouTubeScreen youtube_screen;

	public static void main(String[] args) {
		// Init browser
		browser = new Browser();

		launch(args);
	}

	@Override
	public void start(Stage stage) {
		//LOAD FONT STUFF
		javafx.scene.text.Font.loadFont("file:assets/fonts/Roboto-Light.ttf", 60);
		javafx.scene.text.Font.loadFont("file:assets/fonts/Roboto-Thin.ttf", 60);
		IconFontFX.register(GoogleMaterialDesignIcons.getIconFont());
		IconFontFX.register(FontAwesome.getIconFont());

		stage.setTitle("Vision");
		stage.setResizable(false);

		// Init server
		server = new Server();
		server.start();
		try {
			server.bind(4567, 28960);
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.addListener(new RemoteListener());

		// Init icons
		setIcons(stage);
		main_stage = stage;

		// Auto full screen
		if (FULLSCREEN) {
			main_stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
			main_stage.setFullScreen(FULLSCREEN);
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			WIDTH = gd.getDisplayMode().getWidth();
			HEIGHT = gd.getDisplayMode().getHeight();
			ANCHOR[0] = WIDTH / 2;
			ANCHOR[1] = HEIGHT / 2;
			SCALE = HEIGHT / 2160;
			HORIZONTAL_SCALE = (3840.0F / 2160.0F) / (WIDTH / HEIGHT);
		}
		
		main_stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});

		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Load graphics
		Graphics.load();

		// Init screens
		main_screen = new MainScreen(gc);
		media_screen = new MediaScreen(gc);
		youtube_screen = new YouTubeScreen(gc);

		// Checks for update
		if (!Update.isAvailable()) {
			setScreen(main_screen);
		} else {
			setScreen(main_screen);
			//TODO CHANGE THIS TO UPDATE SCREEN WHEN APPLICABLE
		}

		/*
		 * Render loop (0.016 = 1/60 = 60FPS)
		 */
		Timeline renderCycle = new Timeline();
		renderCycle.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.016), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				gc.clearRect(0, 0, 3840 * Vision.SCALE, 2160 * Vision.SCALE);

				current_screen.render();
				current_screen.update();
			}
		});
		renderCycle.getKeyFrames().add(kf);
		renderCycle.play();
		/*
		 * Render loop (0.016 = 1/60 = 60FPS)
		 */

		main_stage.show();
	}

	/**
	 * Sets all possible icons for the program
	 */
	private void setIcons(Stage stage) {
		stage.getIcons().add(new Image("file:assets/icons/1024.png"));
		stage.getIcons().add(new Image("file:assets/icons/512.png"));
		stage.getIcons().add(new Image("file:assets/icons/256.png"));
		stage.getIcons().add(new Image("file:assets/icons/128.png"));
		stage.getIcons().add(new Image("file:assets/icons/64.png"));
		stage.getIcons().add(new Image("file:assets/icons/48.png"));
		stage.getIcons().add(new Image("file:assets/icons/32.png"));
		stage.getIcons().add(new Image("file:assets/icons/24.png"));
		stage.getIcons().add(new Image("file:assets/icons/16.png"));
	}

	/**
	 * Shorthand method for setting the currently displayed screen
	 */
	public static void setScreen(Screen screen) {
		current_screen = screen;
		current_screen.start();
	}

	/**
	 * Checks whether or not an internet connection is detected
	 */
	public static void isNetwork() {

	}
}