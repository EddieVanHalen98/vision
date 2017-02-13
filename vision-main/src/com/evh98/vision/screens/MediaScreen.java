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
	int[][] panesPos = {{1, 1}, {2, 1}, {3, 1}, {4, 1}, {1, 2}, {2, 2}, {3, 2}, {4, 2}};
	
	public MediaScreen(Vision vision) {
		super(vision);
		
		panes = new ArrayList<SmallPane>();
		panes.add(new SmallPane(vision.movies_screen, Palette.BLUE, Palette.PINK, "Movies", Icons.MOVIES_ALT, panesPos[0]));
		panes.add(new SmallPane(vision.youtube_screen, Palette.BLUE, Palette.RED, "YouTube", Icons.YOUTUBE, panesPos[1]));
		panes.add(new SmallPane("http://www.plex.tv", Palette.BLUE, Palette.YELLOW, "Plex", Icons.PLEX, panesPos[2]));
		panes.add(new SmallPane("http://network.wwe.com", Palette.BLUE, Palette.BLACK, "Network", Icons.WWE, panesPos[3]));
		panes.add(new SmallPane("http://www.netflix.com", Palette.BLUE, Palette.RED, "Netflix", Icons.NETFLIX, panesPos[4]));
		panes.add(new SmallPane("http://www.spotify.com", Palette.BLUE, Palette.GREEN, "Spotify", Icons.SPOTIFY, panesPos[5]));
		panes.add(new SmallPane("http://photos.google.com", Palette.BLUE, Palette.PURPLE, "Photos", Icons.COLLECTIONS, panesPos[6]));
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
                    panes.get(i).open(vision);
                    vision.server.sendToAllTCP(panes.get(i).getText());
                }
            }
        }
        else if (Controller.isRed()) {
        	vision.setScreen(vision.home_screen);
        }
		if(Controller.isNavigationKey()) {
			int[] newCoords = Controller.getNewXY(x, y, 4, 2, 8);
			x = newCoords[0];
			y = newCoords[1];
		}
	}

	@Override
	public void dispose() {
		
	}
}