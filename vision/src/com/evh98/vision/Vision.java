/**
 * Vision - Created and owned by Muhammad Saeed (EddieVanHalen98)
 * 
 * Vision.java
 * The main class
 * 
 * File created on 18th April 2016
 */

package com.evh98.vision;

import java.io.IOException;
import java.util.logging.Level;

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.evh98.vision.apps.NetflixScreen;
import com.evh98.vision.apps.VideoScreen;
import com.evh98.vision.apps.YouTubeListener;
import com.evh98.vision.apps.YouTubeScreen;
import com.evh98.vision.screens.AppScreen;
import com.evh98.vision.screens.GameScreen;
import com.evh98.vision.screens.MainScreen;
import com.evh98.vision.screens.MediaScreen;
import com.evh98.vision.screens.Screen;
import com.evh98.vision.screens.SystemScreen;
import com.evh98.vision.screens.UpdateScreen;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;
import com.evh98.vision.util.RemoteListener;
import com.evh98.vision.util.Update;
import com.teamdev.jxbrowser.chromium.BrowserCore;
import com.teamdev.jxbrowser.chromium.LoggerProvider;

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
	public static float SCALE = Graphics.F_HD;
	public static float WIDTH = 3840;
	public static float HEIGHT = 2160;
	public static boolean FULLSCREEN = true;
	public static float VOLUME = 1.0F;
	
	int x = 0;
	int y = 0;
	
	public static Server server;
	public static RemoteListener main_listener;
	public static YouTubeListener youtube_listener;
	
	public static Group root;
	public static Stage main_stage;
	public static Scene main_scene;
	
	public static Screen current_screen;
	public static MainScreen main_screen;
	public static GameScreen game_screen;
	public static MediaScreen media_screen;
	public static AppScreen app_screen;
	public static SystemScreen system_screen;
	public static UpdateScreen update_screen;
	
	public static VideoScreen video_screen;
	public static NetflixScreen netflix_screen;
	public static YouTubeScreen youtube_screen;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void init() {
		BrowserCore.initialize();
		LoggerProvider.setLevel(Level.OFF);
		
		Graphics.load();
	}
	
	public void start(Stage stage) {
		/*
		 * Sets up Server
		 */
		server = new Server();
		server.start();
		try {
			server.bind(4567, 28960);
		} catch (IOException e) {
			e.printStackTrace();
		}
		main_listener = new RemoteListener();
		youtube_listener = new YouTubeListener();
		server.addListener(main_listener);
		
		/*
		 * Sets up title and icons
		 */
		main_stage = stage;
		main_stage.setTitle("Vision");
		main_stage.getIcons().add(new Image("file:assets/icons/1024.png"));
		main_stage.getIcons().add(new Image("file:assets/icons/512.png"));
		main_stage.getIcons().add(new Image("file:assets/icons/256.png"));
		main_stage.getIcons().add(new Image("file:assets/icons/128.png"));
		main_stage.getIcons().add(new Image("file:assets/icons/64.png"));
		main_stage.getIcons().add(new Image("file:assets/icons/48.png"));
		main_stage.getIcons().add(new Image("file:assets/icons/32.png"));
		main_stage.getIcons().add(new Image("file:assets/icons/24.png"));
		main_stage.getIcons().add(new Image("file:assets/icons/16.png"));
		
		/*
		 * Sets up scene
		 */
		root = new Group();
		main_scene = new Scene(root, Palette.LIGHT_GRAY);
		main_scene.getStylesheets().add("fonts.css");
		main_scene.setCursor(Cursor.NONE);
		main_stage.setScene(main_scene);
		main_stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		main_stage.setFullScreen(FULLSCREEN);
		main_stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
		});
		Canvas canvas = new Canvas(WIDTH * SCALE, HEIGHT * SCALE);
		root.getChildren().add(canvas);
		
		/*
		 * Sets up screens
		 */		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		main_screen = new MainScreen(gc);
		game_screen = new GameScreen(gc);
		media_screen = new MediaScreen(gc);
		app_screen = new AppScreen(gc);
		system_screen = new SystemScreen(gc);
		update_screen = new UpdateScreen(gc);
		
		video_screen = new VideoScreen(gc);
		netflix_screen = new NetflixScreen(gc);
		youtube_screen = new YouTubeScreen(gc);
		
		// Checks for update
		if (!Update.isAvailable()) {
			setScreen(video_screen);
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
				current_screen.update(main_scene);
	    	}
		});
		renderCycle.getKeyFrames().add(kf);
		renderCycle.play();
		
		main_stage.show();
	}
	
	/**
	 * Shorthand method for setting the currently displayed screen
	 */
	public static void setScreen(Screen screen) {
		current_screen = screen;
		current_screen.start();
	}
	
	public static void setScreen(Screen screen, Listener listener) {
		server.removeListener(main_listener);
		server.addListener(listener);
		current_screen = screen;
		current_screen.start();
	}
}