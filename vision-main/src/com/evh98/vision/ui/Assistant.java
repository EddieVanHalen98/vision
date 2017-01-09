package com.evh98.vision.ui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.evh98.vision.Vision;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;

public class Assistant {
	
	Robot robot;
	
	BitmapFont font;
	
	String output;
	
	private boolean isActive;
	
	public Assistant() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		font = Graphics.createFont(Graphics.font_roboto_thin, 176);
		
		output = "";
		
		isActive = false;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void render(SpriteBatch sprite_batch, ShapeRenderer shape_renderer) {
		// Draw rectangle
        shape_renderer.begin(ShapeType.Filled);
        shape_renderer.setColor(Palette.PURPLE);
        Graphics.drawRect(shape_renderer, -1920, 640, 3840, 256);
        shape_renderer.end();
		
		// Search output
        sprite_batch.begin();
        font.setColor(Palette.WHITE);
        Graphics.drawText(sprite_batch, font, output, 0, 768);
        sprite_batch.end();
	}
	
	public void update() {
		if (Controller.isAnyKey()) {
			isActive = false;
		}
	}
	
	public void execute(String text) {
		isActive = true;

		String command = text.substring(0, text.indexOf(' '));
		String param = text.substring(text.indexOf('\"') + 1, text.length() - 3).toLowerCase();
		
		System.out.println(command + param);
		
		if (command.equals("input.unknown")) {
			inputUnknown();
		}
		else if (command.equals("movie.play")) {
			moviePlay(param);
		}
		else if (command.equals("playback.pause")) {
			playbackPause();
		}
		else if (command.equals("time.get")) {
			timeGet();
		}
	}
	
	private void inputUnknown() {
		output = "Sorry, I didn't understand that";
	}
	
	private void moviePlay(String title) {
		output = "Sure thing!";
		
		Vision.search.feelingLuckyMovies(title);
	}
	
	private void playbackPause() {
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_SPACE);
	}
	
	private void timeGet() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss aaa");
		output = sdf.format(cal.getTime());
	}
}