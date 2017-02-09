/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.screens;

import java.util.ArrayList;

import com.evh98.vision.Vision;
import com.evh98.vision.ui.GamePane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Palette;

public class GamesScreen extends VisionScreen {
	
    int x = 0, y = 0;
    
    ArrayList<GamePane> panes;
	
	public GamesScreen(Vision vision) {
		super(vision);
		
		panes = new ArrayList<GamePane>();
	}

	@Override
	public void show() {
		start(Palette.RED, "default");
	}
	
	@Override
	public void draw(float delta) {
		
	}
	
	@Override
	public void update() {
		if (Controller.isRed()) {
        	vision.setScreen(vision.home_screen);
        }
	}

	@Override
	public void dispose() {
		sprite_batch.dispose();
		shape_renderer.dispose();
	}
}