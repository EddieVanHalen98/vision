/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 *
 * MediaScreen.java
 * Media screen
 *
 * File created on 25th April 2016
 */

package com.evh98.vision.apps;

import java.awt.Desktop;
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
		
		File[] files = new File(System.getProperty("user.home") + "/Movies/").listFiles();
		
		int i = 0;
		for (File file : files) {
	        if (file.getName().contains(".mp4") || file.getName().contains(".mkv") || file.getName().contains(".avi")) {
	        	panes.add(new MediaPane(Palette.PINK, getPosterURL(parseName(file)), 1920 - (222 + (i * 660)), -798, file));
	        }
	        i++;
	    }
	}
	
	@Override
	public void render() {
		Graphics.drawBackground(gc, Graphics.background_pink);
		
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
					for (int i = 0; i < 6; i++) {
						if (panesPos[i][0] == x && panesPos[i][1] == y) {
							try {
								Desktop.getDesktop().open(panes.get(i).getPath());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
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