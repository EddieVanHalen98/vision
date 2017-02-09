/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evh98.vision.Vision;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Palette;
import com.evh98.vision.util.Update;

public class UpdateScreen extends VisionScreen {

    BitmapFont font;
	
    int stage = 0;
    String text = "An update is available!\nPress Space/Green to continue\nPress Escape/Red to cancel";
	
	public UpdateScreen(Vision vision) {
		super(vision);
		
        font = Graphics.createFont(Graphics.font_roboto_thin, 192);
        font.setColor(Palette.DARK_GRAY);
	}

	@Override
	public void show() {
		start(Palette.PURPLE, "update");
	}
	
	@Override
	public void draw(float delta) {
		sprite_batch.begin();
            Graphics.drawText(sprite_batch, font, text, 0, 0);
		sprite_batch.end();
	}
	
	@Override
	public void update() {
        if (Controller.isGreen()) {
            if (stage == 0) {
                text = "Downloading...";
                Update.downloadUpdate();
                text = "Finished!\nPress Space/Green to continue";
                stage++;
            } else if (stage == 1) {
                Gdx.app.exit();
            }
        }
	}

	@Override
	public void dispose() {
		
	}
}