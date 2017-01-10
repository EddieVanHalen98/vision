/**
 * Vision
 * 
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.media;

import java.io.File;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Game {

	private final String title;
	private final File file;
	
	private Sprite poster;
	private String genre;
	private String release;
	
	public Game (String title, File file) {
		this.title = title;
		this.file = file;
		
		scanPoster();
	}
	
	public void scanPoster() {
		
	}

	public String getTitle() {
		return title;
	}

	public File getFile() {
		return file;
	}

	public Sprite getPoster() {
		return poster;
	}

	public String getGenre() {
		return genre;
	}

	public String getRelease() {
		return release;
	}
	
	public void open() {
		
	}
}