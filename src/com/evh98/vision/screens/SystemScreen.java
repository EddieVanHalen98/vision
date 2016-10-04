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

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class SystemScreen extends Screen {

	int position = 0;
	int x = -1;
	int y = -1;
	
	ArrayList<SettingsPane> panes;
	int[][] panesPos = {{1, 1}, {2, 1}, {3, 1}, {4, 1}, {1, 2}, {2, 2}, {3, 2}, {4, 2}};
	
	String input = "";
	
	public SystemScreen(GraphicsContext gc) {
		super(gc);
		
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
		
		switch (position) {
		case 0:
			for (int i = 0; i < panes.size(); i++) {
				if (panesPos[i][0] == x && panesPos[i][1] == y) {
					panes.get(i).renderAlt(gc);
				} else {
					panes.get(i).render(gc);
				}
			}
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			
		}
	}
	
	@Override
	public void update(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				int [] newCoords = getNewXY(e, x, y, 4, 2, 6);
				x = newCoords[0];
				y = newCoords[1];

				if (Controller.isSearch(e)) {
					Vision.search.toggleSearch();
				}
				
				if (position == 0) {
					
				}
				switch (position) {
				case 0:
					if (Controller.isGreen(e)) {
						for (int i = 0; i < 6; i++) {
							if (panesPos[i][0] == x && panesPos[i][1] == y) {
								position = i;
							}
						}
					}
					else if (Controller.isRed(e)) {
						Vision.setScreen(Vision.main_screen);
					}
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					
				}
			}
		});
	}
}