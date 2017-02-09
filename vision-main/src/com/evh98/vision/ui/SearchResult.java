/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.ui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.badlogic.gdx.Gdx;
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
	private final Object param;
	
	public SearchResult(Color color, Sprite icon, String result, Object param) {
		this.color = color;
		this.icon = icon;
		
		if (!(result.length() > 36)) {
			this.result = result;
		} else {
			this.result = result.substring(0, 36) + "...";
		}
		
		this.param = param;
	}
	
	public void render(SpriteBatch sprite_batch, ShapeRenderer shape_renderer, BitmapFont font, int y) {
        // Backing
        renderBacking(shape_renderer, y, Palette.LIGHT_GRAY);
        
        // Icon & Result
        renderData(sprite_batch, font, Palette.DARK_GRAY, y);
	}

	public void renderAlt(SpriteBatch sprite_batch, ShapeRenderer shape_renderer, BitmapFont font, int y) {
        // Backing
        renderBacking(shape_renderer, y, color);
        
        // Icon & Result
        renderData(sprite_batch, font, Palette.LIGHT_GRAY, y);
	}
	
    
    private void renderBacking(ShapeRenderer shape_renderer, int y, Color color) {
        shape_renderer.begin(ShapeType.Filled);
        shape_renderer.setColor(color);
        Graphics.drawRect(shape_renderer, -1920, y, 3840, 256);
        shape_renderer.end();
    }
    
    private void renderData(SpriteBatch sprite_batch, BitmapFont font, Color color, int y) {
    	sprite_batch.begin();
    	Graphics.drawSprite(sprite_batch, icon, -1860, y + 32, 192, 192, color);
//    	Graphics.drawSprite(sprite_batch, icon, -1760, y + 128, color);
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
		if (icon == Icons.MOVIES) {
			Vision.movies.get((int) param).open();
		} else if (icon == Icons.YOUTUBE) {
			Gdx.net.openURI((String) param);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Robot robot = null;
			
			try {
				robot = new Robot();
			} catch(AWTException e) {
				e.printStackTrace();
			}
			
			// Fullscreen browser
			robot.keyPress(KeyEvent.VK_WINDOWS);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_WINDOWS);
			robot.keyRelease(KeyEvent.VK_UP);
			
			// Play
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			
			// Fullscreen video
			robot.keyPress(KeyEvent.VK_F);
			robot.keyRelease(KeyEvent.VK_F);
		}
	}
}
