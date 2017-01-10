/**
 * Vision
 * 
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Server;
import com.evh98.vision.apps.MoviesScreen;
import com.evh98.vision.apps.YouTubeScreen;
import com.evh98.vision.media.App;
import com.evh98.vision.media.Movie;
import com.evh98.vision.screens.AppsScreen;
import com.evh98.vision.screens.GamesScreen;
import com.evh98.vision.screens.HomeScreen;
import com.evh98.vision.screens.MediaScreen;
import com.evh98.vision.screens.SystemScreen;
import com.evh98.vision.screens.UpdateScreen;
import com.evh98.vision.ui.Loading;
import com.evh98.vision.ui.Search;
import com.evh98.vision.util.Assistant;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.RemoteListener;
import com.evh98.vision.util.Update;

public class Vision extends Game {
	
	public static int BUILD_NUMBER = 1;
	public static float WIDTH = 2560;
	public static float HEIGHT = 1440;
	public static float[] ANCHOR = {WIDTH / 2, HEIGHT / 2};
	public static float SCALE = HEIGHT / 2160;
	public static float HORIZONTAL_SCALE = (3840.0F / 2160.0F) / (WIDTH / HEIGHT);
	public boolean FULLSCREEN = false;
	
	public Server server;
	
	public static Search search;
	public static Assistant assistant;
	public static Loading loading;
	public static ArrayList<Game> games;
	public static ArrayList<App> apps;
	public static ArrayList<Movie> movies;

    public YouTubeScreen youtube_screen;
    public MoviesScreen movies_screen;
    
	public HomeScreen home_screen;
    public GamesScreen games_screen;
	public MediaScreen media_screen;
    public AppsScreen apps_screen;
    public SystemScreen system_screen;
    public UpdateScreen update_screen;
    
	@Override
	public void create () {
		initServer();
		initDisplay();
		initAssets();
		initObjects();
		initScreens();
		
		checkUpdate();
	}
	
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
	
	private void initDisplay() {
		if (FULLSCREEN) {
			WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
			HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
			Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
			Gdx.input.setCursorCatched(true);
		} else {
			Gdx.graphics.setWindowedMode((int) WIDTH,(int) HEIGHT);
		}
	}
	
	private void initAssets() {
		Graphics.loadAll();
	}
	
	private void initObjects() {
		search = new Search();
		assistant = new Assistant(this);
		loading = new Loading();
		games = new ArrayList<Game>();
		apps = new ArrayList<App>();
		movies = new ArrayList<Movie>();
	}
	
	private void initScreens() {
        movies_screen = new MoviesScreen(this);
        youtube_screen = new YouTubeScreen(this);
        
		home_screen = new HomeScreen(this);
		games_screen = new GamesScreen(this);
		media_screen = new MediaScreen(this);
		apps_screen = new AppsScreen(this);
		system_screen = new SystemScreen(this);
        update_screen = new UpdateScreen(this);
	}
	
	private void checkUpdate() {
		if (!Update.isAvailable()) {
			setScreen(home_screen);
		} else {
			setScreen(update_screen);
		}
	}
}
