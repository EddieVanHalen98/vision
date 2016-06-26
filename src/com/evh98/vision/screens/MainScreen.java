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
import com.evh98.vision.ui.MainPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;

import com.evh98.vision.util.Palette;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.javafx.IconNode;

import static com.evh98.vision.Vision.WIDTH;
import static com.evh98.vision.Vision.HEIGHT;

public class MainScreen extends Screen {

	int x = 0;
	int y = 0;

	MainPane games;
	MainPane media;
	MainPane apps;
	MainPane system;

	public MainScreen(GraphicsContext gc) {
		super(gc);
	}

	@Override
	public void start() {
		super.start();

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
					  } else if (x == 2 && y == 1) {
						  Vision.setScreen(Vision.media_screen);
					  } else if (x == 1 && y == 2) {
//						Vision.setScreen(Vision.app_screen);
					  } else if (x == 2 && y == 2) {
//						system.renderAlt(gc);
					  }
				  }
				  if (Controller.isRed(e)) {
					  System.exit(0);
				  }

				  updateUI();
				  }
			  });

		root.getStylesheets().addAll("file:assets/style/panes.css");

		games = new MainPane("Games", GoogleMaterialDesignIcons.LIVE_TV, new String[]{"pane", "red"}, new float[]{WIDTH * 0.0833f, HEIGHT * 0.0833f, WIDTH * 0.375f, HEIGHT * 0.375f});
		media = new MainPane("Media", GoogleMaterialDesignIcons.LOCAL_MOVIES, new String[]{"pane", "blue"}, new float[]{WIDTH * 0.542f, HEIGHT * 0.0833f, WIDTH * 0.375f, HEIGHT * 0.375f});
		apps = new MainPane("Apps", GoogleMaterialDesignIcons.APPS, new String[]{"pane", "yellow"}, new float[]{WIDTH * 0.0833f, HEIGHT * 0.542f, WIDTH * 0.375f, HEIGHT * 0.375f});
		system = new MainPane("System", GoogleMaterialDesignIcons.SETTINGS, new String[]{"pane", "green"}, new float[]{ WIDTH * 0.542f, HEIGHT * 0.542f, WIDTH * 0.375f, HEIGHT * 0.375f});

		root.getChildren().addAll(games.getPane(), media.getPane(), apps.getPane(), system.getPane());
	}

	@Override
	public void render() {
		Graphics.drawBackground(gc, Graphics.background_colored);
	}

	@Override
	public void update() {

	}

	public void updateUI() {
		games.getPane().getStyleClass().remove("selected");
		media.getPane().getStyleClass().remove("selected");
		apps.getPane().getStyleClass().remove("selected");
		system.getPane().getStyleClass().remove("selected");

		games.setIconFill(Color.WHITE);
		media.setIconFill(Color.WHITE);
		apps.setIconFill(Color.WHITE);
		system.setIconFill(Color.WHITE);

		if(x == 1 && y == 1) {
			games.getPane().getStyleClass().add("selected");
			games.setIconFill(Palette.RED);
		}
		if(x == 2 && y == 1) {
			media.getPane().getStyleClass().add("selected");
			media.setIconFill(Palette.BLUE);
		}
		if(x == 1 && y == 2) {
			apps.getPane().getStyleClass().add("selected");
			apps.setIconFill(Palette.YELLOW);
		}
		if(x == 2 && y == 2) {
			system.getPane().getStyleClass().add("selected");
			system.setIconFill(Palette.GREEN);
		}
	}
}