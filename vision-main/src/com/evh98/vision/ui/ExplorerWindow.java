package com.evh98.vision.ui;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Icons;
import com.evh98.vision.util.Palette;

public class ExplorerWindow extends Window {

	int x = -1; int y = 1;
	
	private final File file;
	private final String type;
	
	private ArrayList<ExplorerFile> files;
	
	private final BitmapFont font;
	
	private final int[][] positions = 
		{{1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1},
		{1, 2}, {2, 2}, {3, 2}, {4, 2}, {5, 2},
		{1, 3}, {2, 3}, {3, 3}, {4, 3}, {5, 3},
		{1, 4}, {2, 4}, {3, 4}, {4, 4}, {5, 4}};
	
	public ExplorerWindow(String path, String type, Color border) {
		super(3200, 1800, Palette.LIGHT_GRAY, border);
		this.file = new File(path);
		this.type = type;
		
		this.files = new ArrayList<ExplorerFile>();
		
		this.font = Graphics.createFont(Graphics.font_roboto_light, 64);
		
		generateFiles();
	}
	
	@Override
	public void draw(SpriteBatch sprite_batch) {
		for (ExplorerFile file : files) {
			file.draw(sprite_batch);
		}
	}
	
	@Override
	public void update() {
		if (Controller.isRed() || Controller.isBlue()) {
			setInactive();
		} 
		else if (Controller.isNavigationKey()) {
			int[] newCoords = Controller.getNewXY(x, y, 5, 4, files.size());
			x = newCoords[0];
			y = newCoords[1];
		}
	}
	
	private void generateFiles() {
		files.clear();
		
		int i = 0;
		for (File file : file.listFiles()) {
			if (file.isDirectory() && !file.getName().startsWith(".")) {
				files.add(new ExplorerFile(file, Icons.FOLDER, font, positions[i]));
				i++;
			}
			if (i == 19) return;
		}
	}
	
	public File getFile() {
		return file;
	}

	public String getType() {
		return type;
	}
}