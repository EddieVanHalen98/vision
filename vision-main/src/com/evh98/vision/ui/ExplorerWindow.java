package com.evh98.vision.ui;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Data;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Icons;
import com.evh98.vision.util.Palette;

public class ExplorerWindow extends Window {

	int x = -1; int y = 1;
	
	private File file;
	private final String type;
	
	private ArrayList<ExplorerFile> files;
	
	private final BitmapFont font;
	
	private final int[][] positions = 
		{{1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1},
		{1, 2}, {2, 2}, {3, 2}, {4, 2}, {5, 2},
		{1, 3}, {2, 3}, {3, 3}, {4, 3}, {5, 3},
		{1, 4}, {2, 4}, {3, 4}, {4, 4}, {5, 4}};
	
	public ExplorerWindow(String path, String type, Color border) {
		super(Palette.LIGHT_GRAY, border);
		
		this.file = new File(path);
		this.type = type;
		
		this.files = new ArrayList<ExplorerFile>();
		
		this.font = Graphics.createFont(Graphics.font_roboto_light, 64);
		
		generateFiles();
	}
	
	@Override
	public void draw(SpriteBatch sprite_batch) {
		for (int i = 0; i < files.size(); i++) {
            if (positions[i][0] == x && positions[i][1] == y) {
                files.get(i).drawSelected(sprite_batch, borderColor);
                
                if (Controller.isGreen()) {
                	ExplorerFile f = files.get(i);
                	
                	if (f.getFile().getPath().equals("Previous Folder") && file.getParentFile().exists()) {
                		try {
            				file = file.getParentFile();
            	    		generateFiles();
            			} catch (Exception e) {
            				e.printStackTrace();
            			}
                	}
                	if (f.getFile().getPath().equals("Select Folder") || 
                			f.getType().equals(Icons.GAMES) || f.getType().equals(Icons.MOVIES)) {
                		Data.explorer_result = f.getFile();
                		setInactive();
                	} else {
                		file = files.get(i).getFile();
                		generateFiles();
                	}
                }
                
            } else {
                files.get(i).draw(sprite_batch);
            }
        }
	}
	
	@Override
	public void update() {
		if (Controller.isRed()) {
			try {
				file = file.getParentFile();
	    		generateFiles();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (Controller.isNavigationKey()) {
			int[] newCoords = Controller.getNewXY(x, y, 5, 4, files.size());
			x = newCoords[0];
			y = newCoords[1];
		}
	}
	
	private void generateFiles() {
		files.clear();
		files.add(new ExplorerFile(new File("Previous Folder"), Icons.BACK, font, positions[0]));
		
		int i = 1;
		for (File file : file.listFiles()) {
			if (file.isDirectory() && !file.getName().startsWith(".")) {
				files.add(new ExplorerFile(file, Icons.FOLDER, font, positions[i]));
				i++;
			}
			if (type.equals("games") && file.getName().endsWith(".exe")) {
				files.add(new ExplorerFile(file, Icons.GAMES, font, positions[i]));
				i++;
			} else if (type.equals("movies") && 
					(file.getName().endsWith(".mp4") || file.getName().endsWith(".mkv") || file.getName().endsWith(".avi"))) {
				files.add(new ExplorerFile(file, Icons.MOVIES, font, positions[i]));
				i++;
			}
			if (i == 19) break;
		}
		
		files.add(new ExplorerFile(new File("Select Folder"), Icons.CONFIRM, font, positions[i++]));
	}
	
	public File getFile() {
		return file;
	}

	public String getType() {
		return type;
	}
}