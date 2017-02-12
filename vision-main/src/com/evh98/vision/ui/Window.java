package com.evh98.vision.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.evh98.vision.Vision;
import com.evh98.vision.util.Graphics;

public class Window {

	protected final int width;
	protected final int height;
	private int x;
	private int y;
	protected final Color color;
	protected final Color borderColor;
	protected boolean isActive;
	
	public Window(int width, int height, Color color, Color borderColor) {
		this.width = width;
		this.height = height;
		this.x = -1920 + ((3840 - width) / 2);
		this.y = -1080 + ((2160 - height) / 2);
		this.color = color;
		this.borderColor = borderColor;
		this.isActive = false;
	}
	
	public void render(SpriteBatch sprite_batch, ShapeRenderer shape_renderer) {
		GL20 gl = Gdx.graphics.getGL20();
		gl.glEnable(GL20.GL_BLEND);
		gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		shape_renderer.begin(ShapeType.Filled);
			shape_renderer.setColor(color);
			Graphics.drawRect(shape_renderer, x, y, width, height);
		shape_renderer.end();

		gl.glDisable(GL20.GL_BLEND);
		gl.glLineWidth(12 * Vision.SCALE);
		shape_renderer.begin(ShapeType.Line);
			shape_renderer.setColor(borderColor);
			Graphics.drawRect(shape_renderer, x, y, width, height);
		shape_renderer.end();
		
		draw(sprite_batch);
	}
	
	public void draw(SpriteBatch sprite_batch) {
		
	}
	
	public void update() {
		
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive() {
		this.isActive = true;
	}
	
	public void setInactive() {
		this.isActive = false;
	}
}