package com.evh98.vision.apps;

import com.badlogic.gdx.Gdx;
import com.evh98.vision.Vision;
import com.evh98.vision.screens.VisionScreen;
import com.evh98.vision.util.Data;

public class LinkScreen extends VisionScreen {

	public LinkScreen(Vision vision) {
		super(vision);
	}

	@Override
	public void show() {
		Gdx.net.openURI(Data.link);
		
		vision.setScreen(vision.media_screen);
	}
}