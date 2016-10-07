/**
 * Vision
 * 
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Server;
import com.evh98.vision.apps.MovieScreen;
import com.evh98.vision.apps.YouTubeScreen;
import com.evh98.vision.media.App;
import com.evh98.vision.media.Game;
import com.evh98.vision.media.Movie;
import com.evh98.vision.screens.AppScreen;
import com.evh98.vision.screens.GameScreen;
import com.evh98.vision.screens.MainScreen;
import com.evh98.vision.screens.MediaManagerScreen;
import com.evh98.vision.screens.MediaScreen;
import com.evh98.vision.screens.Screen;
import com.evh98.vision.screens.SystemScreen;
import com.evh98.vision.screens.UpdateScreen;
import com.evh98.vision.ui.Search;
import com.evh98.vision.util.Data;
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
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Vision extends Application {

	public static int BUILD_NUMBER = 1;
	public static float WIDTH = 0;
	public static float HEIGHT = 0;
	public static float[] ANCHOR = {WIDTH / 2, HEIGHT / 2};
	public static float SCALE = HEIGHT / 2160;
	public static float HORIZONTAL_SCALE = (3840.0F / 2160.0F) / (WIDTH / HEIGHT);
	public static boolean FULLSCREEN = true;
	
	public static String HOME = System.getProperty("user.home") + "/vision/";

	public static Browser browser;
	public static Server server;

	public static Group root;
	public static Stage main_stage;
	 
	public static Search search;
	
	public static ArrayList<Game> games;
	public static ArrayList<App> apps;
	public static ArrayList<Movie> movies;

	public static Screen current_screen;
	public static MainScreen main_screen;
	public static UpdateScreen update_screen;
	public static GameScreen game_screen;
	public static MediaScreen media_screen;
	public static AppScreen app_screen;
	
	public static SystemScreen system_screen;
	public static MediaManagerScreen media_manager_screen;
	
	public static MovieScreen movie_screen;
	public static YouTubeScreen youtube_screen;

	public static AudioClip click;
	public static AudioClip back;
	public static AudioClip touch;

	public static void main(String[] args) {
		// Init browser
		browser = new Browser();

		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Vision");

		initServer();
		initIcons(stage);

		/*
		 * Init scene
		 */
		root = new Group();
		Scene scene = new Scene(root, Palette.LIGHT_GRAY);
		scene.getStylesheets().add("fonts.css");
		scene.setCursor(Cursor.NONE);

		main_stage = stage;
		
		main_stage.setScene(scene);
		main_stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});
		initDisplay();
		
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		Graphics.load();
		/*
		 * Init scene
		 */
		
		search = new Search();
		
		games = new ArrayList<Game>();
		apps = new ArrayList<App>();
		movies = new ArrayList<Movie>();
		
		initScreens(gc, root, scene);
		
		// Init audio
		touch = new AudioClip("file:assets/sfx/touch.wav");
		back = new AudioClip("file:assets/sfx/back.wav");
		click = new AudioClip("file:assets/sfx/click.wav");

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
					search.render(gc);
					search.update(scene);
				} else {
					current_screen.overheadUpdate();
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
	 * Initialise the Vision remote server
	 */
	private void initServer() {
		server = new Server();
		server.start();
		try {
			server.bind(4567, 28960);
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.addListener(new RemoteListener());
	}

	/**
	 * Sets all possible icons for the program
	 */
	private void initIcons(Stage stage) {
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
	
	private void initDisplay() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		if (new File(Vision.HOME + "data/prefs.dat").exists()) {
			try {
				Data.loadPrefs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			FULLSCREEN = true;
			WIDTH = gd.getDisplayMode().getWidth();
			HEIGHT = gd.getDisplayMode().getHeight();
			try {
				Data.savePrefs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (FULLSCREEN) {
			main_stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
			main_stage.setFullScreen(FULLSCREEN);
			WIDTH = gd.getDisplayMode().getWidth();
			HEIGHT = gd.getDisplayMode().getHeight();
		}
		
		ANCHOR[0] = WIDTH / 2;
		ANCHOR[1] = HEIGHT / 2;
		SCALE = HEIGHT / 2160;
		HORIZONTAL_SCALE = (3840.0F / 2160.0F) / (WIDTH / HEIGHT);
	}
	
	/**
	 * Initialise all the screens
	 */
	private void initScreens(GraphicsContext gc, Group root, Scene scene) {
		main_screen = new MainScreen(gc, root, scene);
		update_screen = new UpdateScreen(gc, root, scene);
		game_screen = new GameScreen(gc, root, scene);
		media_screen = new MediaScreen(gc, root, scene);
		app_screen = new AppScreen(gc, root, scene);
		
		system_screen = new SystemScreen(gc, root, scene);
		media_manager_screen = new MediaManagerScreen(gc, root, scene);
		
		movie_screen = new MovieScreen(gc, root, scene);
		youtube_screen = new YouTubeScreen(gc, root, scene);
	}

	/**
	 * Shorthand method for setting the currently displayed screen
	 */
	public static void setScreen(Screen screen) {
		current_screen = screen;
		current_screen.start();
	}
}