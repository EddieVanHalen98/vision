/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.screens;

import java.util.ArrayList;

import com.evh98.vision.Vision;
import com.evh98.vision.ui.SettingsPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Icons;
import com.evh98.vision.util.Palette;

public class SystemScreen extends VisionScreen {
	
    int x = -1, y = 1;
	
	ArrayList<SettingsPane> panesMain;
	ArrayList<SettingsPane> panesMedia;
	
	public SystemScreen(Vision vision) {
		super(vision);
		
		panesMain = new ArrayList<SettingsPane>();
		panesMain.add(new SettingsPane("Display", Icons.TV_GUIDE, new int[]{1, 1}));
		panesMain.add(new SettingsPane("Search", Icons.SEARCH, new int[]{2, 1}));
		panesMain.add(new SettingsPane("Remote", Icons.PHONE, new int[]{3, 1}));
		panesMain.add(new SettingsPane("Accounts", Icons.ACCOUNT, new int[]{4, 1}));
		panesMain.add(new SettingsPane("Feedback", Icons.FEEDBACK, new int[]{5, 1}));
		panesMain.add(new SettingsPane("About", Icons.INFO, new int[]{6, 1}));
	}

	@Override
	public void show() {
		start(Palette.GREEN, "default");
	}
	
	@Override
	public void draw(float delta) {
		for (SettingsPane pane : panesMain) {
			if (pane.getPosition()[0] == x && y == 1) {
				pane.renderAlt(sprite_batch, shape_renderer);
			} else {
				pane.render(sprite_batch, shape_renderer);
			}
		}
		
		
	}
	
	@Override
	public void update() {
        if (Controller.isRed()) {
        	vision.setScreen(vision.home_screen);
        } 
        if (y == 1) {
            if (Controller.isLeft()) {
            	if (x == -1) x = 6;
            	else if (x > 1) x--;
            } else if (Controller.isRight()) {
            	if (x == -1) x = 1;
            	else if (x < 6) x++;
            } else if (Controller.isGreen()) {
            	
            }
        }
	}

	@Override
	public void dispose() {
		
	}
}