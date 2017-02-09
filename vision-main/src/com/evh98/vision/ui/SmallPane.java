/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.ui;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

public class SmallPane {

	private Screen screen;
	private Color screen_color;
	private Color pane_color;
	private String text;
	private Sprite icon;
	private int x;
	private int y;
	private BitmapFont font_text;

	/**
	 * Initializes a small pane object
	 */
	public SmallPane(Screen screen, Color screen_color, Color pane_color, String text, Sprite icon, int[] position) {
		this.screen = screen;
		this.screen_color = screen_color;
		this.pane_color = pane_color;
		this.text = text;
		this.icon = icon;
		
		this.x = (-1728) + (912 * (position[0] - 1));
		this.y = (-810) + (900 * (position[1] - 1));

		this.font_text = Graphics.createFont(Graphics.font_roboto_thin, 108);
	}

	/*
	 * Rendering of the main pane
	 */
    public void render(SpriteBatch sprite_batch, ShapeRenderer shape_renderer) {
        // Draw rectangle
        renderRectangle(shape_renderer, Palette.DARK_GRAY);
		// Draw title & icon
        renderData(sprite_batch, font_text);
	}

	/*
	 * Rendering of the selected pane
	 */
	public void renderAlt(SpriteBatch sprite_batch, ShapeRenderer shape_renderer) {
		// Draw rectangle
        renderRectangle(shape_renderer, screen_color);
		// Draw title & icon
        renderData(sprite_batch, font_text);
	}
    
    private void renderRectangle(ShapeRenderer shape_renderer, Color color) {
        shape_renderer.begin(ShapeType.Filled);
        shape_renderer.setColor(pane_color);
        Graphics.drawRect(shape_renderer, x, y, 720, 542);
        shape_renderer.setColor(color);
        Graphics.drawRect(shape_renderer, x, y + 540, 720, 180);
        shape_renderer.end();
    }
    
    private void renderData(SpriteBatch sprite_batch, BitmapFont font_text) {
        sprite_batch.begin();
        Graphics.drawText(sprite_batch, font_text, text, x + 360, y + 630);
		Graphics.drawSprite(sprite_batch, icon, x + 360, y + 270, Palette.LIGHT_GRAY);
        sprite_batch.end();
    }

	public Screen getScreen() {
		return screen;
	}

	public String getText() {
		return text;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
