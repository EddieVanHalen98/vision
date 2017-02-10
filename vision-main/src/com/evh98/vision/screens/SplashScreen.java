package com.evh98.vision.screens;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.esotericsoftware.kryonet.Server;
import com.evh98.vision.Vision;
import com.evh98.vision.apps.MoviesScreen;
import com.evh98.vision.apps.YouTubeScreen;
import com.evh98.vision.media.App;
import com.evh98.vision.media.Movie;
import com.evh98.vision.ui.Loading;
import com.evh98.vision.ui.Search;
import com.evh98.vision.util.Assistant;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.RemoteListener;
import com.evh98.vision.util.Update;
import com.evh98.vision.util.Util;

public class SplashScreen extends VisionScreen {
	
	public SplashScreen(Vision vision) {
		super(vision);
	}
	
	@Override
	public void show() {
		Graphics.splash = Graphics.createSprite(Gdx.files.internal("splash.png"));
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.95F, 0.95F, 0.95F, 1F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Graphics.camera.update();
		
		sprite_batch.setProjectionMatrix(Graphics.camera.combined);
		shape_renderer.setProjectionMatrix(Graphics.camera.combined);
		
		Graphics.drawSplash(sprite_batch);
		
		new Thread() {
			public void run() {
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
			            init();
					}
				});
			}
		}.start();
	}
	
	private void init() {
		initServer();
		initAssets();
		initObjects();
		initScreens();
		
		checkUpdate();
	}
	
	private void initServer() {
		vision.server = new Server();
		vision.server.start();
		try {
			vision.server.bind(4567, 28960);
		} catch (IOException e) {
			e.printStackTrace();
		}
		vision.server.addListener(new RemoteListener());
	}
	
	private void initAssets() {
		Graphics.loadAll();
	}
	
	private void initObjects() {
		Vision.search = new Search(vision);
		Vision.assistant = new Assistant(vision);
		Vision.loading = new Loading();
		Vision.games = new ArrayList<Game>();
		Vision.apps = new ArrayList<App>();
		Vision.movies = new ArrayList<Movie>();
	}
	
	private void initScreens() {
        vision.home_screen = new HomeScreen(vision);
        vision.games_screen = new GamesScreen(vision);
        vision.media_screen = new MediaScreen(vision);
        vision.apps_screen = new AppsScreen(vision);
        vision.system_screen = new SystemScreen(vision);
        vision.update_screen = new UpdateScreen(vision);
        
        vision.movies_screen = new MoviesScreen(vision);
        vision.youtube_screen = new YouTubeScreen(vision);
	}
	
	private void checkUpdate() {
		if (Util.isNetworkAvailable()) {
			if (!Update.isAvailable()) {
				vision.setScreen(vision.home_screen);
			} else {
				vision.setScreen(vision.update_screen);
			}
		}
	}
}