/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.screens;

import com.evh98.vision.Vision;
import com.evh98.vision.ui.ExplorerWindow;
import com.evh98.vision.ui.Pane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Icons;
import com.evh98.vision.util.Palette;

public class HomeScreen extends VisionScreen {
	
	// Cursor position
	int x = -1, y = -1;
	
	// TODO: Exit dialog
	
	Pane games = new Pane(Palette.RED, "Games", Icons.GAMES, -1600, -832);
	Pane media = new Pane(Palette.BLUE, "Media", Icons.MOVIES, 64, -832);
	Pane apps = new Pane(Palette.YELLOW, "Apps", Icons.APPS, -1600, 48);
	Pane system = new Pane(Palette.GREEN, "System", Icons.SETTINGS, 64, 48);
	
	public HomeScreen(Vision vision) {
		super(vision);
		
		window = new ExplorerWindow(System.getProperty("user.home"), "folders", Palette.SYSTEM);
	}

	@Override
	public void show() {
		Graphics.particles.start();
		
		start(Palette.WHITE, "default");
	}
	
	@Override
	public void draw(float delta) {
		if (x == 1 && y == 1) {
			games.renderAlt(sprite_batch, shape_renderer);
			
			if (Controller.isGreen()) vision.setScreen(vision.games_screen);
		} else {
			games.render(sprite_batch, shape_renderer);
		}
		
		if (x == 2 && y == 1) {
			media.renderAlt(sprite_batch, shape_renderer);
			
			if (Controller.isGreen()) vision.setScreen(vision.media_screen);
		} else {
			media.render(sprite_batch, shape_renderer);
		}
		
		if (x == 1 && y == 2) {
			apps.renderAlt(sprite_batch, shape_renderer);
			
			if (Controller.isGreen()) vision.setScreen(vision.apps_screen);
		} else {
			apps.render(sprite_batch, shape_renderer);
		}
		
		if (x == 2 && y == 2) {
			system.renderAlt(sprite_batch, shape_renderer);
			
			if (Controller.isGreen()) vision.setScreen(vision.system_screen);
		} else {
			system.render(sprite_batch, shape_renderer);
		}
	}
	
	@Override
	public void update() {
		if (Controller.isRed()) {
			vision.terminate();
		}
		if(Controller.isNavigationKey()){
			int[] newCoords = Controller.getNewXY(x, y, 2, 2, 4);
			x = newCoords[0];
			y = newCoords[1];
		}
	}

	@Override
	public void dispose() {
		sprite_batch.dispose();
		shape_renderer.dispose();
	}
}