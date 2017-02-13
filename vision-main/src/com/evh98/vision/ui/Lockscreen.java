package com.evh98.vision.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evh98.vision.util.Data;
import com.evh98.vision.util.Graphics;

public class Lockscreen {
	
	private boolean isActive = false;
	
	BitmapFont font;
	
    Calendar cal;
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

	public Lockscreen() {
		font = Graphics.createFont(Graphics.font_roboto_thin, 128);
	}
	
	public void render(SpriteBatch sprite_batch) {
        cal = Calendar.getInstance();
        
		sprite_batch.begin();
			Graphics.drawLock(sprite_batch);
	        Graphics.drawText(sprite_batch, font, sdf.format(cal.getTime()), 0, -888);
	        Graphics.drawText(sprite_batch, font, Data.user_email, 0, 888);
		sprite_batch.end();
	}

	public boolean isActive() {
		return isActive;
	}

	public void toggle(String email) {
		if (isActive && email.equals(Data.user_email)) {
			isActive = false;
		} else {
			Data.user_email = email;
			isActive = true;
		}
	}
}