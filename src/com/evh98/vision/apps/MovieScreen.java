/**
 * Vision
 * 
 * Created and owned by James T Saeed (EddieVanHalen98)
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
import com.evh98.vision.util.Data;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class MovieScreen extends Screen {

	int x = 0;
	int firstItem = 0;
	
	ArrayList<MediaPane> panes;
	
	public MovieScreen(GraphicsContext gc, Group root, Scene scene) {
		super(gc, root, scene);
		
		panes = new ArrayList<MediaPane>();
	}
	
	@Override
	public void start() {
		File f = new File(Vision.HOME + "data/movies.dat");
		
		if (f.exists()) {
			try {
				Data.loadMovies();
			} catch (Exception e) {
				e.printStackTrace();
			}
			generatePanes();
		} else {
			scan();
			try {
				Data.saveMovies();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void render() {
		Graphics.drawBackground(gc, Graphics.background_pink);
		
		for (int i = firstItem; i < firstItem + 4; i++) {
			if ((x - 1) == i) {
				// Provide x position. Minus the first item so the first item is as if it was item 0 (so it renders at start of screen)
				panes.get(i).renderAlt(gc, Vision.movies.get(i).getPoster(), getXforPane(i - firstItem));
			} else {
				panes.get(i).render(gc, Vision.movies.get(i).getPoster(), getXforPane(i - firstItem));
			}
		}
	}
	
	@Override
	public void update(KeyEvent e) {
		// As with other screens, get new coords, but only x
		int [] newCoords = getNewXY(e, x, 0, panes.size(), 1, panes.size());
		x = newCoords[0];
		// If we are past first, don't be
		while(x > firstItem + 4) {
			firstItem++;
		}
		while(x < firstItem + 1) {
			firstItem--;
		}
					
		if (Controller.isGreen(e)) {
			try {
				Desktop.getDesktop().open(Vision.movies.get(x - 1).getFile());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		else if (Controller.isBlue(e)) {
			System.out.println("Blue");
			scan();
			try {
				Data.saveMovies();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public float getXforPane(int pane){
		return -1920 + (184 + (pane * 914));
	}
	
	public String[] parseFilm(String name) {
		String title = name.substring(0, name.length() - 11);
		String year = name.substring(name.length() - 9, name.length() - 5);
		String[] s = {title, year};
		
		return s;
	}
	
	public void scan() {
		File[] files = new File(Data.MOVIES_FILEPATH).listFiles();

		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(".mp4") || files[i].getName().contains(".mkv") || files[i].getName().contains(".avi")) {
				String[] s = parseFilm(files[i].getName().toString());
	        	Vision.movies.add(new Movie(s[0], s[1], files[i]));
	        }
		}
		
		generatePanes();
	}
	
	public void generatePanes() {
		for (int i = 0; i < Vision.movies.size(); i++) {
        	panes.add(new MediaPane(Palette.PINK, Vision.movies.get(i).getTitle(), -1920 + (184 + (i * 914)), -670));
		}
	}
	
	@Override
	public void exit() {
		Vision.setScreen(Vision.media_screen);
	}
}