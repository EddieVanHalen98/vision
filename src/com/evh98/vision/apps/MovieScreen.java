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
	
	@Override
	public void exit() {
		Vision.setScreen(Vision.media_screen);
	}
}