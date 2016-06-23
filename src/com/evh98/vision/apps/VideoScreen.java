/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 *
 * MediaScreen.java
 * Media screen
 *
 * File created on 25th April 2016
 */

package com.evh98.vision.apps;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.evh98.vision.Vision;
import com.evh98.vision.screens.Screen;
import com.evh98.vision.ui.MediaPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class VideoScreen extends Screen {

	int x = 0;
	int y = 0;
	
	ArrayList<MediaPane> panes;
    int[][] panesPos = {{1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {1, 2}, {2, 2}, {3, 2}, {4, 2}, {5, 2}};
	
	public VideoScreen(GraphicsContext gc) {
		super(gc);
	}

	@Override
	public void start() {
		panes = new ArrayList<MediaPane>();
		
		File[] files = new File("D:/Movies/").listFiles();
		
		int i = 0;
		for (File file : files) {
	        if (file.getName().contains(".mp4") || file.getName().contains(".mkv")) {
	        	panes.add(new MediaPane(Palette.PINK, getPosterURL(parseName(file)), 222 + (i * 660), 282, file));
	        }
	        i++;
	    }
	}
	
	@Override
	public void render() {
		Graphics.drawImageRaw(gc, Graphics.background_pink, 0, 0);
		
		for (MediaPane pane: panes) {
			pane.render(gc);
		}
	}
	
	@Override
	public void update(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				if (Controller.isLeft(e)) {
					if (x >= 2 && x <= 4) {
						x--;
					}
				}
				if (Controller.isRight(e)) {
					if (x >= 0 && x <= 3) {
						x++;
						if (y == 0) {
							y = 1;
						}
					}
				}
				if (Controller.isUp(e)) {
					if (y == 2) {
						y = 1;
					}
				}
				if (Controller.isDown(e)) {
					if (y == 0 || y == 1) {
						y++;
						if (x == 0) {
							x = 1;
						}
					}
				}
				if (Controller.isGreen(e)) {
					MediaPlayer mp = new MediaPlayer(new Media(panes.get(2).getPath().toURI().toString()));
				    MediaView mv = new MediaView(mp);
				    mv.setFitWidth(Vision.WIDTH * Vision.SCALE);
				    mv.setFitHeight(Vision.HEIGHT * Vision.SCALE);
				    mv.setY(((Vision.HEIGHT / 2) - (mv.getFitHeight() / 4))* Vision.SCALE);
				    Vision.root.getChildren().add(mv);
				    mp.play();
				    /*
					for (int i = 0; i < 6; i++) {
						if (panesPos[i][0] == x && panesPos[i][1] == y) {
							mp = new MediaPlayer(new Media(panes.get(i).getPath().toURI().toString()));
						    mv = new MediaView(mp);
						    Vision.root.getChildren().add(mv);
						    mp.play();
						}
					}
					*/
				}
				if (Controller.isRed(e)) {
					Vision.setScreen(Vision.main_screen);
				}
			}
		});
	}
	
	public String parseName(File file) {
		String name = file.getName();
		name = name.replace("mp4", "");
		name = name.replace("mkv", "");
		name = name.replaceAll("[^a-zA-Z]", " ");
		System.out.println(name);
		return name;
	}
	
	public String getPosterURL(String name) {
		String sURL = "http://www.omdbapi.com/?t=" + name + "&plot=short&r=json";
		sURL = sURL.replace(" ", "%20");

		try {
			URL url = new URL(sURL);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.connect();

			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			JsonObject rootobj = root.getAsJsonObject();

			return rootobj.get("Poster").getAsString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "null";
	}
}