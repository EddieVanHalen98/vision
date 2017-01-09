/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.apps;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.evh98.vision.Vision;
import com.evh98.vision.media.Movie;
import com.evh98.vision.ui.MediaPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

public class MoviesScreen implements Screen {

	Vision vision;
	SpriteBatch sprite_batch;
	ShapeRenderer shape_renderer;
	
    int x = 0;
    
    ArrayList<MediaPane> panes;
    
    Robot robot;
	
	public MoviesScreen(Vision vision) {
		this.vision = vision;
		
		sprite_batch = new SpriteBatch();
		shape_renderer = new ShapeRenderer();
		
		panes = new ArrayList<MediaPane>();
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		scan();
	}

	@Override
	public void show() {
		Graphics.setParticles(Palette.PINK);
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
        
        if (Vision.search.isActive()) {
			Vision.search.render(sprite_batch, shape_renderer);
			Vision.search.update();
		} else {
			update();
		}
	}
	
	private void draw() {
		for (int i = 0; i < panes.size(); i++) {
			if ((x - 1) == i) {
				panes.get(i).renderAlt(sprite_batch, shape_renderer, Vision.movies.get(i).getPoster());
			} else {
				panes.get(i).render(sprite_batch, shape_renderer, Vision.movies.get(i).getPoster());
			}
		}
	}
	
	private void update() {
		if (Controller.isSearch()) {
			Vision.search.toggleSearch();
		}
		else if (Controller.isRed()) {
            vision.setScreen(vision.media_screen);
        }
		else if (Controller.isGreen()) {
			try {
				Desktop.getDesktop().open(Vision.movies.get(x - 1).getFile());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				robot.keyPress(KeyEvent.VK_WINDOWS);
				robot.keyPress(KeyEvent.VK_UP);
				robot.keyRelease(KeyEvent.VK_WINDOWS);
				robot.keyRelease(KeyEvent.VK_UP);
				
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			}
		}
		else if (Controller.isLeft() && x > 1) {
			x--;
		}
		else if (Controller.isRight() && x <= panes.size()) {
			x++;
		}
	}
	
	private void scan() {
		Vision.movies.clear();
		panes.clear();
		
		File[] files = new File("C:/Movies/").listFiles();

		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(".mp4") || files[i].getName().contains(".mkv") || files[i].getName().contains(".avi")) {
				String[] s = parseFilm(files[i].getName().toString());
	        	Vision.movies.add(new Movie(s[0], s[1], files[i]));
	        }
		}
		
		generatePanes();
	}
	
	private void generatePanes() {
		for (int i = 0; i < Vision.movies.size(); i++) {
        	panes.add(new MediaPane(Palette.PINK, Vision.movies.get(i).getTitle(), -1920 + (184 + (i * 914)), -670));
		}
	}
	
	private String[] parseFilm(String name) {
		String title = name.substring(0, name.length() - 11);
		String year = name.substring(name.length() - 9, name.length() - 5);
		String[] s = {title, year};
		
		return s;
	}

	@Override
	public void dispose() {
		sprite_batch.dispose();
		shape_renderer.dispose();
	}

	@Override public void resize(int width, int height) {}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void hide() {}
}
