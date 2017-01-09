/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.evh98.vision.Vision;
import com.evh98.vision.ui.Loading;
import com.evh98.vision.ui.Pane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Icons;
import com.evh98.vision.util.Palette;

public class HomeScreen implements Screen {

	Vision vision;
	SpriteBatch sprite_batch;
	ShapeRenderer shape_renderer;
	
	// Cursor position
	int x = 0, y = 0;
	
	// TODO: Exit dialog
	
	Pane games = new Pane(Palette.RED, "Games", Icons.GAMES, -1600, -832);
	Pane media = new Pane(Palette.BLUE, "Media", Icons.MOVIES, 64, -832);
	Pane apps = new Pane(Palette.YELLOW, "Apps", Icons.APPS, -1600, 48);
	Pane system = new Pane(Palette.GREEN, "System", Icons.SETTINGS, 64, 48);
	
	Loading loading;
	
	public HomeScreen(Vision vision) {
		this.vision = vision;
		
		sprite_batch = new SpriteBatch();
		shape_renderer = new ShapeRenderer();
		loading = new Loading();
	}

	@Override
	public void show() {
		Graphics.particles.start();
		Graphics.setParticles(Palette.WHITE);
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
		
		draw(delta);
		
		if (Vision.search.isActive()) {
			Vision.search.render(sprite_batch, shape_renderer);
			Vision.search.update();
		} else if (Vision.assistant.isActive()) {
			Vision.assistant.render(sprite_batch, shape_renderer);
			Vision.assistant.update();
		} else {
			update();
		}
	}
	
	private void draw(float delta) {
		if (x == 1 && y == 1) {
			games.renderAlt(sprite_batch, shape_renderer);
			
			if (Controller.isGreen() && !Vision.search.isActive()) {
				vision.setScreen(vision.games_screen);
			}
		} else {
			games.render(sprite_batch, shape_renderer);
		}
		
		if (x == 2 && y == 1) {
			media.renderAlt(sprite_batch, shape_renderer);
			
			if (Controller.isGreen() && !Vision.search.isActive()) {
				vision.setScreen(vision.media_screen);
			}
		} else {
			media.render(sprite_batch, shape_renderer);
		}
		
		if (x == 1 && y == 2) {
			apps.renderAlt(sprite_batch, shape_renderer);
			
			if (Controller.isGreen() && !Vision.search.isActive()) {
				vision.setScreen(vision.apps_screen);
			}
		} else {
			apps.render(sprite_batch, shape_renderer);
		}
		
		if (x == 2 && y == 2) {
			system.renderAlt(sprite_batch, shape_renderer);
			
			if (Controller.isGreen() && !Vision.search.isActive()) {
				vision.setScreen(vision.system_screen);
			}
		} else {
			system.render(sprite_batch, shape_renderer);
		}
	}
	
	private void update() {
		if (Controller.isSearch()) {
			Vision.search.toggleSearch();
		}
		else if (Controller.isUp()) {
				if (y == 0) {
					y = 1;
					x = 1;
				} else if (y == 2) {
					y = 1;
				}
			}
			else if (Controller.isDown()) {
				if (y == 0 || y == 1) {
					y++;
					if (x == 0) {
						x = 1;
					}
				}
			}
			else if (Controller.isLeft()){
				if(x==0){
					y = 1;
					x = 1;
				} else if (x == 2) {
					x = 1;
				}
			}
			else if (Controller.isRight()){
				if(x == 0 || x == 1){
					x++;
					if (y == 0){
						y = 1;
					}
				}
			}
			else if (Controller.isBlue()) {
				Vision.search.feelingLuckyMovies("the lego movie");
			}
			else if (Controller.isRed()) {
				Graphics.dispose();
				vision.server.stop();
				Gdx.app.exit();
			}
	}

	@Override
	public void dispose() {
		sprite_batch.dispose();
		shape_renderer.dispose();
	}

	@Override public void hide() {}

	@Override public void pause() {}

	@Override public void resize(int arg0, int arg1) {}

	@Override public void resume() {}
}