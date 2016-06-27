/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 * 
 * Vision.java
 * The main class
 * 
 * File created on 18th April 2016
 */

package com.evh98.vision;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import com.esotericsoftware.kryonet.Server;
import com.evh98.vision.apps.VideoScreen;
import com.evh98.vision.apps.YouTubeScreen;
import com.evh98.vision.screens.AppScreen;
import com.evh98.vision.screens.GameScreen;
import com.evh98.vision.screens.MainScreen;
import com.evh98.vision.screens.MediaScreen;
import com.evh98.vision.screens.Screen;
import com.evh98.vision.screens.SystemScreen;
import com.evh98.vision.screens.UpdateScreen;
import com.evh98.vision.ui.Search;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;
import com.evh98.vision.util.RemoteListener;
import com.evh98.vision.util.Update;
import com.teamdev.jxbrowser.chromium.Browser;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Vision extends Application {

	public static int BUILD_NUMBER = 1;
	public static float WIDTH = 960;
	public static float HEIGHT = 540;
	public static float[] ANCHOR = {WIDTH / 2, HEIGHT / 2};
	public static float SCALE = HEIGHT / 2160;
	public static float HORIZONTAL_SCALE = (3840.0F / 2160.0F) / (WIDTH / HEIGHT);
	public static boolean FULLSCREEN = false;

	public static Browser browser;
	public static Server server;

	public static Group root;
	public static Stage main_stage;
	
	public static Search search;

	public static Screen current_screen;
	public static MainScreen main_screen;
	public static UpdateScreen update_screen;
	public static GameScreen game_screen;
	public static MediaScreen media_screen;
	public static AppScreen app_screen;
	public static SystemScreen system_screen;
	
	public static VideoScreen video_screen;
	public static YouTubeScreen youtube_screen;

	public static void main(String[] args) {
		// Init browser
		browser = new Browser();

		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Vision");
		
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

		/*
		 * Init scene
		 */
		root = new Group();
		Scene scene = new Scene(root, Palette.LIGHT_GRAY);
		scene.getStylesheets().add("fonts.css");
		scene.setCursor(Cursor.NONE);

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
		
		main_stage.setScene(scene);
		main_stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Load graphics
		Graphics.load();
		/*
		 * Init scene
		 */
		
		search = new Search(gc);

		// Init screens
		main_screen = new MainScreen(gc);
		update_screen = new UpdateScreen(gc);
		game_screen = new GameScreen(gc);
		media_screen = new MediaScreen(gc);
		app_screen = new AppScreen(gc);
		system_screen = new SystemScreen(gc);
		
		video_screen = new VideoScreen(gc);
		youtube_screen = new YouTubeScreen(gc);

		// Checks for update
		if (!Update.isAvailable()) {
			setScreen(main_screen);
		} else {
			setScreen(update_screen);
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
				if (search.isActive()) {
					search.render();
					search.update(scene);
				} else {
					current_screen.update(scene);
				}
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