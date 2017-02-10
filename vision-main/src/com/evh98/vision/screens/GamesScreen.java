/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.screens;

import java.util.ArrayList;

import com.evh98.vision.Vision;
import com.evh98.vision.ui.MediaPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

public class GamesScreen extends VisionScreen {
	
    int x = 0;
    int firstItem = 0;
    
    ArrayList<MediaPane> panes;
	
	public GamesScreen(Vision vision) {
		super(vision);
		
		panes = new ArrayList<MediaPane>();
		
		scan();
	}

	@Override
	public void show() {
		start(Palette.RED, "default");
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
        	vision.setScreen(vision.home_screen);
        }
		else if (Controller.isGreen()) {
			
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
		generatePanes();
	}
	
	private void generatePanes() {
		panes.add(new MediaPane(Palette.RED, "Manage Games", Graphics.default_manage));
		panes.add(new MediaPane(Palette.RED, "Add Game", Graphics.default_add));
	}
	
	@Override
	public void dispose() {
		sprite_batch.dispose();
		shape_renderer.dispose();
	}
}