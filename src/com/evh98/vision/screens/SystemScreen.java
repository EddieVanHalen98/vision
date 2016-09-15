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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SystemScreen extends Screen {

	int position = 0;
	int x = 0;
	int y = 0;
	
	ArrayList<SettingsPane> panes;
	int[][] panesPos = {{1, 1}, {2, 1}, {3, 1}, {4, 1}, {1, 2}, {2, 2}, {3, 2}, {4, 2}};
	
	String input = "";
	
	public SystemScreen(GraphicsContext gc) {
		super(gc);
	}
	
	@Override
	public void start() {
		panes = new ArrayList<SettingsPane>();
		panes.add(new SettingsPane("Account", Icons.Material, Icons.PERSON, -1536, -768));
		panes.add(new SettingsPane("Display", Icons.Material, Icons.DISPLAY, -768, -768));
		panes.add(new SettingsPane("Locations", Icons.Material, Icons.FOLDER, 0, -768));
	}

	@Override
	public void render() {
		Graphics.drawBackground(gc, Graphics.background_green);
		
		if (position == 0) {
			for (int i = 0; i < panes.size(); i++) {
				if (panesPos[i][0] == x && panesPos[i][1] == y) {
					panes.get(i).renderAlt(gc);
				} else {
					panes.get(i).render(gc);
				}
			}
		}
		else if (position == 1) {
			
		}
		else if (position == 2) {
			
		}
		else if (position == 3) {
			
		}
	}
	
	@Override
	public void update(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				int [] newCoords = getNewXY(e, x, y, 4, 2, 6);
				x = newCoords[0];
				y = newCoords[1];
				

				if (Controller.isSearch(e)) {
					Vision.search.toggleSearch();
				}
				
				if (position == 0) {
					if (Controller.isGreen(e)) {
						
					}
					else if (Controller.isRed(e)) {
						Vision.setScreen(Vision.main_screen);
					}
				}
				else if (position == 1) {
					
				}
				else if (position == 2) {
					
				}
				else if (position == 3) {
					
				}
			}
		});
	}
}