/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.evh98.vision.Vision;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

public class Pane {
	
	Color color;
	String text;
	Sprite icon;
	int x;
	int y;
	BitmapFont font_text;
	
	/**
	 * Initializes a pane object
	 */
	public Pane(Color color, String text, Sprite icon, int x, int y) {
		this.color = color;
		this.text = text;
		this.icon = icon;
		this.x = x;
		this.y = y;
		
		font_text = Graphics.createFont(Graphics.font_roboto_thin, 192);
	}
	
	/*
	 * Rendering of the main pane
	 */
	public void render(SpriteBatch sprite_batch, ShapeRenderer shape_renderer) {
		// Draw rectangle
		shape_renderer.begin(ShapeType.Filled);
		shape_renderer.setColor(color);
		Graphics.drawRect(shape_renderer, x, y, 1536, 768);
		shape_renderer.end();
		// Draw title
		sprite_batch.begin();
		font_text.setColor(Palette.LIGHT_GRAY);
		Graphics.drawText(sprite_batch, font_text, text, x + 768, y + 532);
		// Draw icon
		Graphics.drawSprite(sprite_batch, icon, x + 768, y + 232, Palette.LIGHT_GRAY);
		sprite_batch.end();
	}
	
	/*
	 * Rendering of the selected pane
	 */
	public void renderAlt(SpriteBatch sprite_batch, ShapeRenderer shape_renderer) {
		GL20 gl = Gdx.graphics.getGL20();
		gl.glEnable(GL20.GL_BLEND);
		gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		// Draw rectangle
		shape_renderer.begin(ShapeType.Filled);
		shape_renderer.setColor(242F, 242F, 242F, 0.9F);
		Graphics.drawRect(shape_renderer, x, y, 1536, 768);
		shape_renderer.end();
		gl.glDisable(GL20.GL_BLEND);
		// Draw outline
		gl.glLineWidth(3 * Vision.SCALE);
		shape_renderer.begin(ShapeType.Line);
		shape_renderer.setColor(color);
		Graphics.drawRect(shape_renderer, x, y, 1536, 768);
		shape_renderer.end();
		// Draw title
		sprite_batch.begin();
		font_text.setColor(color);
		Graphics.drawText(sprite_batch, font_text, text, x + 768, y + 532);
		// Draw icon
		Graphics.drawSprite(sprite_batch, icon, x + 768, y + 232, color);
		sprite_batch.end();
	}
}
