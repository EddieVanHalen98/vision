/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.evh98.vision.Vision;
import com.evh98.vision.ui.SmallPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Icons;
import com.evh98.vision.util.Palette;

public class MediaScreen implements Screen {

	Vision vision;
	SpriteBatch sprite_batch;
	ShapeRenderer shape_renderer;
	
	int x = 1, y = 1;
	
	ArrayList<SmallPane> panes;
	int[][] panesPos = {{1, 1}, {2, 1}, {3, 1}, {4, 1}, {1, 2}, {2, 2}};
	
	public MediaScreen(Vision vision) {
		this.vision = vision;
		
		sprite_batch = new SpriteBatch();
		shape_renderer = new ShapeRenderer();
		
		panes = new ArrayList<SmallPane>();
		panes.add(new SmallPane(vision.movies_screen, Palette.BLUE, Palette.PINK, "Movies", Icons.MOVIES, -1728, -810));
		panes.add(new SmallPane(vision.home_screen, Palette.BLUE, Palette.RED, "Netflix", Icons.TV_GUIDE, -816, -810));
		panes.add(new SmallPane(vision.youtube_screen, Palette.BLUE, Palette.RED, "YouTube", Icons.YOUTUBE, 96, -810));
		panes.add(new SmallPane(vision.home_screen, Palette.BLUE, Palette.YELLOW, "Music", Icons.MUSIC, 1008, -810));
		panes.add(new SmallPane(vision.home_screen, Palette.BLUE, Palette.GREEN, "Spotify", Icons.SPOTIFY, -1728, 90));
		panes.add(new SmallPane(vision.home_screen, Palette.BLUE, Palette.PURPLE, "Photos", Icons.COLLECTIONS, -816, 90));
	}

	@Override 
	public void show() {
		Graphics.setParticles(Palette.BLUE);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.95F, 0.95F, 0.95F, 1F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Graphics.camera.update();
		
		sprite_batch.setProjectionMatrix(Graphics.camera.combined);
		shape_renderer.setProjectionMatrix(Graphics.camera.combined);
		
		sprite_batch.begin();
			Graphics.particles.draw(sprite_batch, delta);
		sprite_batch.end();
		
        draw();
        
        if (vision.search.isActive()) {
			vision.search.render(sprite_batch, shape_renderer);
			vision.search.update();
		} else {
			update();
		}
	}
	
	private void draw() {
		for (int i = 0; i < panes.size(); i++) {
            if (panesPos[i][0] == x && panesPos[i][1] == y) {
                panes.get(i).renderAlt(sprite_batch, shape_renderer);
            } else {
                panes.get(i).render(sprite_batch, shape_renderer);
            }
        }
	}
	
	private void update() {
		if (Controller.isSearch()) {
			vision.search.toggleSearch();
		}
		else if (Controller.isGreen()) {
            for (int i = 0; i < panes.size(); i++) {
                if (panesPos[i][0] == x && panesPos[i][1] == y) {
//                	System.out.println(panes.get(i).getScreen().toString());
                    vision.setScreen(panes.get(i).getScreen());
                    vision.server.sendToAllTCP(panes.get(i).getText());
                }
            }
        }
        else if (Controller.isRed()) {
        	vision.setScreen(vision.home_screen);
        }
        else if (Controller.isUp() && y == 2) {
			y = 1;
		}
		else if (Controller.isDown() && y == 1) {
			y = 2;
		}
		else if (Controller.isRight() && x >= 1 && x <= 3) {
			x++;
		}
		else if (Controller.isLeft() && x >= 2) {
			x--;
		}
	}

	@Override
	public void dispose() {
		
	}

	@Override public void hide() {}
	@Override public void pause() {}
	@Override public void resize(int arg0, int arg1) {}
	@Override public void resume() {}
}