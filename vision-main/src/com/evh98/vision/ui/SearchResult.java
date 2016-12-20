/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.ui;

import java.awt.Desktop;
import java.io.IOException;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.evh98.vision.Vision;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Icons;
import com.evh98.vision.util.Palette;

public class SearchResult {

	private final Color color;
	private final Sprite icon;
	private final String result;
	private final int position;
	
	public SearchResult(Color color, Sprite icon, String result, int position) {
		this.color = color;
		this.icon = icon;
		this.result = result;
		this.position = position;
	}

	
	public void render(SpriteBatch sprite_batch, ShapeRenderer shape_renderer, BitmapFont font, int y) {
        // Backing
        renderBacking(shape_renderer, y);
        
        // Icon & Result
        renderData(sprite_batch, font, Palette.DARK_GRAY, y);
	}

	public void renderAlt(SpriteBatch sprite_batch, ShapeRenderer shape_renderer, BitmapFont font, int y) {
        // Backing
        renderBacking(shape_renderer, y);
        
        // Icon & Result
        renderData(sprite_batch, font, color, y);
	}
	
    
    private void renderBacking(ShapeRenderer shape_renderer, int y) {
        shape_renderer.begin(ShapeType.Filled);
        shape_renderer.setColor(Palette.LIGHT_GRAY);
        Graphics.drawRect(shape_renderer, -1920, y, 3840, 256);
        shape_renderer.end();
    }
    
    private void renderData(SpriteBatch sprite_batch, BitmapFont font, Color color, int y) {
    	sprite_batch.begin();
    	Graphics.drawSprite(sprite_batch, icon, -1760, y + 128, color);
    	font.setColor(color);
    	Graphics.drawText(sprite_batch, font, result, 0, y + 128);
    	sprite_batch.end();
    }
	
	public Color getColor() {
		return color;
	}
	
	public Sprite getIcon() {
		return icon;
	}

	public String getResult() {
		return result;
	}
	
	public void open() {
		if (icon == Icons.MOVIES_ALT) {
			try {
				Desktop.getDesktop().open(Vision.movies.get(position).getFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
