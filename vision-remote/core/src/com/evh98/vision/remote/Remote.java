package com.evh98.vision.remote;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryonet.Client;

public class Remote extends ApplicationAdapter {

	ActionResolver actionResolver;

	SpriteBatch batch;

	public Client client;

	Texture main;

	public Remote(ActionResolver actionResolver) {
		this.actionResolver = actionResolver;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		client = new Client();
		client.start();
		client.discoverHost(28960, 5000);
		try {
			client.connect(5000, client.discoverHost(28960, 5000).getHostAddress(), 4567, 28960);
		} catch (Exception e) {
			e.printStackTrace();
		}

		main = new Texture(Gdx.files.internal("main.png"));
		main.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
			batch.draw(main, 0, 0);
		batch.end();

		update();
	}

	private void update() {
		if (Gdx.input.justTouched()) {
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();

			if (x >= 967 && x <= 1267 && y >= 1872 && y <= 2172) {
				client.sendTCP("green");
			}
			else if (x >= 176 && x <= 476 && y >= 1872 && y <= 2172) {
				client.sendTCP("red");
			}
			else if (x >= 176 && x <= 476 && y >= 1079 && y <= 1379) {
				client.sendTCP("assistant yema oooo");
			}
			else if (x >= 967 && x <= 1267 && y >= 1079 && y <= 1079) {
				client.sendTCP("yellow");
			}
			else if (x >= 579 && x <= 861 && y >= 1236 && y <= 1518) {
				client.sendTCP("up");
			}
			else if (x >= 579 && x <= 861 && y >= 1729 && y <= 2011) {
				client.sendTCP("down");
			}
			else if (x >= 332 && x <= 614 && y >= 1483 && y <= 1765) {
				client.sendTCP("left");
			}
			else if (x >= 826 && x <= 1108 && y >= 1483 && y <= 1765) {
				client.sendTCP("right");
			}
			else if (x >= 561 && x <= 879 && y >= 568 && y <= 886) {
				actionResolver.showSpeechPopup();
			}
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
