package com.evh98.vision.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evh98.vision.Vision;

public class Loading {

	Animation animation;
	Texture sheet;
	
	float stateTime;
	
	public Loading() {
		sheet = new Texture(Gdx.files.internal("loading.png"));
		sheet.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion[][] temp = TextureRegion.split(sheet, 640, 256);
		
		TextureRegion[] frames = new TextureRegion[8 * 20];
		
		int index = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 8; j++) {
				frames[index++] = temp[i][j];
			}
		}
		
		animation = new Animation(0.02F, frames);
		
		stateTime = 0;
	}
	
	public void render(SpriteBatch batch, float delta) {
		stateTime += delta;
		TextureRegion frame = animation.getKeyFrame(stateTime, true);
		
		batch.begin();
			batch.draw(frame, 2560 * Vision.SCALE, 1920 * Vision.SCALE);
		batch.end();
	}
}