/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.apps;

import java.io.File;
import java.util.ArrayList;

import com.evh98.vision.Vision;
import com.evh98.vision.media.Movie;
import com.evh98.vision.screens.VisionScreen;
import com.evh98.vision.ui.MediaPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

public class MoviesScreen extends VisionScreen {
	
    int x = 0;
    int firstItem = 0;
    
    ArrayList<MediaPane> panes;
    
	public MoviesScreen(Vision vision) {
		super(vision);
		
		panes = new ArrayList<MediaPane>();
		
		try {
			scan();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void show() {
		start(Palette.PINK, "default");
	}
	
	@Override
	public void draw(float delta) {
		for (int i = 0; i < panes.size(); i++) {
			if ((x - 1) == i) {
				panes.get(i).renderAlt(sprite_batch, shape_renderer, getXForPane(i - firstItem));
			} else {
				panes.get(i).render(sprite_batch, shape_renderer, getXForPane(i - firstItem));
			}
		}
	}
	
	private float getXForPane(int pane) {
		return -1920 + (184 + (pane * 914));
	}
	
	@Override
	public void update() {
		if (Controller.isRed()) {
            vision.setScreen(vision.media_screen);
        }
		else if (Controller.isGreen()) {
			Vision.movies.get(x - 1).open();
		}
		else if (Controller.isNavigationKey()) {
			int[] newCoords = Controller.getNewXY(x, 0, panes.size(), 1, panes.size());
			x = newCoords[0];
			
			while (x > firstItem + 4) {
				firstItem++;
			}
			while (x < firstItem + 1) {
				firstItem--;
			}
		}
	}
	
	private void scan() {
		Vision.movies.clear();
		panes.clear();
		
		File[] files = new File("C:/Movies/").listFiles();

		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(".mp4") || files[i].getName().contains(".mkv") || files[i].getName().contains(".avi")) {
				String[] s = parseFilm(files[i].getName().toString());
	        	Vision.movies.add(new Movie(s[0], s[1], files[i]));
	        }
		}
		
		generatePanes();
	}
	
	private void generatePanes() {
		for (int i = 0; i < Vision.movies.size(); i++) {
        	panes.add(new MediaPane(Palette.PINK, Vision.movies.get(i).getTitle(), Vision.movies.get(i).getPoster()));
		}
		
		panes.add(new MediaPane(Palette.PINK, "Manage Movies", Graphics.default_manage));
		panes.add(new MediaPane(Palette.PINK, "Add Movie", Graphics.default_add));
	}
	
	private String[] parseFilm(String name) {
		String title = name.substring(0, name.length() - 11);
		String year = name.substring(name.length() - 9, name.length() - 5);
		String[] s = {title, year};
		
		return s;
	}

	@Override
	public void dispose() {
		sprite_batch.dispose();
		shape_renderer.dispose();
	}
}