/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.evh98.vision.Vision;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;
import com.evh98.vision.util.Update;

public class UpdateScreen implements Screen {

	Vision vision;
	SpriteBatch sprite_batch;
	ShapeRenderer shape_renderer;
    BitmapFont font;
	
    int stage = 0;
    String text = "An update is available!\nPress Space/Green to continue\nPress Escape/Red to cancel";
	
	public UpdateScreen(Vision vision) {
		this.vision = vision;
		
		sprite_batch = new SpriteBatch();
		shape_renderer = new ShapeRenderer();
		
        font = Graphics.createFont(Graphics.font_roboto_thin, 192);
        font.setColor(Palette.DARK_GRAY);
	}

	@Override
	public void show() {
		Graphics.setParticles(Palette.PURPLE);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.95F, 0.95F, 0.95F, 1F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Graphics.camera.update();
		update();
		
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
		sprite_batch.begin();
            Graphics.drawText(sprite_batch, font, text, 0, 0);
		sprite_batch.end();
	}
	
	private void update() {
        if (Controller.isGreen()) {
            if (stage == 0) {
                text = "Downloading...";
                Update.downloadUpdate();
                text = "Finished!\nPress Space/Green to continue";
                stage++;
            } else if (stage == 1) {
                Gdx.app.exit();
            }
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
