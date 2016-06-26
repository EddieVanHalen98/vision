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
import com.evh98.vision.ui.MediaPane;
import com.evh98.vision.ui.SmallPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import jiconfont.icons.FontAwesome;
import jiconfont.icons.GoogleMaterialDesignIcons;

import static com.evh98.vision.Vision.WIDTH;
import static com.evh98.vision.Vision.HEIGHT;

public class MediaScreen extends Screen {

	int x = 0;
	int y = 0;

	GridPane grid;

	MediaPane videos;
	MediaPane netflix;
	MediaPane youtube;
	MediaPane music;
	MediaPane spotify;
	MediaPane photos;

	public MediaScreen(GraphicsContext gc) {
		super(gc);
	}

	@Override
	public void start() {
		super.start();

		root.getStylesheets().add("file:assets/style/panes.css");

		grid = new GridPane();
		grid.setPadding(new Insets((HEIGHT * 0.2) / 3, Vision.WIDTH * 0.04, (HEIGHT * 0.2) / 3, Vision.WIDTH * 0.04));
		grid.setHgap(WIDTH * 0.04);
		grid.setVgap((HEIGHT * 0.2) / 3);

		videos = new MediaPane("Videos", GoogleMaterialDesignIcons.MOVIE, new String[]{"media-pane", "pink"}, new float[]{WIDTH * 0.2f, HEIGHT * 0.4f});
		netflix = new MediaPane("Netflix", GoogleMaterialDesignIcons.PLACE, new String[]{"media-pane", "red"}, new float[]{WIDTH * 0.2f, HEIGHT * 0.4f});
		youtube = new MediaPane("YouTube", FontAwesome.YOUTUBE, new String[]{"media-pane", "red"}, new float[]{WIDTH * 0.2f, HEIGHT * 0.4f});
		music = new MediaPane("Music", GoogleMaterialDesignIcons.VOLUME_UP, new String[]{"media-pane", "yellow"}, new float[]{WIDTH * 0.2f, HEIGHT * 0.4f});
		spotify = new MediaPane("Spotify", FontAwesome.SPOTIFY, new String[]{"media-pane", "green"}, new float[]{WIDTH * 0.2f, HEIGHT * 0.4f});
		photos = new MediaPane("Photos", GoogleMaterialDesignIcons.COLLECTIONS, new String[]{"media-pane", "purple"}, new float[]{WIDTH * 0.2f, HEIGHT * 0.4f});

		grid.addRow(0, videos.getPane(), netflix.getPane(), youtube.getPane(), music.getPane());
		grid.addRow(1, spotify.getPane(), photos.getPane());

		root.getChildren().add(grid);

		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				if (Controller.isLeft(e)) {
					x--;
				}
				if (Controller.isRight(e)) {
					x++;
				}
				if (Controller.isUp(e)) {
					y--;
				}
				if (Controller.isDown(e)) {
					y++;
				}

				if(y == 3) y = 2;
				if(y == 0) y = 1;
				if(x < 1) x = 1;
				if(x > 4 && y == 1) {
					y = 2;
					x = 1;
				}
				if(x > 2 && y == 2) x = 2;

				if (Controller.isGreen(e)) {
					for(Node n : grid.getChildren()) {
						if (grid.getRowIndex(n) == (y - 1) && grid.getColumnIndex(n) == (x - 1)) {
							if(x == 1 && y == 1) {
//                                Vision.setScreen(n.getScreen());
//                                Vision.server.sendToAllTCP();
							}
							if(x == 2 && y == 1) {
//                                Vision.setScreen(n.getScreen());
//                                Vision.server.sendToAllTCP();
							}
							if(x == 3 && y == 1) {
								Vision.setScreen(Vision.youtube_screen);
								Vision.server.sendToAllTCP("YouTube");
							}
							if(x == 4 && y == 1) {
//                                Vision.setScreen(n.getScreen());
//                                Vision.server.sendToAllTCP();
							}
							if(x == 1 && y == 2) {
//                                Vision.setScreen(n.getScreen());
//                                Vision.server.sendToAllTCP();
							}
							if(x == 1 && y == 2) {
//                                Vision.setScreen(n.getScreen());
//                                Vision.server.sendToAllTCP();
							}
						}
					}
				}
				if (Controller.isRed(e)) {
					Vision.setScreen(Vision.main_screen);
				}

				updateUI();
			}
		});
	}

	public void updateUI() {
		videos.getPane().getStyleClass().remove("active");
		netflix.getPane().getStyleClass().remove("active");
		youtube.getPane().getStyleClass().remove("active");
		music.getPane().getStyleClass().remove("active");
		spotify.getPane().getStyleClass().remove("active");
		photos.getPane().getStyleClass().remove("active");

		for(Node n : grid.getChildren()) {
			if(grid.getRowIndex(n) == (y - 1) && grid.getColumnIndex(n) == (x - 1)) {
				n.getStyleClass().add("active");
			}
		}
	}

	@Override
	public void render() {
		Graphics.drawBackground(gc, Graphics.background_blue);
	}

	@Override
	public void update() {

	}
}