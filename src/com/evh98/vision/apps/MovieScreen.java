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
	int y = 0;
	
	ArrayList<MediaPane> panes;
    int[][] panesPos = {{1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {1, 2}, {2, 2}, {3, 2}, {4, 2}, {5, 2}};
	
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
	        	panes.add(new MediaPane(Palette.PINK, -1920 + (222 + (i * 660)), -798));
	        }
	        i++;
		}
		
		/*
		int i = 0;
		for (File file : files) {
	        if (file.getName().contains(".mp4") || file.getName().contains(".mkv") || file.getName().contains(".avi")) {
	        	panes.add(new MediaPane(Palette.PINK, getPosterURL(parseName(file)), 1920 - (222 + (i * 660)), -798, file));
	        }
	        i++;
	    }
	    */
	}
	
	@Override
	public void render() {
		Graphics.drawBackground(gc, Graphics.background_pink);
		
		for (int i = 0; i < panes.size(); i++) {
			panes.get(i).render(gc, Vision.movies.get(i).getPoster());
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
								Desktop.getDesktop().open(Vision.movies.get(i).getFile());
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
	
	public String[] parseFilm(String name) {
		String title = name.substring(0, name.length() - 11);
		String year = name.substring(name.length() - 9, name.length() - 5);
		String[] s = {title, year};
		
		return s;
	}
	
	/**
	 * First part returns title, second part returns year
	public String[] parseFilm(String name) {
		ArrayList<String> db = new ArrayList<String>();
		ArrayList<Double> results = new ArrayList<Double>();
		Levenshtein l = new Levenshtein();

		try {
			FileUtils.copyURLToFile(new URL("http://www.evh98.com/vision/moviedb"), new File(System.getProperty("user.home") + "/Vision/moviedb"));
			
			BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home") + "/Vision/moviedb"));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				db.add(sCurrentLine);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String movie: db) {
			results.add(l.distance(name, movie));
		}

		ArrayList<Double> temp = new ArrayList<Double>();
		
		for (double result: results) {
			temp.add(result);
		}
		
		Collections.sort(temp);
		
		int index = 0;
		
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).equals(temp.get(0))) {
				index = i;
			}
		}
		
		String s = db.get(index);
		s = s.replace(":", "");
		
		String[] sa = name.split("(");
		sa[1] = sa[1].replace(")", "");
		sa[1] = sa[1].replace(" ", "");
		sa[1] = sa[1].replace("&", "");
		sa[1] = sa[1].replace(",", "");
		sa[1] = sa[1].substring(sa[1].length() - 4, sa[1].length());
		
		return sa;
	}
	 */
}