package com.evh98.vision.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

public class SettingsPane {

	private String text;
	private Sprite icon;
	private int[] position;
	private int x;
	private int y;
	private BitmapFont font_text;
	
	/**
	 * Initializes a settings pane object
	 */
	public SettingsPane(String text, Sprite icon, int[] position) {
		this.text = text;
		this.icon = icon;

		this.position = position;
		this.x = (640 * (position[0] - 1)) - 1920;
		this.y = (640 * (position[1] - 1)) - 1080;
		
		this.font_text = Graphics.createFont(Graphics.font_roboto_thin, 128);
	}
	
	/*
	 * Rendering of the main pane
	 */
	public void render(SpriteBatch sprite_batch, ShapeRenderer shape_renderer) {
		renderRectangle(shape_renderer, Palette.GREEN);
		renderData(sprite_batch, Palette.LIGHT_GRAY);
	}
	
	/*
	 * Rendering of the main pane
	 */
	public void renderAlt(SpriteBatch sprite_batch, ShapeRenderer shape_renderer) {
		renderRectangle(shape_renderer, Palette.LIGHT_GRAY);
		renderData(sprite_batch, Palette.GREEN);
	}
	
	private void renderRectangle(ShapeRenderer shape_renderer, Color color) {
        shape_renderer.begin(ShapeType.Filled);
        shape_renderer.setColor(color);
        Graphics.drawRect(shape_renderer, x, y, 640, 640);
        shape_renderer.end();
    }
    
    private void renderData(SpriteBatch sprite_batch, Color color) {
        sprite_batch.begin();
        font_text.setColor(color);
		Graphics.drawSprite(sprite_batch, icon, x + 320, y + 256, color);
        Graphics.drawText(sprite_batch, font_text, text, x + 320, y + 500);
        sprite_batch.end();
    }
    
    public int[] getPosition() {
    	return position;
    }
}