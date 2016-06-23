/**
 * Vision - Created and owned by James T Saeed (EddieVanHalen98)
 * 
 * NetflixScreen.java
 * Screen for Vision Netflix app
 * 
 * File created on 21st April 2016
 */

package com.evh98.vision.apps;

import com.evh98.vision.Vision;
import com.evh98.vision.screens.Screen;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;

public class NetflixScreen extends Screen {
	
	Browser browser;
    BrowserView browserView;
    
	public NetflixScreen(GraphicsContext gc) {
		super(gc);
	}
	
	@Override
	public void start() {
		browser = new Browser();
		browserView = new BrowserView(browser);
		
		Scene scene = new Scene(new BorderPane(browserView), Vision.WIDTH * Vision.SCALE, Vision.HEIGHT * Vision.SCALE);
		Vision.main_stage.setScene(scene);
		browser.loadURL("https://www.netflix.com/Login");
	}

	@Override
	public void render() {
		
	}
}