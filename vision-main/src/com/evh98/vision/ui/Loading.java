package com.evh98.vision.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evh98.vision.util.Graphics;

public class Loading {

	Animation animation;
	Texture sheet;
	
	float stateTime;
	
	int ROWS = 20;
	int COLUMNS = 18;
	
	boolean isActive;
	
	public Loading() {
		sheet = new Texture(Gdx.files.internal("loading.png"));
		sheet.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion[][] temp = TextureRegion.split(sheet, sheet.getWidth() / COLUMNS, sheet.getHeight() / ROWS);
		
		TextureRegion[] frames = new TextureRegion[ROWS * COLUMNS];
		
		int index = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				frames[index++] = temp[i][j];
			}
		}
		
		animation = new Animation(0.005F, frames);
		
		stateTime = 0;
		
		isActive = false;
	}
	
	public void render(SpriteBatch batch, float delta) {
		stateTime += delta;
		Sprite frame = new Sprite(animation.getKeyFrame(stateTime, true));
		
		batch.begin();
			Graphics.drawSprite(batch, frame, 1440, 600);
		batch.end();
	}
	
	public void render(SpriteBatch batch, float delta, int x, int y) {
		stateTime += delta;
		Sprite frame = new Sprite(animation.getKeyFrame(stateTime, true));
		
		batch.begin();
			Graphics.drawSprite(batch, frame, x, y);
		batch.end();
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive() {
		isActive = true;
	}
	
	public void setInactive() {
		isActive = false;
	}
}