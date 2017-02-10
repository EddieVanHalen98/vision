/**
 * Vision
 * 
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision;

import java.awt.Toolkit;
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
import com.evh98.vision.screens.SplashScreen;
import com.evh98.vision.screens.SystemScreen;
import com.evh98.vision.screens.UpdateScreen;
import com.evh98.vision.ui.Loading;
import com.evh98.vision.ui.Search;
import com.evh98.vision.util.Assistant;
import com.evh98.vision.util.Graphics;

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
    
    public SplashScreen splash_screen;
    
	public HomeScreen home_screen;
    public GamesScreen games_screen;
	public MediaScreen media_screen;
    public AppsScreen apps_screen;
    public SystemScreen system_screen;
    public UpdateScreen update_screen;

    public YouTubeScreen youtube_screen;
    public MoviesScreen movies_screen;
    
	@Override
	public void create () {
		init();
	}
	
	public void init() {
		initDisplay();
		Graphics.loadCamera();
		
		splash_screen = new SplashScreen(this);
		setScreen(splash_screen);
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
}
