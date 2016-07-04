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
import java.util.ArrayList;

import com.evh98.vision.Vision;
import com.evh98.vision.media.Movie;
import com.evh98.vision.screens.Screen;
import com.evh98.vision.ui.MediaPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class MovieScreen extends Screen {

	int x = 0;
	
	ArrayList<MediaPane> panes;
	
	public MovieScreen(GraphicsContext gc) {
		super(gc);
	}

	@Override
	public void start() {
		panes = new ArrayList<MediaPane>();
		
		File[] files = new File(System.getProperty("user.home") + "/Movies/").listFiles();

		int i = 0;
		for (File file : files) {
			if (file.getName().contains(".mp4") || file.getName().contains(".mkv") || file.getName().contains(".avi")) {
				String[] s = parseFilm(file.getName().toString());
	        	Vision.movies.add(new Movie(file, s[0], s[1]));
	        	panes.add(new MediaPane(Palette.PINK, s[0], -1920 + (184 + (i * 914)), -670));
		        i++;
	        }
		}
	}
	
	@Override
	public void render() {
		Graphics.drawBackground(gc, Graphics.background_pink);
		
		for (int i = 0; i < panes.size(); i++) {
			if ((x - 1) == i) {
				panes.get(i).renderAlt(gc, Vision.movies.get(i).getPoster());
			} else {
				panes.get(i).render(gc, Vision.movies.get(i).getPoster());
			}
		}
	}
	
	@Override
	public void update(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				if (Controller.isLeft(e)) {
					if (x >= 2 && x <= panes.size()) {
						x--;
					}
				}
				if (Controller.isRight(e)) {
					if (x >= 0 && x <= (panes.size() - 1)) {
						x++;
					}
				}
				if (Controller.isGreen(e)) {
					for (int i = 0; i < panes.size(); i++) {
						try {
							Desktop.getDesktop().open(Vision.movies.get(i).getFile());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				if (Controller.isRed(e)) {
					Vision.setScreen(Vision.media_screen);
				}
			}
		});
	}
	
	public String[] parseFilm(String name) {
		String title = name.substring(0, name.length() - 11);
		String year = name.substring(name.length() - 9, name.length() - 5);
		String[] s = {title, year};
		
		return s;
	}
}