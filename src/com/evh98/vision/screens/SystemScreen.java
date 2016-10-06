/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 *
 * MediaScreen.java
 * Media screen
 *
 * File created on 20th April 2016
 */

package com.evh98.vision.screens;

import java.util.ArrayList;

import com.evh98.vision.Vision;
import com.evh98.vision.ui.SettingsPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Icons;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class SystemScreen extends Screen {

	int x = -1;
	int y = -1;
	
	ArrayList<SettingsPane> panes;
	int[][] panesPos = {{1, 1}, {2, 1}, {3, 1}, {4, 1}, {1, 2}, {2, 2}, {3, 2}, {4, 2}};
	
	String input = "";
	
	public SystemScreen(GraphicsContext gc, Group root, Scene scene) {
		super(gc, root, scene);
		
		panes = new ArrayList<SettingsPane>();
		panes.add(new SettingsPane("General", Icons.Material, Icons.SETTINGS, -1536, -768));
		panes.add(new SettingsPane("Account", Icons.Material, Icons.PERSON, -768, -768));
		panes.add(new SettingsPane("Display", Icons.Material, Icons.DISPLAY, 0, -768));
		panes.add(new SettingsPane("Locations", Icons.Material, Icons.FOLDER, 768, -768));
		panes.add(new SettingsPane("About", Icons.Material, Icons.INFO, -1536, 0));
	}

	@Override
	public void render() {
		Graphics.drawBackground(gc, Graphics.background_green);
		
		for (int i = 0; i < panes.size(); i++) {
			if (panesPos[i][0] == x && panesPos[i][1] == y) {
				panes.get(i).renderAlt(gc);
			} else {
				panes.get(i).render(gc);
			}
		}
	}
	
	@Override
	public void update(KeyEvent e) {
		int [] newCoords = getNewXY(e, x, y, 4, 2, 6);
		x = newCoords[0];
		y = newCoords[1];
				
		if (Controller.isGreen(e)) {
			Vision.setScreen(Vision.media_manager_screen);
			for (int i = 0; i < panes.size(); i++) {
				if (panesPos[i][0] == x && panesPos[i][1] == y) {
					switch(i) {
					case 0:
						break;
					default:
					}
				}
			}
		}
	}
	
	@Override
	public void exit() {
		Vision.setScreen(Vision.main_screen);
	}
}