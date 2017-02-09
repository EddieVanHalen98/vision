/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.screens;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Palette;

public class AppsScreen extends VisionScreen {
	
    int x = 1, y = 1;
	
	public AppsScreen(Vision vision) {
		super(vision);
	}

	@Override
	public void show() {
		start(Palette.YELLOW, "default");
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
		
	}
}