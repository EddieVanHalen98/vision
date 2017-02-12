package com.evh98.vision.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.evh98.vision.Vision;
import com.evh98.vision.ui.Window;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

public class VisionScreen implements Screen {

	protected Vision vision;
	protected SpriteBatch sprite_batch;
	protected ShapeRenderer shape_renderer;
	protected Window window;
	
	public VisionScreen(Vision vision) {
		this.vision = vision;
		
		sprite_batch = new SpriteBatch();
		shape_renderer = new ShapeRenderer();
		
		window = new Window(0, 0, Palette.LIGHT_GRAY, Palette.SYSTEM);
	}
	
	@Override
	public void show() {
		
	}
	
	public void start(Color color, String screenName) {
		Graphics.setParticles(color);
		
		vision.server.sendToAllTCP("default");
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

		if (Vision.loading.isActive()) {
			Vision.loading.render(sprite_batch, delta);
		}
		else if (window.isActive()) {
			window.render(sprite_batch, shape_renderer);
			window.update();
		}
		else if (Vision.search.isActive()) {
			Vision.search.render(sprite_batch, shape_renderer);
			Vision.search.update();
		} else {
			if (Controller.isSearch()) {
				Vision.search.toggleSearch();
			}
			update();
		}
	}
	
	public void draw(float delta) {
		
	}
	
	public void update() {
		
	}
	
	@Override
	public void dispose() {
		
	}

	@Override public void hide() {}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void resize(int width, int height) {}
}