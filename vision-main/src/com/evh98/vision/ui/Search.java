/**
 * Vision
 *
 * Created and owned by James T Saeed (EddieVanHalen98)
 */

package com.evh98.vision.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.evh98.vision.Vision;
import com.evh98.vision.util.Controller;
import com.evh98.vision.util.Graphics;
import com.evh98.vision.util.Icons;
import com.evh98.vision.util.Palette;
import com.evh98.vision.util.Util;

public class Search {
	
	Vision vision;
	
	BitmapFont font;
	BitmapFont fontTime;
	
	String input;
	
	private boolean isActive;
	private boolean showResults;
	
	ArrayList<SearchResult> results;
	
	int y = 0;
	
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	public Search(Vision vision) {
		this.vision = vision;
		
		font = Graphics.createFont(Graphics.font_roboto_thin, 176);
		fontTime = Graphics.createFont(Graphics.font_roboto_bold, 128);
		
		input = "";
		
		isActive = false;
		showResults = false;
		
		results = new ArrayList<SearchResult>();
	}
	
	public void render(SpriteBatch sprite_batch, ShapeRenderer shape_renderer) {
		// Draw rectangle
        shape_renderer.begin(ShapeType.Filled);
        shape_renderer.setColor(Palette.SYSTEM);
        Graphics.drawRect(shape_renderer, -1920, 640, 3840, 256);
        shape_renderer.end();

		// Search icon
        sprite_batch.begin();
        Graphics.drawSprite(sprite_batch, Icons.SEARCH, -1760, 768, Palette.WHITE);
		
		// Search input
        font.setColor(Palette.WHITE);
        Graphics.drawText(sprite_batch, font, input, 0, 768);
		
        // Time
        Graphics.drawText(sprite_batch, fontTime, sdf.format(cal.getTime()), 1640, 768);
        sprite_batch.end();
        
		if (showResults) {
			for (int i = 0; i < results.size(); i++) {
				if ((i + 1) != y) {
					results.get(i).render(sprite_batch, shape_renderer, font, 384 - (i * 256));
				} else {
					results.get(i).renderAlt(sprite_batch, shape_renderer, font, 384 - (i * 256));
				}
			}
		}
	}
	
	public void update() {
        if (Controller.isSearch()) {
            toggleSearch();
            showResults = false;
        }
				
        if (showResults) {
            if (Controller.isGreen()) results.get(y - 1).open();
            else if (Controller.isUp() && y < results.size()) y++;
            else if (Controller.isDown() && y > 0) y--;
            else if (Controller.isRed()) {
            	results.clear(); 
            	toggleSearch();
            }
        } else {
        	if (Controller.letterPressed() != "/" && input.length() <= 32) input += Controller.letterPressed();
        	else if (Controller.digitPressed() != "/" && input.length() <= 32) input += Controller.digitPressed();
        	else if (Controller.isSpace() && input.length() <= 32) input += " ";
        	else if (Controller.isBackspace() && input.length() != 0) input = input.substring(0, input.length() - 1);
        	else if (Controller.isEnter()) {
        		results.clear();
        		search();
        	}
        }
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void toggleSearch() {
		input = "";
		isActive = !isActive;
		y = 0;
	}
	
	public void toggleResults() {
		showResults = !showResults;
	}
	
	public void feelingLuckyMovies(String movie) {
		input = movie;
		for (int i = 0; i < Vision.movies.size(); i++) {
			String title = Vision.movies.get(i).getTitle().toLowerCase();
			if (similarity(title, input) >= 0.5 || title.contains(input)) {
				Vision.movies.get(i).open();
				return;
			}
		}
	}
	
	private void search() {
		searchMovie();
		if (Util.isNetworkAvailable()) searchYouTube();
		
		if (results.size() == 0) {
			results.add(new SearchResult(Palette.DARK_GRAY, Icons.INFO, "No results found", 0));
		}
		
		toggleResults();
	}
	
	private void searchMovie() {
		for (int i = 0; i < Vision.movies.size(); i++) {
			String title = Vision.movies.get(i).getTitle().toLowerCase();
			if (similarity(title, input) >= 0.5 || title.contains(input)) {
				results.add(new SearchResult(Palette.PINK, Icons.MOVIES, Vision.movies.get(i).getTitle(), i));
			}
		}
	}
	
	private void searchYouTube() {
	    List<com.google.api.services.youtube.model.SearchResult> searchResult = 
	    		vision.youtube_screen.searchVideos(input, 1);
	    
	    results.add(new SearchResult(Palette.RED, Icons.YOUTUBE, searchResult.get(0).getSnippet().getTitle(), "https://youtu.be/" + searchResult.get(0).getId().getVideoId()));
	}
	
	public double similarity(String s1, String s2) {
		String longer = s1, shorter = s2;
		if (s1.length() < s2.length()) {
			longer = s2; shorter = s1;
		}
		int longerLength = longer.length();
		if (longerLength == 0) {
			return 1.0;
		}
		return (longerLength - StringUtils.getLevenshteinDistance(longer, shorter)) / (double) longerLength;
	}
}
