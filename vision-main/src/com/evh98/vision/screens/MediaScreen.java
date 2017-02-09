/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.screens;

import java.util.ArrayList;

import com.evh98.vision.Vision;
import com.evh98.vision.ui.SmallPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Icons;
import com.evh98.vision.util.Palette;

public class MediaScreen extends VisionScreen {
	
	int x = -1, y = -1;
	
	ArrayList<SmallPane> panes;
	int[][] panesPos = {{1, 1}, {2, 1}, {3, 1}, {4, 1}, {1, 2}, {2, 2}};
	
	public MediaScreen(Vision vision) {
		super(vision);
		
		panes = new ArrayList<SmallPane>();
		panes.add(new SmallPane(vision.movies_screen, Palette.BLUE, Palette.PINK, "Movies", Icons.MOVIES, -1728, -810));
		panes.add(new SmallPane(vision.home_screen, Palette.BLUE, Palette.RED, "Netflix", Icons.TV_GUIDE, -816, -810));
		panes.add(new SmallPane(vision.youtube_screen, Palette.BLUE, Palette.RED, "YouTube", Icons.YOUTUBE, 96, -810));
		panes.add(new SmallPane(vision.home_screen, Palette.BLUE, Palette.YELLOW, "Music", Icons.MUSIC, 1008, -810));
		panes.add(new SmallPane(vision.home_screen, Palette.BLUE, Palette.GREEN, "Spotify", Icons.SPOTIFY, -1728, 90));
		panes.add(new SmallPane(vision.home_screen, Palette.BLUE, Palette.PURPLE, "Photos", Icons.COLLECTIONS, -816, 90));
	}

	@Override 
	public void show() {
		start(Palette.BLUE, "default");
	}
	
	@Override
	public void draw(float delta) {
		for (int i = 0; i < panes.size(); i++) {
            if (panesPos[i][0] == x && panesPos[i][1] == y) {
                panes.get(i).renderAlt(sprite_batch, shape_renderer);
            } else {
                panes.get(i).render(sprite_batch, shape_renderer);
            }
        }
	}
	
	@Override
	public void update() {
		if (Controller.isGreen()) {
            for (int i = 0; i < panes.size(); i++) {
                if (panesPos[i][0] == x && panesPos[i][1] == y) {
                    vision.setScreen(panes.get(i).getScreen());
                    vision.server.sendToAllTCP(panes.get(i).getText());
                }
            }
        }
        else if (Controller.isRed()) {
        	vision.setScreen(vision.home_screen);
        }
		if(Controller.isNavigationKey()){
			int[] newCoords = Controller.getNewXY(x, y, 4, 2, 6);
			x = newCoords[0];
			y = newCoords[1];
		}
	}

	@Override
	public void dispose() {
		
	}
}