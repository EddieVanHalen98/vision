/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 *
 * MainScreen.java
 * Home screen
 *
 * File created on 19th April 2016
 */

package com.evh98.vision.screens;

import com.evh98.vision.Vision;
import com.evh98.vision.ui.Pane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class MainScreen extends Screen {

	int x = 0;
	int y = 0;

	Pane games = new Pane(Palette.RED, "Games", '\uf2db', -1600, -832);
	Pane media = new Pane(Palette.BLUE, "Media", '\uf19d', 64, -832);
	Pane apps = new Pane(Palette.YELLOW, "Apps", '\uf313', -1600, 48);
	Pane system = new Pane(Palette.GREEN, "System", '\uf1c6', 64, 48);

	public MainScreen(GraphicsContext gc) {
		super(gc);
	}

	@Override
	public void render() {
		Graphics.drawBackground(gc, Graphics.background_colored);

		if (x == 1 && y == 1) {
			games.renderAlt(gc);
		} else {
			games.render(gc);
		}

		if (x == 2 && y == 1) {
			media.renderAlt(gc);
		} else {
			media.render(gc);
		}

		if (x == 1 && y == 2) {
			apps.renderAlt(gc);
		} else {
			apps.render(gc);
		}

		if (x == 2 && y == 2) {
			system.renderAlt(gc);
		} else {
			system.render(gc);
		}
	}

	@Override
	public void update(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				if (Controller.isLeft(e)) {
					if (x == 0) {
						y = 1;
						x = 1;
					} else if (x == 2) {
						x = 1;
					}
				}
				if (Controller.isRight(e)) {
					if (x == 0 || x == 1) {
						x++;
						if (y == 0) {
							y = 1;
						}
					}
				}
				if (Controller.isUp(e)) {
					if (y == 0) {
						y = 1;
						x = 1;
					} else if (y == 2) {
						y = 1;
					}
				}
				if (Controller.isDown(e)) {
					if (y == 0 || y == 1) {
						y++;
						if (x == 0) {
							x = 1;
						}
					}
				}
				if (Controller.isGreen(e)) {
					if (x == 1 && y == 1) {
//						Vision.setScreen(Vision.game_screen);
					}
					else if (x == 2 && y == 1) {
						Vision.setScreen(Vision.media_screen);
					}
					else if (x == 1 && y == 2) {
//						Vision.setScreen(Vision.app_screen);
					}
					else if (x == 2 && y == 2) {
						system.renderAlt(gc);
					}
				}
				if (Controller.isRed(e)) {
					System.exit(0);
				}
			}
		});
	}
}