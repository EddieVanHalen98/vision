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
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

public class AppsScreen implements Screen {

	Vision vision;
	SpriteBatch sprite_batch;
	ShapeRenderer shape_renderer;
	
    int x = 1, y = 1;
	
	public AppsScreen(Vision vision) {
		this.vision = vision;
		
		sprite_batch = new SpriteBatch();
		shape_renderer = new ShapeRenderer();
	}

	@Override
	public void show() {
		Graphics.setParticles(Palette.YELLOW);
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
		
	}
	
	private void update() {
		if (Controller.isSearch()) {
			vision.search.toggleSearch();
		}
		else if (Controller.isRed()) {
        	vision.setScreen(vision.home_screen);
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