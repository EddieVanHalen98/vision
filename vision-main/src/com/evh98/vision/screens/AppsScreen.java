/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.screens;

import java.util.ArrayList;

import com.evh98.vision.Vision;
import com.evh98.vision.ui.SmallPane;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Icons;
import com.evh98.vision.util.Palette;

public class AppsScreen extends VisionScreen {
	
    int x = -1, y = -1;
	
    ArrayList<SmallPane> panes;
    int[][] panesPos = {{1, 1}, {2, 1}, {3, 1}, {4, 1}, {1, 2}, {2, 2}, {3, 2}, {4, 2}};
    
	public AppsScreen(Vision vision) {
		super(vision);
		
		panes = new ArrayList<SmallPane>();
		panes.add(new SmallPane("http://www.facebook.com", Palette.YELLOW, Palette.BLUE, "Facebook", Icons.FACEBOOK, panesPos[0]));
		panes.add(new SmallPane("http://www.twitter.com", Palette.YELLOW, Palette.BLUE, "Twitter", Icons.TWITTER, panesPos[1]));
		panes.add(new SmallPane(vision.lights_screen, Palette.YELLOW, Palette.YELLOW, "Lights", Icons.BULB, panesPos[2]));
		panes.add(new SmallPane("http://web.whatsapp.com", Palette.YELLOW, Palette.GREEN, "WhatsApp", Icons.WHATSAPP, panesPos[3]));
	}

	@Override
	public void show() {
		start(Palette.YELLOW, "default");
	}
	
	@Override
	public void draw(float delta) {
		for (int i = 0; i < panes.size(); i++) {
            if (panesPos[i][0] == x && panesPos[i][1] == y) {
                panes.get(i).renderAlt(sprite_batch, shape_renderer);
            } else {
                panes.get(i).render(sprite_batch, shape_renderer);
            }
        }
	}
	
	@Override
	public void update() {
		if (Controller.isGreen()) {
			for (int i = 0; i < panes.size(); i++) {
				if (panesPos[i][0] == x && panesPos[i][1] == y) {
					panes.get(i).open(vision);
					vision.server.sendToAllTCP(panes.get(i).getText());
				}
			}
		}
		else if (Controller.isRed()) {
        	vision.setScreen(vision.home_screen);
        }
        else if(Controller.isNavigationKey()) {
			int[] newCoords = Controller.getNewXY(x, y, 4, 1, panes.size());
			x = newCoords[0];
			y = newCoords[1];
		}
	}

	@Override
	public void dispose() {
		
	}
}